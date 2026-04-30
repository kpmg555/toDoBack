package org.acme.infrastructure.mapper;

import org.acme.domain.models.Category;
import org.acme.infrastructure.entities.CategoryEntity;

public class CategoryMapper {

    public static Category toDomain(CategoryEntity entity) {
        if (entity == null) return null;
        return new Category(entity.getId(), entity.getName());
    }
}
