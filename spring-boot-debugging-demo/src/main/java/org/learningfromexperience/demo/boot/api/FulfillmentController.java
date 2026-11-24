package org.learningfromexperience.demo.boot.api;

import org.learningfromexperience.demo.boot.service.FulfillmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fulfillment")
public class FulfillmentController {
    private final FulfillmentService fulfillmentService;
    public FulfillmentController(FulfillmentService fulfillmentService) {
        this.fulfillmentService = fulfillmentService;
    }

    @PostMapping("/validate")
    public FulfillmentReceipt validate(@RequestBody FulfillmentRequest request) {
        return fulfillmentService.validate(request);
    }
}
