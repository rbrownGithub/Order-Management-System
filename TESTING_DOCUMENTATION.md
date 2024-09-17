Documentation for the Project
Chosen Methodology: Test-Driven Development (TDD)

The project followed the Test-Driven Development (TDD) methodology. TDD is a software development process where tests are written before the actual implementation code. The cycle consists of writing a failing test, implementing code to make the test pass, and then refactoring the code as needed.

In this project:

Tests for CRUD operations on orders and products were written first.
The implementation was then developed to satisfy these tests.
The cycle continued until all functionalities were implemented and tested.
Test Cases Written

Several test cases were created to ensure the functionality of the order management system. The test cases cover different scenarios, such as:

Unit Tests:
OrderServiceTest:
updateOrder_Success: Verifies that an order is successfully updated with valid data.
updateOrder_OrderNotFound: Verifies that an exception is thrown when trying to update an order that does not exist.
updateOrder_InvalidData: Verifies that an exception is thrown when the provided data for the update is invalid.
ProductServiceTest:
Similar tests for CRUD operations on products (e.g., creating a product, updating a product, handling cases where the product is not found).
Integration Test:
OrderProductIntegrationTest:
testCreateOrderWithProducts: Tests the end-to-end functionality of creating an order with multiple products, ensuring that all services work together correctly.
Test Results

All tests were run to ensure the functionality of the application. The results were:

Unit Test Results:

All tests (OrderServiceTest and ProductServiceTest) were executed and passed successfully.
Output: Process finished with exit code 0
Integration Test Results:

The integration test (OrderProductIntegrationTest) was executed and passed successfully.
Output: Process finished with exit code 0
These results indicate that the application is working as expected, with all functionalities correctly implemented and tested.

