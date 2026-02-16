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
            //IMPORTANT - write example question here
            questionText = "";
            //mission.getQuestion();

            //IMPORTANT - write example answer here
            correctAnswer = "";
            //mission.getAnswer();
            //IMPORTANT - write the link below for evidence
            evidenceLink = "";
            //mission.getExplanation();
            

            model.addAttribute("questionGenerated", true);
        } else {
            model.addAttribute("questionGenerated", false);
        }
        
        // IMPORTANT: Add to model for George's UI
        
        model.addAttribute("questionText", questionText);
        model.addAttribute("correctAnswer", correctAnswer);
        model.addAttribute("evidenceLink", evidenceLink);

        return "index";
    }

    @PostMapping("/verify")
    public String verifyAnswer(
            @RequestParam String userAnswer,
            @RequestParam String correctAnswer,
            @RequestParam String evidenceLink,
            Model model) {
        
        boolean isCorrect = userAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("userAnswer", userAnswer);
        model.addAttribute("correctAnswer", correctAnswer);
        model.addAttribute("evidenceLink", evidenceLink);
        
        if (isCorrect) {
            model.addAttribute("resultMessage", "Correct!");
            model.addAttribute("feedback", "Your answer matches our traceability records.");
        } else {
            model.addAttribute("resultMessage", "Incorrect");
            model.addAttribute("feedback", "The correct traceability value is shown below.");
        }
        
        return "answer";
    }
}