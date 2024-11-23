package com.testing.cobertura;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OrderService {
    private static final double VIP_THRESHOLD = 1000.0;
    private static final double BULK_DISCOUNT_THRESHOLD = 5;
    private Map<String, Double> customerBalance = new HashMap<>();
    private Set<String> vipCustomers = new HashSet<>();

    /**
     * Processes an order with various business rules:
     * - Validates input data
     * - Applies different discount rules based on quantity and customer status
     * - Handles customer balance
     * - Updates VIP status based on order total
     * - Returns final order details with applied discounts
     *
     * @param order The order to process
     * @return Map containing processed order details
     * @throws OrderProcessingException if validation fails
     */


    public Map<String, Object> processOrder(Order order) {
        // Input validation
        validateOrder(order);

        double totalBeforeDiscount = calculateBaseTotal(order);
        double finalDiscount = 0.0;
        List<String> appliedDiscounts = new ArrayList<>();

        // Remise quantité
        if (order.getQuantity() >= BULK_DISCOUNT_THRESHOLD) {
            finalDiscount += 0.1; // 10% remise
            appliedDiscounts.add("BULK_DISCOUNT");
        }

        // Remise VIP
        if (vipCustomers.contains(order.getCustomerId())) {
            finalDiscount += 0.05; // 5% additionnel pour VIP
            appliedDiscounts.add("VIP_DISCOUNT");
        }

        // Remise saisonnière
        if (isSeasonalProduct(order.getProductId())) {
            finalDiscount += 0.15; // 15% remise saisonnière
            appliedDiscounts.add("SEASONAL_DISCOUNT");
        }

        // Les remises s'additionnent simplement
        double finalTotal = totalBeforeDiscount * (1 - finalDiscount);

        // Update customer balance
        updateCustomerBalance(order.getCustomerId(), finalTotal);

        // Check and update VIP status
        checkAndUpdateVipStatus(order.getCustomerId());

        // Prepare response
        Map<String, Object> result = new HashMap<>();
        result.put("orderId", UUID.randomUUID().toString());
        result.put("originalTotal", totalBeforeDiscount);
        result.put("finalTotal", finalTotal);
        result.put("totalDiscount", finalDiscount * 100 + "%");
        result.put("appliedDiscounts", appliedDiscounts);
        result.put("customerBalance", customerBalance.get(order.getCustomerId()));
        result.put("isVipCustomer", vipCustomers.contains(order.getCustomerId()));

        return result;
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new OrderProcessingException("Order cannot be null");
        }
        if (order.getProductId() == null || order.getProductId().trim().isEmpty()) {
            throw new OrderProcessingException("Invalid product ID");
        }
        if (order.getQuantity() <= 0) {
            throw new OrderProcessingException("Quantity must be positive");
        }
        if (order.getPrice() <= 0) {
            throw new OrderProcessingException("Price must be positive");
        }
        if (order.getCustomerId() == null || order.getCustomerId().trim().isEmpty()) {
            throw new OrderProcessingException("Invalid customer ID");
        }
    }

    private double calculateBaseTotal(Order order) {
        return order.getQuantity() * order.getPrice();
    }

    private boolean isSeasonalProduct(String productId) {
        // Example: Products starting with 'S' are seasonal
        return productId.startsWith("S");
    }

    private void updateCustomerBalance(String customerId, double amount) {
        customerBalance.merge(customerId, amount, Double::sum);
    }

    private void checkAndUpdateVipStatus(String customerId) {
        double totalSpent = customerBalance.getOrDefault(customerId, 0.0);
        if (totalSpent >= VIP_THRESHOLD) {
            vipCustomers.add(customerId);
        }
    }
}
