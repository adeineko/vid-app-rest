package kdg.be.prog5_app.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "subscribers")
    private int subscribers;

    @OneToMany(mappedBy = "channel")
    private List<ChannelVideo> videos;

    public Channel() {
    }

    public Channel(int id, String name, LocalDate date, int subscribers) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.subscribers = subscribers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }
}
