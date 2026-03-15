package Group4.tracer;

<<<<<<< HEAD
import org.junit.jupiter.api.Disabled;
=======
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

>>>>>>> 30e500a (Added FR2 traceability timeline tests)
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import Group4.tracer.model.Stages;
import Group4.tracer.repository.StageRepository;

@SpringBootTest
<<<<<<< HEAD
@Disabled
=======
@ActiveProfiles("test")
>>>>>>> 30e500a (Added FR2 traceability timeline tests)
class TracerApplicationTests {

    @Autowired
    private StageRepository stageRepository;

    @Test
    void contextLoads() {
        assertNotNull(stageRepository);
    }

    @Test
    void fr2_validProductIdReturnsTimelineStages() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("1");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());
    }

    @Test
    void fr2_invalidProductIdReturnsNoTimelineStages() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("99999");

        assertNotNull(stages);
        assertTrue(stages.isEmpty());
    }

    @Test
    void fr2_returnedStagesBelongToRequestedProduct() {
        String productId = "1";
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
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("1");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());

        for (int i = 0; i < stages.size() - 1; i++) {
            Integer currentStageId = stages.get(i).getStageId();
            Integer nextStageId = stages.get(i + 1).getStageId();

            assertNotNull(currentStageId);
            assertNotNull(nextStageId);
            assertTrue(currentStageId <= nextStageId);
        }
    }

    @Test
    void fr2_stageTimelineFieldsArePopulated() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("1");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());

        for (Stages stage : stages) {
            assertNotNull(stage.getStageId());
            assertNotNull(stage.getProductId());
            assertNotNull(stage.getStageName());
        }
    }

    @Test
    void fr2_returnedStagesContainNoNullObjects() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("1");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());

        for (Stages stage : stages) {
            assertNotNull(stage);
        }
    }

    @Test
    void fr2_sameProductQueryReturnsConsistentResults() {
        List<Stages> firstCall = stageRepository.findByProductIdOrderByStageIdAsc("1");
        List<Stages> secondCall = stageRepository.findByProductIdOrderByStageIdAsc("1");

        assertNotNull(firstCall);
        assertNotNull(secondCall);
        assertEquals(firstCall.size(), secondCall.size());
    }

    @Test
    void fr2_firstReturnedStageHasSmallestStageId() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("1");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());
        assertNotNull(stages.get(0).getStageId());

        Integer firstStageId = stages.get(0).getStageId();
        for (Stages stage : stages) {
            assertNotNull(stage.getStageId());
            assertTrue(firstStageId <= stage.getStageId());
        }
    }
}