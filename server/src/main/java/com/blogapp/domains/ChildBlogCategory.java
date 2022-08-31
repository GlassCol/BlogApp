package com.blogapp.domains;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ChildBlogCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "child_category_id", nullable = false)
    private Long id;

    @Column
    private String label;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", nullable = false)
    @ToString.Exclude
    private ParentBlogCategory parent;

}
