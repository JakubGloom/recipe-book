package com.gluma.recepiebook.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipes"}) //bc we have bidirectional references, it make endless loop
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
