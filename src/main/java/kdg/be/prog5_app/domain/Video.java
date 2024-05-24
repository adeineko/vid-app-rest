package kdg.be.prog5_app.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;
    @Column(name = "views")
    private int views;
    @Column(name = "link")
    private String link;
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private VideoGenre genre;

    @OneToMany(mappedBy = "video")
    private List<ChannelVideo> revenues;

    public Video() {
    }

    public Video(String title, int views, String link, VideoGenre genre) {
        this.title = title;
        this.views = views;
        this.link = link;
        this.genre = genre;
    }

    public Video(int id, String title, int views, String link, VideoGenre genre) {
        this.id = id;
        this.title = title;
        this.views = views;
        this.link = link;
        this.genre = genre;
    }

    public Video(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public VideoGenre getGenre() {
        return genre;
    }

    public void setGenre(VideoGenre genre) {
        this.genre = genre;
    }

    public List<ChannelVideo> getRevenues() {
        return revenues;
    }

    public void setRevenues(List<ChannelVideo> revenues) {
        this.revenues = revenues;
    }
}
