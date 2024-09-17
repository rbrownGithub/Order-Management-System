package org.example.ordermanagementsystem.service;

import org.example.ordermanagementsystem.entity.Order;
import org.example.ordermanagementsystem.exception.InvalidOrderException;
import org.example.ordermanagementsystem.exception.OrderNotFoundException;
import org.example.ordermanagementsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));

        // Validate total amount
        if (orderDetails.getTotalAmount() < 0) {
            throw new InvalidOrderException("Total amount cannot be negative");
        }

        // Update fields
        existingOrder.setStatus(orderDetails.getStatus());
        existingOrder.setTotalAmount(orderDetails.getTotalAmount());
        // Update other fields as necessary

        return orderRepository.save(existingOrder);
    }



    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}