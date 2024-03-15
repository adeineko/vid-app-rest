package kdg.be.prog5_app.controllers.api.dto;

public class UpdateChannelDto {
    private String name;
    private int subscribers;

    public UpdateChannelDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }
}
