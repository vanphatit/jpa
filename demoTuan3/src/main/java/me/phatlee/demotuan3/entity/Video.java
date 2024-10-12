package me.phatlee.demotuan3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
@NamedQuery(name = "Video.findAll", query = "SELECT c FROM Video c")
public class Video implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videoID")
    private int videoID;

    @Column(name = "title", columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(name = "posterURL", columnDefinition = "NVARCHAR(500)")
    private String posterURL;

    @Column(name = "description", columnDefinition = "NVARCHAR(500)")
    private String description;

    @Column(name = "views")
    private int views;

    @Column(name = "active")
    private int active;

    @Column(name = "videoURL", columnDefinition = "NVARCHAR(500)")
    private String videoURL;
}