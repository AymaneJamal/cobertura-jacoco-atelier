package com.testing.cobertura;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

class OrderServiceTest {
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void testProcessOrderValid() {
        Order order = new Order("P001", 10, 20.0, "C001");
        Map<String, Object> result = orderService.processOrder(order);

        assertNotNull(result.get("orderId"));
        assertEquals(200.0, result.get("originalTotal"));
        assertTrue((double) result.get("finalTotal") < 200.0);
    }

    @Test
    void testProcessOrderInvalidProduct() {
        Order invalidOrder = new Order("", 10, 20.0, "C001");

        Exception exception = assertThrows(OrderProcessingException.class, () -> {
            orderService.processOrder(invalidOrder);
        });

        assertEquals("Invalid product ID", exception.getMessage());
    }

    @Test
    void testProcessOrderQuantityDiscount() {
        Order order = new Order("P001", 10, 20.0, "C002");
        Map<String, Object> result = orderService.processOrder(order);

        @SuppressWarnings("unchecked")
        List<String> appliedDiscounts = (List<String>) result.get("appliedDiscounts");

        assertTrue(appliedDiscounts.contains("BULK_DISCOUNT"));
    }

    @Test
    void testVIPCustomerDiscount() {
        Order order = new Order("P001", 1, 2000.0, "VIP001");
        orderService.processOrder(order); // First order to make customer VIP

        Order secondOrder = new Order("P001", 1, 100.0, "VIP001");
        Map<String, Object> result = orderService.processOrder(secondOrder);

        @SuppressWarnings("unchecked")
        List<String> appliedDiscounts = (List<String>) result.get("appliedDiscounts");

        assertTrue(appliedDiscounts.contains("VIP_DISCOUNT"));
        assertTrue((Boolean) result.get("isVipCustomer"));
    }
}