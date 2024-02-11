package me.nolanjames.ecommerce.repository;

import me.nolanjames.ecommerce.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {
}
