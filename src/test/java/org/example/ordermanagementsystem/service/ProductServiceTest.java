package org.example.ordermanagementsystem.service;

import org.example.ordermanagementsystem.entity.Product;
import org.example.ordermanagementsystem.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void getAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = productService.getProductById(productId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(productId, result.get().getId());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void createProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Product result = productService.createProduct(product);

        // Assert
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals(10.0, result.getPrice());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void updateProduct() {
        // Arrange
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Old Name");
        existingProduct.setPrice(10.0);

        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName("New Name");
        updatedProduct.setPrice(20.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        // Act
        Product result = productService.updateProduct(productId, updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals(20.0, result.getPrice());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void deleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }
}
