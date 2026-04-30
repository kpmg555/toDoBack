package org.acme.infrastructure.mapper;

import org.acme.domain.models.Todo;
import org.acme.infrastructure.entities.CategoryEntity;
import org.acme.infrastructure.entities.CommentEntity;
import org.acme.infrastructure.entities.TodoEntity;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.stream.Collectors;

public class TodoMapper {

    public static Todo toDomain(TodoEntity entity) {
        Todo todo = new Todo();
        todo.setId(entity.getId());
        todo.setTitle(entity.getTitle());
        todo.setDescription(entity.getDescription());
        todo.setCompleted(entity.isCompleted());
        todo.setCreatedAt(entity.getCreatedAt());

        if (entity.getOwner() != null && Hibernate.isInitialized(entity.getOwner())) {
            todo.setOwner(UserMapper.toDomain(entity.getOwner()));
        }

        if (Hibernate.isInitialized(entity.getCategories())) {
            todo.setCategories(
                    entity.getCategories().stream()
                            .map(CategoryMapper::toDomain)
                            .collect(Collectors.toCollection(HashSet::new))
            );
        }

        if (Hibernate.isInitialized(entity.getComments())) {
            todo.setComments(
                    entity.getComments().stream()
                            .map(CommentMapper::toDomain)
                            .collect(Collectors.toList())
            );
        }

        return todo;
    }

    public static TodoEntity toEntity(Todo todo) {
        TodoEntity entity = new TodoEntity();
        entity.setId(todo.getId());
        entity.setTitle(todo.getTitle());
        entity.setDescription(todo.getDescription());
        entity.setCompleted(todo.isCompleted());
        entity.setCreatedAt(todo.getCreatedAt());
        return entity;
    }
}
