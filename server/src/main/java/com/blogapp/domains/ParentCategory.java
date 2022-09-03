package com.blogapp.domains;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parent_categories",
        uniqueConstraints = { @UniqueConstraint(name = "parentLabel", columnNames = {"label"} )})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ParentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parent_category_id")
    private Long id;

    @Column
    private String label;

    @Column(name = "is_primary")
    private boolean isPrimary;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<SubCategory> subCategories;

}
