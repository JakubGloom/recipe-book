package com.gluma.recepiebook.repositories;

import com.gluma.recepiebook.domain.Category;
import com.gluma.recepiebook.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
