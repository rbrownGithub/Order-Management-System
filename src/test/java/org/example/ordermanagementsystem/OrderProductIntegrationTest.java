package org.example.ordermanagementsystem;

import org.example.ordermanagementsystem.entity.Order;
import org.example.ordermanagementsystem.entity.Product;
import org.example.ordermanagementsystem.service.OrderService;
import org.example.ordermanagementsystem.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderProductIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Test
    void testCreateOrderWithProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(50.0);
        productService.createProduct(product1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(100.0);
        productService.createProduct(product2);

        Order order = new Order();
        order.setStatus("Pending");
        order.setTotalAmount(150.0);
        order.setProducts(Arrays.asList(product1, product2));

        // Act
        Order savedOrder = orderService.createOrder(order);

        // Assert
        assertNotNull(savedOrder.getId());
        assertEquals(2, savedOrder.getProducts().size());
        assertEquals(150.0, savedOrder.getTotalAmount());
    }
}
