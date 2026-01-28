package com.example.TestsAddressbook.connections;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_person")
public class User {

    @Id
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "viewers")
    private List<Video> videos;

    public User(int id, String username, String email, List<Video> videos) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.videos = videos;
    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", videos=" + videos +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
