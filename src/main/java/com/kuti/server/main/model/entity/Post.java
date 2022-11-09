package com.kuti.server.main.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "POSTS")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "lastModificationDate")
    private LocalDateTime lastModificationDate;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @Column(name = "ownerId")
    private int ownerId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ownerObject", nullable = false)
    private User ownerObject;
}
