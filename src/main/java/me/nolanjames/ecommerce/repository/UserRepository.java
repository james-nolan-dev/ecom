package me.nolanjames.ecommerce.repository;

import me.nolanjames.ecommerce.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
