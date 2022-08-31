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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
/**
 **
 */
public class ParentBlogCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parent_category_id")
    private Long id;

    @Column
    private String label;

    @Column
    private boolean isPrimary;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ChildBlogCategory> childCategories;

}
