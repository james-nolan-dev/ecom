package me.nolanjames.ecommerce.repository;

import me.nolanjames.ecommerce.entity.OrderEntity;
import me.nolanjames.ecommerce.model.NewOrder;

import java.util.Optional;

public interface OrderRepositoryExt {
    Optional<OrderEntity> insert(NewOrder newOrder);
}
