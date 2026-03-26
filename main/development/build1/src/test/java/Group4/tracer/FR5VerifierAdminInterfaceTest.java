package Group4.tracer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import Group4.tracer.controller.TracerController;
import Group4.tracer.model.issueReport;
import Group4.tracer.repository.ChangeLogRepository;
import Group4.tracer.repository.IssueRepository;

@SpringBootTest
@ActiveProfiles("test")
class FR5VerifierAdminInterfaceTest {

    @Autowired
    private TracerController tracerController;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ChangeLogRepository changeLogRepository;

    @Test
    void fr5_reportPageLoadsWithProductId() {
        Model model = new ExtendedModelMap();

        String viewName = tracerController.showReportPage("P001", model);

        assertEquals("report-issue", viewName);
        assertTrue(model.containsAttribute("productId"));
        assertEquals("P001", model.getAttribute("productId"));
        assertTrue(model.containsAttribute("allStages"));
    }

    @Test
    void fr5_submitIssue_savesReport() {
        RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();

        String result = tracerController.processReport(
                "P001",
                "S1",
                "Wrong Data",
                "Test issue",
                redirectAttributes
        );

        assertEquals("redirect:/", result);

        List<issueReport> reports = issueRepository.findAll();
        assertFalse(reports.isEmpty());

        issueReport report = reports.get(reports.size() - 1);
        assertEquals("P001", report.getProductId());
        assertEquals("S1", report.getStageId());
        assertEquals("Wrong Data", report.getIssueType());
        assertEquals("Test issue", report.getUserDescription());
    }

    @Test
    void fr5_verifierInboxDisplaysReports() {
        issueReport report = new issueReport();
        report.setProductId("P001");
        report.setStageId("S1");
        report.setIssueType("Test");
        report.setUserDescription("Inbox test");
        issueRepository.save(report);

        Model model = new ExtendedModelMap();

        String viewName = tracerController.showVerifierInbox(model);

        assertEquals("verifier-inbox", viewName);
        assertTrue(model.containsAttribute("reports"));
    }

    @Test
    void fr5_resolveIssue_updatesStatus() {
        issueReport report = new issueReport();
        report.setProductId("P001");
        report.setStageId("S1");
        report.setIssueType("Test");
        report.setUserDescription("Resolve test");
        report = issueRepository.save(report);

        String result = tracerController.resolveIssue(report.getId());

        assertEquals("redirect:/verifier/inbox", result);

        issueReport updated = issueRepository.findById(report.getId()).orElse(null);
        assertNotNull(updated);
        assertEquals("RESOLVED", updated.getStatus());
    }

    @Test
    void fr5_historyLoadsLogs() {
        Model model = new ExtendedModelMap();

        String viewName = tracerController.viewHistory(model);

        assertEquals("history", viewName);
        assertTrue(model.containsAttribute("logs"));
    }
}