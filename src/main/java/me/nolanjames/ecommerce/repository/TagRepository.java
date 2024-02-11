package me.nolanjames.ecommerce.repository;

import me.nolanjames.ecommerce.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TagRepository extends CrudRepository<TagEntity, UUID> {
}
