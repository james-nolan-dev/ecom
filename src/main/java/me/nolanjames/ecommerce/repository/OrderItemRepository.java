package me.nolanjames.ecommerce.repository;

import me.nolanjames.ecommerce.entity.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, UUID> {
}
