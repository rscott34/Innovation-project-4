package Group4.tracer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import Group4.tracer.model.Products;
import Group4.tracer.model.Stages;
import Group4.tracer.repository.ProductRepository;
import Group4.tracer.repository.StageRepository;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL",
    "spring.datasource.driverClassName=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.sql.init.mode=never"
})
class TracerApplicationTests {

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUpTestData() {
        stageRepository.deleteAll();
        productRepository.deleteAll();

        Products product = new Products("P001", "Test Product", "Food", "Test Brand", "Test Description");
        productRepository.save(product);

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

        stageRepository.save(stage1);
        stageRepository.save(stage2);
    }

    @Test
    void contextLoads() {
        assertNotNull(stageRepository);
        assertNotNull(productRepository);
    }

    private String findExistingProductId() {
        List<Products> allProducts = productRepository.findAll();

        assertNotNull(allProducts);
        assertFalse(allProducts.isEmpty(), "Expected seeded product data in the test database.");

        for (Products product : allProducts) {
            if (product != null && product.getProductId() != null) {
                List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc(product.getProductId());
                if (stages != null && !stages.isEmpty()) {
                    return product.getProductId();
                }
            }
        }

        fail("Expected at least one seeded product with timeline stages in the test database.");
        return null;
    }

    @Test
    void testFindStagesByProductId() {
        String productId = findExistingProductId();

        List<Stages> stages = stageRepository.findByProductIdOrderByStageIdAsc(productId);

        assertNotNull(stages);
        assertFalse(stages.isEmpty(), "Stages should not be empty");

        for (int i = 1; i < stages.size(); i++) {
            assertTrue(
                stages.get(i - 1).getStageId().compareTo(stages.get(i).getStageId()) <= 0,
                "Stages are not ordered correctly"
            );
        }
    }
}
