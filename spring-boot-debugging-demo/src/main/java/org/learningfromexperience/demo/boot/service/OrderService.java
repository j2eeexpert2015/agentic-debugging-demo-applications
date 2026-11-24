package org.learningfromexperience.demo.boot.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.learningfromexperience.demo.boot.api.OrderQuote;
import org.learningfromexperience.demo.boot.api.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final DiscountCalculator discountCalculator;
    private final PricingService pricingService;
    private final ShippingService shippingService;

    public OrderService(DiscountCalculator discountCalculator, PricingService pricingService,
                        ShippingService shippingService) {
        this.discountCalculator = discountCalculator;
        this.pricingService = pricingService;
        this.shippingService = shippingService;
    }

    public OrderQuote calculateDiscountQuote(OrderRequest request) {
        BigDecimal subtotal = subtotal(request);
        BigDecimal discount = discountCalculator.calculateDiscount(subtotal, request.discountPercent());
        BigDecimal discounted = subtotal.subtract(discount);
        return new OrderQuote(subtotal, discount, discounted, money("0"), discounted);
    }

    public OrderQuote calculateShippingQuote(OrderRequest request) {
        BigDecimal merchandiseSubtotal = subtotal(request);
        BigDecimal discount = pricingService.calculateDiscount(merchandiseSubtotal, request.discountPercent());
        BigDecimal discountedSubtotal = merchandiseSubtotal.subtract(discount);
        BigDecimal shippingCharge = shippingService.calculateCharge(discountedSubtotal);
        BigDecimal finalTotal = discountedSubtotal.add(shippingCharge);
        return new OrderQuote(merchandiseSubtotal, discount, discountedSubtotal, shippingCharge, finalTotal);
    }

    private BigDecimal subtotal(OrderRequest request) {
        if (request.quantity() <= 0 || request.unitPrice() == null || request.unitPrice().signum() < 0
                || request.discountPercent() < 0 || request.discountPercent() > 100) {
            throw new IllegalArgumentException("Invalid order request");
        }
        return request.unitPrice().multiply(BigDecimal.valueOf(request.quantity()))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal money(String amount) { return new BigDecimal(amount).setScale(2); }
}
