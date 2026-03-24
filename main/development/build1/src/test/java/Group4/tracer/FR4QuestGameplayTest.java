package Group4.tracer;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Group4.tracer.controller.QuestionController;
import Group4.tracer.model.Mission;
import Group4.tracer.model.User;
import Group4.tracer.repository.MissionRepository;
import Group4.tracer.repository.UserRepository;

class FR4QuestGameplayTest {

    private MockMvc mockMvc;
    private MissionRepository missionRepository;
    private UserRepository userRepository;
    private String generatePath;
    private String verifyPath;

    @BeforeEach
    void setUp() {
        QuestionController controller = new QuestionController();
        missionRepository = Mockito.mock(MissionRepository.class);
        userRepository = Mockito.mock(UserRepository.class);

        ReflectionTestUtils.setField(controller, "missionRepository", missionRepository);
        ReflectionTestUtils.setField(controller, "userRepository", userRepository);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        generatePath = resolvePath(QuestionController.class, "generate", true);
        verifyPath = resolvePath(QuestionController.class, "verify", false);
    }

    private String resolvePath(Class<?> controllerClass, String keyword, boolean getRequest) {
        String classPrefix = "";
        RequestMapping classMapping = controllerClass.getAnnotation(RequestMapping.class);
        if (classMapping != null && classMapping.value().length > 0) {
            classPrefix = classMapping.value()[0];
        }

        for (Method method : controllerClass.getDeclaredMethods()) {
            if (!method.getName().toLowerCase().contains(keyword.toLowerCase())) {
                continue;
            }

            String methodPath = null;
            if (getRequest) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                if (getMapping != null) {
                    methodPath = firstPath(getMapping.value(), getMapping.path());
                }
            } else {
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                if (postMapping != null) {
                    methodPath = firstPath(postMapping.value(), postMapping.path());
                }
            }

            if (methodPath != null) {
                return normalisePath(classPrefix, methodPath);
            }
        }

        throw new IllegalStateException("Could not find mapped " + (getRequest ? "GET" : "POST")
                + " handler in QuestionController for keyword: " + keyword);
    }

    private String firstPath(String[] valuePaths, String[] pathPaths) {
        if (valuePaths != null && valuePaths.length > 0) {
            return valuePaths[0];
        }
        if (pathPaths != null && pathPaths.length > 0) {
            return pathPaths[0];
        }
        return "";
    }

    private String normalisePath(String prefix, String suffix) {
        String left = (prefix == null) ? "" : prefix.trim();
        String right = (suffix == null) ? "" : suffix.trim();

        if (!left.startsWith("/") && !left.isEmpty()) {
            left = "/" + left;
        }
        if (!right.startsWith("/") && !right.isEmpty()) {
            right = "/" + right;
        }

        String combined = (left + right).replaceAll("//+", "/");
        return combined.isEmpty() ? "/" : combined;
    }

    // Order must match QuestionController.generateQuestion():
    // [0] missionId, [1] productId, [2] tier, [3] question, [4] answer,
    // [5] gradingType, [6] options, [7] feedback, [8] anchor
    private Object[] missionRow(
            String missionId,
            String productId,
            String tier,
            String question,
            String answer,
            String gradingType,
            String options,
            String feedback,
            String anchor) {

        return new Object[] {
            missionId,
            productId,
            tier,
            question,
            answer,
            gradingType,
            options,
            feedback,
            anchor
        };
    }

    @Test
    @DisplayName("FR4: generate question creates a mission in session")
    void fr4_generateQuestion_createsMission() throws Exception {
        User user = new User();

        Object[] row = missionRow(
                "M001",
                "P001",
                "Basic",
                "What country is this product from?",
                "Brazil",
                "multiple_choice",
                "Brazil,UK,France,Germany",
                "Because the passport states Brazil.",
                "question-box");

        when(missionRepository.findMissionArray()).thenReturn(new Object[] { row });

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get(generatePath).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("questionGenerated", true))
                .andExpect(model().attributeExists("mission"));

        Mission mission = (Mission) session.getAttribute("mission");
        assertNotNull(mission);
        assertEquals("M001", mission.getId());
    }

    @Test
    @DisplayName("FR4: generate question resets completed missions when none are available")
    void fr4_generateQuestion_noMissionsAvailable() throws Exception {
        User user = spy(new User());
        user.addMission("M001");

        Object[] row = missionRow(
                "M001",
                "P001",
                "Basic",
                "Which stage is shown?",
                "Processing",
                "multiple_choice",
                "RawMaterials,Processing,Assembly",
                "The evidence points to processing.",
                "anchor-1");

        when(missionRepository.findMissionArray()).thenReturn(new Object[] { row });

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get(generatePath).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("questionGenerated", true))
                .andExpect(model().attributeExists("mission"));

        verify(user, times(1)).emptyMissions();
    }

    @Test
    @DisplayName("FR4: correct answer awards points and saves logged-in user")
    void fr4_verify_correctAnswer_awardsPoints() throws Exception {
        Mission mission = new Mission(
                "M001",
                "P001",
                "Basic",
                "What country is this product from?",
                "Brazil",
                "multiple_choice",
                "Brazil,UK,France,Germany",
                "Correct explanation",
                "anchor-1");

        User user = new User();
        user.setUserId("U001");
        user.setUserName("mateo");
        user.setPassword("pass");
        user.setUserTypeString("consumer");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("mission", mission);
        session.setAttribute("user", user);
        session.setAttribute("role", "consumer");
        session.setAttribute("points", 0);

        mockMvc.perform(post(verifyPath)
                .session(session)
                .param("userAnswer", "Brazil"))
                .andExpect(status().isOk())
                .andExpect(view().name("answer"))
                .andExpect(model().attribute("isCorrect", true))
                .andExpect(model().attribute("correctAnswer", "Brazil"));

        verify(userRepository).save(user);
        assertEquals(5, session.getAttribute("points"));
    }

    @Test
    @DisplayName("FR4: wrong answer gives feedback and no points")
    void fr4_verify_wrongAnswer_noPointsAwarded() throws Exception {
        Mission mission = new Mission(
                "M001",
                "P001",
                "Basic",
                "What country is this product from?",
                "Brazil",
                "multiple_choice",
                "Brazil,UK,France,Germany",
                "The correct answer was Brazil.",
                "anchor-1");

        User user = new User();
        user.setUserId("U001");
        user.setUserName("mateo");
        user.setPassword("pass");
        user.setUserTypeString("consumer");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("mission", mission);
        session.setAttribute("user", user);
        session.setAttribute("role", "consumer");
        session.setAttribute("points", 0);

        mockMvc.perform(post(verifyPath)
                .session(session)
                .param("userAnswer", "France"))
                .andExpect(status().isOk())
                .andExpect(view().name("answer"))
                .andExpect(model().attribute("isCorrect", false))
                .andExpect(model().attribute("correctAnswer", "Brazil"))
                .andExpect(model().attribute("feedback", "The correct answer was Brazil."));

        verify(userRepository, never()).save(user);
        assertEquals(0, session.getAttribute("points"));
    }

    @Test
    @DisplayName("FR4: guest correct answer does not save user")
    void fr4_verify_guestDoesNotSaveUser() throws Exception {
        Mission mission = new Mission(
                "M001",
                "P001",
                "Basic",
                "What country is this product from?",
                "Brazil",
                "multiple_choice",
                "Brazil,UK,France,Germany",
                "Correct explanation",
                "anchor-1");

        User user = new User();
        user.setUserId("U001");
        user.setUserName("mateo");
        user.setPassword("pass");
        user.setUserTypeString("consumer");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("mission", mission);
        session.setAttribute("user", user);
        session.setAttribute("role", "guest");
        session.setAttribute("points", 0);

        mockMvc.perform(post(verifyPath)
                .session(session)
                .param("userAnswer", "Brazil"))
                .andExpect(status().isOk())
                .andExpect(view().name("answer"))
                .andExpect(model().attribute("isCorrect", true));

        verify(userRepository, never()).save(user);
    }

    @Test
    @DisplayName("FR4: generate question skips missions already completed")
    void fr4_generateQuestion_skipsCompletedMissions() throws Exception {
        User user = new User();
        user.addMission("M001");

        Object[] first = missionRow(
                "M001",
                "P001",
                "Basic",
                "Completed question",
                "Brazil",
                "multiple_choice",
                "A,B,C,D",
                "Explanation 1",
                "anchor-1");

        Object[] second = missionRow(
                "M002",
                "P002",
                "Basic",
                "Uncompleted question",
                "UK",
                "multiple_choice",
                "A,B,C,D",
                "Explanation 2",
                "anchor-2");

        when(missionRepository.findMissionArray()).thenReturn(new Object[] { first, second });

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get(generatePath).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("questionGenerated", true))
                .andExpect(model().attributeExists("mission"));

        Mission mission = (Mission) session.getAttribute("mission");
        assertNotNull(mission);
        assertEquals("M002", mission.getId());
    }
}