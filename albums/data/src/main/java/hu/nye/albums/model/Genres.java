package hu.nye.albums.model;

/**
 * Enum that stores Genres and their values.
 */
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
