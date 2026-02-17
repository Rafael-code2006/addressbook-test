package com.example.TestsAddressbook.connections;



import javax.persistence.*;

@Entity
@Table(name = "preview")
public class Preview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "image_url")
    private String image_url;

    @OneToOne
    @JoinColumn(name = "video_id")
    private Video video;


    public Preview(int id, String image_url, Video video) {
        this.id = id;
        this.image_url = image_url;
        this.video = video;
    }


    public Preview( String image_url) {
        this.image_url = image_url;
    }

    public Preview(){}

    public int getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }

    public Video getVideo() {
        return video;
    }

    @Override
    public String toString() {
        return "Preview{" +
                "id=" + id +
                ", image_url='" + image_url + '\'' +
                ", video=" + video.getName() +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Preview setVideo(Video video) {
        this.video = video;
        if(video.getPreview() != null){
            video.setPreview(this);
        }
        return this;
    }
}
