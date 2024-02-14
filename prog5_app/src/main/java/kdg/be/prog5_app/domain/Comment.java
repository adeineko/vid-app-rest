package kdg.be.prog5_app.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String text;
    @Column
    private int likes;
    //    private List<Comment> replies;
    @OneToMany
    private List<Video> videos;

//    public Comment(String text) {
//        this.text = text;
//    }

    public Comment() {
    }

    public Comment(long id, String text, int likes, List<Video> videos) {
        this.id = id;
        this.text = text;
        this.likes = likes;
        this.videos = videos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
