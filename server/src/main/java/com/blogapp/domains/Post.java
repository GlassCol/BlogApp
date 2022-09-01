package com.blogapp.domains;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="body", columnDefinition = "TEXT")
    private String body;

    @Column(name="user_id")
    private Long userId;


//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", nullable = true)
//    @ToString.Exclude
//    private User user;


}
