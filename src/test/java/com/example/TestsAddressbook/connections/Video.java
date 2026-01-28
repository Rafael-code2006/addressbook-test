package com.example.TestsAddressbook.connections;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Видео имеет id

    @Column(name = "name") // Берем имя по колонке name
    private String name;

    @Column(name = "author_id") // Берем id автора по колонке author_id
    private int author_id;

    @OneToOne(mappedBy = "video", cascade = CascadeType.ALL)// У видео есть одна связующая таблица с превью
    private Preview preview;

    @OneToMany(mappedBy = "video") //  У одного видео есть много комментариев
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "video_viewer",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    ) // Это видео посмотрело много пользователей
    private List<User> viewers;


    public Video(int id, String name, int author_id, Preview preview) {
        this.id = id;
        this.name = name;
        this.author_id = author_id;
        this.preview = preview;
    }

    @Ignore
    public Video( String name, int author_id) {
        this.name = name;
        this.author_id = author_id;
    }

    public Video(){}


    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author_id=" + author_id +
                ", previewId=" + (preview != null ? preview.getId() : null) +
                ", commentsCount=" + (comments != null ? comments.size() : 0) +
                ", viewersCount=" + (viewers != null ? viewers.size() : 0) +
                '}';
    }


    public List<User> getViewers() {
        return viewers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public Preview getPreview() {
        return preview;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setViewers(List<User> viewers) {
        this.viewers = viewers;
    }
}
