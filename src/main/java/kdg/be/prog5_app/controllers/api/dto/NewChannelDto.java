package kdg.be.prog5_app.controllers.api.dto;

import java.time.LocalDate;

public class NewChannelDto {
    private String name;
    private LocalDate date;
    private int subscribers;

    public NewChannelDto() {
    }

    public NewChannelDto(String name, LocalDate date, int subscribers) {
        this.name = name;
        this.date = date;
        this.subscribers = subscribers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }
}
