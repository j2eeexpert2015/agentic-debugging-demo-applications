package org.learningfromexperience.demo.boot.service;

import org.springframework.stereotype.Service;

@Service
public class SkuValidator {
    public void validate(String canonicalSku) {
        if (!canonicalSku.matches("SKU-\\d{3}")) {
            throw new InvalidSkuException("SKU must use the format SKU-123: " + canonicalSku);
        }
    }
}
