package Group4.tracer;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import Group4.tracer.controller.TracerController;
import Group4.tracer.repository.ChangeLogRepository;
import Group4.tracer.repository.ClaimRepository;
import Group4.tracer.repository.EvidenceRepository;
import Group4.tracer.repository.InputSharesRepository;
import Group4.tracer.repository.ProductRepository;
import Group4.tracer.repository.StageRepository;
import Group4.tracer.repository.VerifierRepository;

@ExtendWith(MockitoExtension.class)
class FR6ComparisonViewTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TracerController tracerController;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StageRepository stageRepository;

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private EvidenceRepository evidenceRepository;

    @Mock
    private ChangeLogRepository changeLogRepository;

    @Mock
    private VerifierRepository verifierRepository;

    @Mock
    private InputSharesRepository inputSharesRepository;

    @BeforeEach
    void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(tracerController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    @DisplayName("FR6: GET /compare should load compare page")
    void fr6_comparePageLoads() throws Exception {
        mockMvc.perform(get("/compare"))
                .andExpect(status().isOk())
                .andExpect(view().name("compare"))
                .andExpect(model().attributeExists("role"));
    }

    @Test
    @DisplayName("FR6: GET /compare should set guest role when no session role exists")
    void fr6_comparePageDefaultsToGuestRole() throws Exception {
        mockMvc.perform(get("/compare"))
                .andExpect(status().isOk())
                .andExpect(view().name("compare"))
                .andExpect(model().attribute("role", "guest"));
    }

    @Test
    @DisplayName("FR6: POST /compare/submit with no input should return compare page")
    void fr6_noInputReturnsComparePage() throws Exception {
        mockMvc.perform(post("/compare/submit")
                        .param("userInput", "")
                        .param("userInput2", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("compare"))
                .andExpect(model().attributeExists("role"));
    }

    @Test
    @DisplayName("FR6: POST /compare/submit with invalid product should still return compare page")
    void fr6_invalidProductReturnsComparePage() throws Exception {
        when(productRepository.findProductArray(anyString())).thenReturn(new Object[0]);
        when(stageRepository.findStageArray(anyString())).thenReturn(new Object[0]);
        when(claimRepository.findClaimArray(anyString())).thenReturn(new Object[0]);
        when(evidenceRepository.findEvidenceArray(anyString())).thenReturn(new Object[0]);
        when(inputSharesRepository.findInputSharesArray(anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/compare/submit")
                        .param("userInput", "BAD_ID")
                        .param("userInput2", "BAD_ID_2"))
                .andExpect(status().isOk())
                .andExpect(view().name("compare"))
                .andExpect(model().attributeExists("role"));
    }

    @Test
    @DisplayName("FR6: POST /compare/submit with valid first product should show product found")
    void fr6_validFirstProductSetsProductFound() throws Exception {
        Object[] productRow = new Object[] {
                "P001", "Test Product", "Food", "Test Brand", "Test Description"
        };

        Object[] claimRow = new Object[] {
                "C001", "P001", null, "Origin", "This is a claim", "Verified", "Rationale text"
        };

        Object[] evidenceRow = new Object[] {
                "C001", "E001", "Certificate", "Issuer", "2024-01-02", "Summary", "file.pdf"
        };

        List<Object[]> shares = new ArrayList<>();
        shares.add(new Object[] { "InputA", "Brazil", 60.0 });

        when(productRepository.findProductArray("P001")).thenReturn(new Object[] { productRow });
        when(stageRepository.findStageArray("P001")).thenReturn(new Object[0]);
        when(claimRepository.findClaimArray("P001")).thenReturn(new Object[] { claimRow });
        when(evidenceRepository.findEvidenceArray("P001")).thenReturn(new Object[] { evidenceRow });
        when(inputSharesRepository.findInputSharesArray("P001")).thenReturn(shares);

        when(productRepository.findProductArray("P002")).thenReturn(new Object[0]);
        when(stageRepository.findStageArray("P002")).thenReturn(new Object[0]);
        when(claimRepository.findClaimArray("P002")).thenReturn(new Object[0]);
        when(evidenceRepository.findEvidenceArray("P002")).thenReturn(new Object[0]);
        when(inputSharesRepository.findInputSharesArray("P002")).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/compare/submit")
                        .param("userInput", "P001")
                        .param("userInput2", "P002"))
                .andExpect(status().isOk())
                .andExpect(view().name("compare"))
                .andExpect(model().attributeExists("role"))
                .andExpect(model().attributeExists("shares1"));
    }

    @Test
    @DisplayName("FR6: compare page should keep verifier role if user is logged in")
    void fr6_comparePageKeepsVerifierRole() throws Exception {
        mockMvc.perform(get("/compare")
                        .sessionAttr("role", "verifier"))
                .andExpect(status().isOk())
                .andExpect(view().name("compare"))
                .andExpect(model().attribute("role", "verifier"));
    }
}