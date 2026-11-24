package org.learningfromexperience.demo.boot.service;

import org.springframework.stereotype.Service;

@Service
public class SkuNormalizer {
    public String normalize(String sku) {
        if (sku == null) return "";
        return sku.strip().toUpperCase().replace("-", "");
    }
}
