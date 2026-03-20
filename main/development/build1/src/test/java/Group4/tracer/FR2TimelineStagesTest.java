package Group4.tracer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import Group4.tracer.model.Products;
import Group4.tracer.model.Stages;
import Group4.tracer.repository.ProductRepository;
import Group4.tracer.repository.StageRepository;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:fr2testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL",
    "spring.datasource.driverClassName=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.sql.init.mode=never"
})
class FR2TimelineStagesTest {

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUpTestData() {
        stageRepository.deleteAll();
        productRepository.deleteAll();

        Products product1 = new Products(
            "P001",
            "Test Product 1",
            "Food",
            "Test Brand",
            "Test Description 1"
        );

        Products product2 = new Products(
            "P002",
            "Test Product 2",
            "Food",
            "Another Brand",
            "Test Description 2"
        );

        productRepository.save(product1);
        productRepository.save(product2);

        Stages stage1 = new Stages(
            "S001",
            "RawMaterials",
            "Brazil",
            "2024-01-01",
            "2024-01-05",
            "Raw material sourcing",
            "P001"
        );

        Stages stage2 = new Stages(
            "S002",
            "Processing",
            "UK",
            "2024-01-06",
            "2024-01-10",
            "Processing stage",
            "P001"
        );

        Stages stage3 = new Stages(
            "S003",
            "Transport",
            "France",
            "2024-01-11",
            "2024-01-12",
            "Transport stage",
            "P001"
        );

        stageRepository.save(stage1);
        stageRepository.save(stage2);
        stageRepository.save(stage3);
    }

    @Test
    void fr2_contextLoads() {
        assertNotNull(stageRepository);
        assertNotNull(productRepository);
    }

    @Test
    void fr2_validProductIdReturnsTimelineStages() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());
        assertEquals(3, stages.size());
    }

    @Test
    void fr2_invalidProductIdReturnsNoTimelineStages() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("999999");

        assertNotNull(stages);
        assertTrue(stages.isEmpty());
    }

    @Test
    void fr2_returnedStagesAreNotNull() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(stages);
        for (Stages stage : stages) {
            assertNotNull(stage);
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
            assertTrue(currentStageId.compareTo(nextStageId) <= 0);
        }
    }

    @Test
    void fr2_stageIdsArePopulated() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());

        for (Stages stage : stages) {
            assertNotNull(stage.getStageId());
            assertFalse(stage.getStageId().isBlank());
        }
    }

    @Test
    void fr2_sameProductQueryReturnsConsistentResults() {
        List<Stages> firstCall = stageRepository.findByProductIdOrderByStageIdAsc("P001");
        List<Stages> secondCall = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(firstCall);
        assertNotNull(secondCall);
        assertEquals(firstCall.size(), secondCall.size());

        for (int i = 0; i < firstCall.size(); i++) {
            assertEquals(firstCall.get(i).getStageId(), secondCall.get(i).getStageId());
        }
    }

    @Test
    void fr2_firstReturnedStageHasSmallestStageId() {
        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc("P001");

        assertNotNull(stages);
        assertFalse(stages.isEmpty());
        assertEquals("S001", stages.get(0).getStageId());
    }
}