package me.nolanjames.ecommerce.repository;

import me.nolanjames.ecommerce.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardRepository extends CrudRepository<CardEntity, UUID> {
}
