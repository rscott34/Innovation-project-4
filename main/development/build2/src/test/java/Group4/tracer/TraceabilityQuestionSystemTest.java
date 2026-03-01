// package Group4.tracer;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import org.junit.jupiter.api.Test;

// class TraceabilityQuestionSystemTest {

//     TraceabilityQuestionSystem system = new TraceabilityQuestionSystem();

//     @Test
//     void testGetProductById_ValidId() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         assertNotNull(product);
//         assertEquals("Olive Oil", product.getName());
//     }

//     @Test
//     void testGetProductById_InvalidId() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("999");

//         assertNull(product);
//     }

//     @Test
//     void testGenerateQuestion_Format() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         String topic = "processing";
//         String question = system.generateQuestion(product, topic);

//         assertTrue(question.contains("processing"));
//         assertTrue(question.contains("Olive Oil"));
//         assertTrue(question.contains("001"));
//     }

//     @Test
//     void testVerifyAnswer_Correct() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         boolean result = system.verifyAnswer(
//                 product,
//                 "processing",
//                 "Cold pressing and filtering"
//         );

//         assertTrue(result);
//     }

//     @Test
//     void testVerifyAnswer_Incorrect() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         boolean result = system.verifyAnswer(
//                 product,
//                 "processing",
//                 "Wrong Answer"
//         );

//         assertFalse(result);
//     }

//     @Test
//     void testRandomlySelectProduct_NotNull() {
//         TraceabilityQuestionSystem.Product product =
//                 system.randomlySelectProduct();

//         assertNotNull(product);
//     }

//     @Test
//     void testRandomlyGenerateTopic_NotNull() {
//         String topic = system.randomlyGenerateTopic();
//         assertNotNull(topic);
//     }

//     @Test
//     void testProductHasAllFiveTraceabilityStages() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         assertNotNull(product.getRawMaterials());
//         assertNotNull(product.getProcessing());
//         assertNotNull(product.getAssembly());
//         assertNotNull(product.getTransport());
//         assertNotNull(product.getRetail());
//     }

//     @Test
//     void testVerifyAnswer_IsCaseInsensitive() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         boolean result = system.verifyAnswer(
//                 product,
//                 "processing",
//                 "cold pressing and filtering"
//         );

//         assertTrue(result);
//     }

//     @Test
//     void testGenerateQuestion_NotNull() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         String question = system.generateQuestion(product, "assembly");

//         assertNotNull(question);
//         assertFalse(question.isEmpty());
//     }

//     @Test
//     void testRandomlySelectProduct_MultipleCallsNotNull() {
//         for (int i = 0; i < 5; i++) {
//             TraceabilityQuestionSystem.Product product =
//                     system.randomlySelectProduct();
//             assertNotNull(product);
//         }
//     }

//     @Test
//     void testInvalidTopicReturnsFalse() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         boolean result = system.verifyAnswer(
//                 product,
//                 "invalidTopic",
//                 "Some Answer"
//         );

//         assertFalse(result);
//     }

//     @Test
//     void testAllProductsAccessible() {
//         // Verify all defined product IDs exist
//         assertNotNull(system.getProductById("001"));
//         assertNotNull(system.getProductById("002"));
//         assertNotNull(system.getProductById("003"));
//         assertNotNull(system.getProductById("004"));
//         assertNotNull(system.getProductById("005"));
//     }

//     @Test
//     void testAllTopicsReturnNonNullTraceabilityValue() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         String[] topics = {
//                 "raw materials",
//                 "processing",
//                 "assembly",
//                 "transport",
//                 "retail"
//         };

//         for (String topic : topics) {
//             String value = product.getTraceabilityValue(topic);
//             assertNotNull(value);
//             assertFalse(value.isEmpty());
//         }
//     }

//     @Test
//     void testVerifyAnswer_TrimsWhitespace() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         boolean result = system.verifyAnswer(
//                 product,
//                 "processing",
//                 "  Cold pressing and filtering  "
//         );

//         assertTrue(result);
//     }

//     @Test
//     void testGetAnswerResult_CorrectContainsSuccessMessage() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         String result = system.getAnswerResult(
//                 product,
//                 "processing",
//                 "Cold pressing and filtering"
//         );

//         assertTrue(result.contains("Correct"));
//         assertTrue(result.contains("Olive Oil"));
//     }

//     @Test
//     void testGetAnswerResult_IncorrectContainsCorrectAnswer() {
//         TraceabilityQuestionSystem.Product product =
//                 system.getProductById("001");

//         String result = system.getAnswerResult(
//                 product,
//                 "processing",
//                 "Wrong Answer"
//         );

//         assertTrue(result.contains("Incorrect"));
//         assertTrue(result.contains("Cold pressing and filtering"));
//         assertTrue(result.contains("Evidence"));
//     }
// }