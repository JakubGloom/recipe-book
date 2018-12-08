package com.gluma.recepiebook.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

//    @javax.persistence.Lob signifies that the annotated field should be represented as BLOB (binary data) in the DataBase.
//    You can annotated any serializable data type with this annotation. In JPA, upon persisting (retrieval)
//    the field content will be serialized (deserialized) using standard Java serialization.
//    Common use of LOB is to annotate a HashMap field inside your Entity to store some of the object
//    properties which are not mapped into DB columns. That way all the unmapped values can be stored
//    in the DB in one column in their binarry representation. Of course the price that is paid is that,
//    as they are stored in binary format, they are not searchable using the JPQL/SQL
    @Lob
    private String recipeNotes;


}
