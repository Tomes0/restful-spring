package com.kuti.server.main.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

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
    private Date creationDate;

    @Column(name = "lastModificationDate")
    private Date lastModificationDate;

    @Column(name = "content")
    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private User ownerId;
}
