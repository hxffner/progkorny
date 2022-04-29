package hu.nye.albums.model;

public enum Genres {
    POP("Pop"),
    ROCK("Rock"),
    RAP("Rap");

    private final String value;

    Genres(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
