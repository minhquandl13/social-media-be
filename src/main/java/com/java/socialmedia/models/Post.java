package com.java.socialmedia.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String caption;
    private String image;
    private String video;

    @ManyToOne
    private User user;

    @OneToMany
    private List<User> liked = new ArrayList<>();
    private LocalDateTime createdAt;
}
