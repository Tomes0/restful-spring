package com.kuti.server.main.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="PICTURES")
public class Picture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pictureId;

    @Lob
    private String bytea;

    @Column(name = "pictureExtension")
    private String extension;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ownerObject", nullable = false)
    private User ownerObject;

    @Column(name = "ownerId")
    private int ownerId;

}
