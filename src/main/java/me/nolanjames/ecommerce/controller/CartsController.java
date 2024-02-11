package me.nolanjames.ecommerce.controller;

import jakarta.validation.Valid;
import me.nolanjames.ecommerce.CartApi;
import me.nolanjames.ecommerce.model.Cart;
import me.nolanjames.ecommerce.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

public class CartsController implements CartApi {
    private static final Logger log = LoggerFactory.getLogger(CartsController.class);

    @Override
    public ResponseEntity<List<Item>> addCartItemsByCustomerId(String customerId, @Valid Item item) {
        log.info("Request for customer ID: {}\nItem: {}", customerId, item);
        return ResponseEntity.ok(Collections.emptyList());
    }
}
