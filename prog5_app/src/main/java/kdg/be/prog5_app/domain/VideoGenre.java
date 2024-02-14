package kdg.be.prog5_app.domain;

public enum VideoGenre {
    Action("Action"),
    Comedy("Comedy"),
    Horror("Horror"),
    Music("Music"),
    Educational("Educational"),;
    private final String displayValue;

    private VideoGenre(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
