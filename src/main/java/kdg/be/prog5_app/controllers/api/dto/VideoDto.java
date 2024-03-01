package kdg.be.prog5_app.controllers.api.dto;


import kdg.be.prog5_app.domain.VideoGenre;

public class VideoDto {
    private int id;
    private String title;
    private int views;
    private String link;
    private VideoGenre genre;

    public VideoDto() {
    }

    public VideoDto(int id, String title, int views, String link, VideoGenre genre) {
        this.id = id;
        this.title = title;
        this.views = views;
        this.link = link;
        this.genre = genre;
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
}
