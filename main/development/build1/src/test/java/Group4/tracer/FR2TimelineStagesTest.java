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

import Group4.tracer.model.Stages;
import Group4.tracer.repository.StageRepository;


@SpringBootTest
@ActiveProfiles("test")
class FR2TimelineStagesTest {

    @Autowired
    private StageRepository stageRepository;

    @Test
    void contextLoads() {
        assertNotNull(stageRepository);
    }

    @Test
    void fr2_validProductIdReturnsTimelineStages() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());
    }

    @Test
    void fr2_invalidProductIdReturnsNoTimelineStages() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("BAD_ID");

        assertNotNull(stages);
        assertTrue(stages.isEmpty());
    }

    @Test
    void fr2_returnedStagesBelongToRequestedProduct() {
        String productId = "P001";
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc(productId);

        assertNotNull(stages);
        assertFalse(stages.isEmpty());

        for (Stages stage : stages) {
            assertNotNull(stage);
            assertNotNull(stage.getProductId());
            assertEquals(productId, stage.getProductId());
        }
    }

    @Test
    void fr2_returnedStagesAreOrderedByStageIdAscending() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());

        for (int i = 0; i < stages.size() - 1; i++) {
            String currentStageId = stages.get(i).getStageId();
            String nextStageId = stages.get(i + 1).getStageId();

            assertNotNull(currentStageId);
            assertNotNull(nextStageId);
            assertTrue(currentStageId.compareTo(nextStageId) <= 0);        }
    }

    @Test

    void fr2_stageTimelineFieldsArePopulated() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());

        for (Stages stage : stages) {
            assertNotNull(stage);
            assertNotNull(stage.getStageId());
            assertNotNull(stage.getProductId());
        }
    }

    @Test
    void fr2_returnedStagesContainNoNullObjects() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());

        for (Stages stage : stages) {
            assertNotNull(stage);
        }
    }

    @Test
    void fr2_sameProductQueryReturnsConsistentResults() {
        List<Stages> firstCall = stageRepository.findByProductIdOrderByStageIdAsc("P001");
        List<Stages> secondCall = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(firstCall);
        assertNotNull(secondCall);
        assertEquals(firstCall.size(), secondCall.size());
    }

    @Test
    void fr2_firstReturnedStageHasSmallestStageId() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());
        assertNotNull(stages.get(0).getStageId());

        String firstStageId = stages.get(0).getStageId(); //changed data type int to str - W
        for (Stages stage : stages) {
            assertNotNull(stage.getStageId());
            assertTrue(firstStageId.compareTo(stage.getStageId()) <= 0);
        }
    }
}