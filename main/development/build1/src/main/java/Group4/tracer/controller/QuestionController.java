package Group4.tracer.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Group4.tracer.model.Mission;
import Group4.tracer.repository.MissionRepository;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    
    @Autowired
    private MissionRepository missionRepository;
    
    //private final String[] STAGES = {"raw materials", "processing", "assembly", "transport", "retail"};
    private final Random random = new Random();

    @GetMapping("/generate")
    public String generateQuestion(Model model) {
        Object[] questionRecords = missionRepository.findMissionArray();
        Object[] questionData = (Object[]) questionRecords[0];
        System.out.println(questionData);
        //random.nextInt(questionRecords.length-1)
        System.out.println("Found");
        
        String correctAnswer = "Information not available";
        String evidenceLink = "#";
        String questionText = "None found";
        
        if (questionData != null && questionData.length > 0) {
            // stageData[0] = stage_value, stageData[1] = evidence_link

            Mission mission = new Mission(
                questionData[0].toString(),
                questionData[1].toString(),
                questionData[2].toString(),
                questionData[3].toString(),
                questionData[4].toString(),
                questionData[5].toString(),
                questionData[6].toString()
            );
            correctAnswer = mission.getAnswer();
            evidenceLink = mission.getExplanation();
            questionText = mission.getQuestion();

            model.addAttribute("questionGenerated", true);
        } else {
            model.addAttribute("questionGenerated", false);
        }

        System.out.println(correctAnswer +  " " + evidenceLink + " " + questionText);
        
        // IMPORTANT: Add to model for George's UI
        
        model.addAttribute("questionText", questionText);
        model.addAttribute("correctAnswer", correctAnswer);
        model.addAttribute("evidenceLink", evidenceLink);

        return "mission";
    }

    @PostMapping("/verify")
    public String verifyAnswer(
            @RequestParam String userAnswer,
            @RequestParam String correctAnswer,
            @RequestParam String evidenceLink,
            Model model) {
        
        boolean isCorrect = userAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
        
        // I've added the verification results to model for George's UI
        model.addAttribute("answerSubmitted", true);
        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("userAnswer", userAnswer);
        model.addAttribute("correctAnswer", correctAnswer);
        model.addAttribute("evidenceLink", evidenceLink);  // IMPORTANT : This is for George's UI link - Rowan said to show this when the answer is wrong
        
        if (isCorrect) {
            model.addAttribute("resultMessage", "✓ Correct!");
            model.addAttribute("feedback", "Your answer matches our traceability records.");
        } else {
            model.addAttribute("resultMessage", "✗ Incorrect");
            model.addAttribute("feedback", "The correct traceability value is shown below.");
            // evidenceLink is already in model - George can use this to create a clickable link as Rowan requested.
            // George I don't know if you already know so I am mentioning it anyways: <a th:href="@{${evidenceLink}}">View evidence for this stage</a>
            // This takes the user to the page with the correct answer information
        }
        
        return "index"; // I've structured it so that what George told me so it can be fed to the UI.
    }
}