package org.example.ordermanagementsystem.service;

import org.example.ordermanagementsystem.entity.Order;
import org.example.ordermanagementsystem.exception.InvalidOrderException;
import org.example.ordermanagementsystem.exception.OrderNotFoundException;
import org.example.ordermanagementsystem.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        // Initialization is handled by MockitoExtension
    }

    @AfterEach
    void tearDown() throws Exception {
        // No need to close resources when using MockitoExtension
    }

    @Test
    void updateOrder_Success() {
        // Arrange
        Long orderId = 1L;
        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setStatus("Pending");
        existingOrder.setTotalAmount(100.0);

        Order updatedOrderDetails = new Order();
        updatedOrderDetails.setStatus("Shipped");
        updatedOrderDetails.setTotalAmount(150.0);

        Order updatedOrder = new Order();
        updatedOrder.setId(orderId);
        updatedOrder.setStatus("Shipped");
        updatedOrder.setTotalAmount(150.0);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(updatedOrder);

        // Act
        Order result = orderService.updateOrder(orderId, updatedOrderDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Shipped", result.getStatus());
        assertEquals(150.0, result.getTotalAmount());
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void updateOrder_OrderNotFound() {
        // Arrange
        Long orderId = 999L;
        Order updatedOrderDetails = new Order();
        updatedOrderDetails.setStatus("Shipped");
        updatedOrderDetails.setTotalAmount(150.0);

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrder(orderId, updatedOrderDetails));
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void updateOrder_InvalidData() {
        // Arrange
        Long orderId = 1L;
        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setStatus("Pending");
        existingOrder.setTotalAmount(100.0);

        Order updatedOrderDetails = new Order();
        updatedOrderDetails.setStatus("Shipped");
        updatedOrderDetails.setTotalAmount(-150.0); // Invalid total amount

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));

        // Act & Assert
        assertThrows(InvalidOrderException.class, () -> orderService.updateOrder(orderId, updatedOrderDetails));
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, never()).save(any(Order.class));
    }

    // Additional test methods for other CRUD operations and edge cases
}
