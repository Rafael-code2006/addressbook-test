package com.example.TestsAddressbook.connections;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    private int id;
    @Column(name = "comment_text")
    private String comment_text;
    @Column(name = "commenter_id")
    private int commenter_id;
    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    public Comment(int id, String comment_text, int commenter_id, Video video) {
        this.id = id;
        this.comment_text = comment_text;
        this.commenter_id = commenter_id;
        this.video = video;
    }

    public Comment(){}

    @Override
    public String toString() {
        return "Comment{" +
                "video=" + video.getId() + " " + video.getName() +
                ", commenter_id=" + commenter_id +
                ", comment_text='" + comment_text + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public int getCommenter_id() {
        return commenter_id;
    }

    public Video getVideo() {
        return video;
    }
}
