package org.learningfromexperience.demo.boot.service;

import org.learningfromexperience.demo.boot.api.FulfillmentReceipt;
import org.learningfromexperience.demo.boot.api.FulfillmentRequest;
import org.springframework.stereotype.Service;

@Service
public class FulfillmentService {
    private final SkuNormalizer normalizer;
    private final SkuValidator validator;

    public FulfillmentService(SkuNormalizer normalizer, SkuValidator validator) {
        this.normalizer = normalizer;
        this.validator = validator;
    }

    public FulfillmentReceipt validate(FulfillmentRequest request) {
        if (request.quantity() <= 0) throw new IllegalArgumentException("Quantity must be positive");
        String normalizedSku = normalizer.normalize(request.sku());
        validator.validate(normalizedSku);
        return new FulfillmentReceipt(normalizedSku, request.quantity(), "ACCEPTED");
    }
}
