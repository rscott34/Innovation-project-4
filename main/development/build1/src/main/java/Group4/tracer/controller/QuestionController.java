package Group4.tracer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Group4.tracer.model.Mission;
import Group4.tracer.model.User;
import Group4.tracer.repository.MissionRepository;
import Group4.tracer.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    
    @Autowired private MissionRepository missionRepository;
    @Autowired private UserRepository userRepository;
    
    //private final String[] STAGES = {"raw materials", "processing", "assembly", "transport", "retail"};
    private final Random rand = new Random();

    @GetMapping("/generate")
    public String generateQuestion(Model model, HttpSession session) {
        Object[] questionRecords = missionRepository.findMissionArray();
        List<Object[]> filteredQuestions = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        for (Object questionData : questionRecords) {
            Object[] row = (Object[]) questionData;
            
            if (!user.getMissions().contains(row[0].toString())) {
                filteredQuestions.add(row);
            }
        }
        if (filteredQuestions.isEmpty()) {
            user.emptyMissions();
            for (Object questionData : questionRecords) {
                filteredQuestions.add((Object[]) questionData);
            }
        }
        
        Object[] questionData = (Object[]) filteredQuestions.get(rand.nextInt(filteredQuestions.size()));
        
        if (questionData != null && questionData.length > 0) {
            // stageData[0] = stage_value, stageData[1] = evidence_link
            
            String options;
            if (questionData[6] == null) {
                options = "-";
            } else {
                options = questionData[6].toString();
            }

            Mission mission = new Mission(
                questionData[0].toString(),
                questionData[1].toString(),
                questionData[2].toString(),
                questionData[3].toString(),
                questionData[4].toString(),
                questionData[5].toString(),
                options,
                questionData[7].toString(),
                questionData[8].toString()
            );
            session.setAttribute("mission", mission);

            model.addAttribute("questionGenerated", true);
            model.addAttribute("mission", mission);
        } else {
            model.addAttribute("questionGenerated", false);
            model.addAttribute("mission", null);
        }
        
        // IMPORTANT: Add to model for George's UI
        
        

        return "index";
    }

    @PostMapping("/verify")
    public String verifyAnswer(
            @RequestParam String userAnswer,
            HttpSession session,
            Model model) {
        
        Mission mission = (Mission) session.getAttribute("mission");
        boolean isCorrect = userAnswer.trim().toLowerCase().equalsIgnoreCase(mission.getAnswer().toLowerCase());
        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("userAnswer", userAnswer);
        model.addAttribute("correctAnswer", mission.getAnswer());
        model.addAttribute("passport", mission.getPassport());
        model.addAttribute("anchor", mission.getAnchor());
        
        
        

        if (isCorrect) {
            int points;

            points = switch (mission.getDifficulty()) {
                case Basic -> 5;
                case Intermediate -> 10;
                case Advanced -> 20;
            };
            session.setAttribute("points", (int) session.getAttribute("points") + points);
            User user = (User) session.getAttribute("user");
            if (user != null) {
                user.addMission(mission.getId());
                System.out.println("--------------");
                System.out.println(mission.getId());
                System.out.println(user.getMissions());
                user.addToScore(points);
                //Logic for saving
                if (!((String) session.getAttribute("role")).equals("guest")) {
                        userRepository.save(user);
                } 
            } else {
                System.err.printf("Failed to find user");
            }
            model.addAttribute("resultMessage", "Correct!");
            model.addAttribute("feedback", String.format("Well done! You scored %d points for this question!", points));
            System.out.println("User now has " + session.getAttribute("points"));
        } else {
            model.addAttribute("resultMessage", "Incorrect");
            model.addAttribute("feedback", mission.getExplanation());
        }
        
        return "answer";
    }
}