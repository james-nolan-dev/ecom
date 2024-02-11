package me.nolanjames.ecommerce.service;

import jakarta.validation.Valid;
import me.nolanjames.ecommerce.entity.CartEntity;
import me.nolanjames.ecommerce.model.Item;

import java.util.List;

public interface CartService {
    public List<Item> addCartItemsByCustomerId(String customerId, @Valid Item item);

    public List<Item> addOrReplaceItemsByCustomerId(String customerId, @Valid Item item);

    public void deleteCart(String customerId);

    public void deleteItemFromCart(String customerId, String itemId);

    public CartEntity getCartByCustomerId(String customerId);

    public List<Item> getCartItemsByCustomerId(String customerId);

    public Item getCartItemsByItemId(String customerId, String itemId);
}
