package me.nolanjames.ecommerce.repository;

import me.nolanjames.ecommerce.entity.AuthorizationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthorizationRepository extends CrudRepository<AuthorizationEntity, UUID> {
}
