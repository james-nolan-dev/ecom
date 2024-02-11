package me.nolanjames.ecommerce.repository;

import me.nolanjames.ecommerce.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
}
