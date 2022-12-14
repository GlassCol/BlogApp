package com.blogapp.photo.domain;

import com.blogapp.post.domain.Post;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photos"
//        uniqueConstraints = @UniqueConstraint(name = "image_url_uk", columnNames = {"image_url"})
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "title")
    private String title;

    // Options types will be images belonging to a post or user
    @Column(name = "image_type")
    @Value(value = "post")
    private String imageType;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "post_id")
    @JsonIncludeProperties(value = {"id"})
    @ToString.Exclude
    private Post post;

    public Photo(String imageUrl, String title, String imageType, LocalDateTime now, LocalDateTime now1) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.imageType = imageType;
        this.createdAt = now;
        this.updatedAt = now;
    }
}
