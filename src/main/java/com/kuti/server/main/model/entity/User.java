package com.kuti.server.main.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="USERS")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "pass")
    private String pass;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "email")
    private String email;

    @JsonManagedReference
    @Column(name = "pictureList")
    @OneToMany(mappedBy = "ownerId")
    private List<Picture> pictureList;

    @JsonManagedReference
    @Column(name = "postList")
    @OneToMany(mappedBy = "ownerObject", fetch = FetchType.LAZY)
    private List<Post> postList;
}
