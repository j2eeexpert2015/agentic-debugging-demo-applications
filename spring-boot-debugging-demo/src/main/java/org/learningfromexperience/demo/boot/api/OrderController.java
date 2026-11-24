package org.learningfromexperience.demo.boot.api;

import org.learningfromexperience.demo.boot.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @PostMapping("/discount-quote")
    public OrderQuote discountQuote(@RequestBody OrderRequest request) {
        return orderService.calculateDiscountQuote(request);
    }

    @PostMapping("/shipping-quote")
    public OrderQuote shippingQuote(@RequestBody OrderRequest request) {
        return orderService.calculateShippingQuote(request);
    }
}
