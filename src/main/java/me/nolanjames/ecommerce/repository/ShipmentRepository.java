package me.nolanjames.ecommerce.repository;

import me.nolanjames.ecommerce.entity.ShipmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShipmentRepository extends CrudRepository<ShipmentEntity, UUID> {
}
