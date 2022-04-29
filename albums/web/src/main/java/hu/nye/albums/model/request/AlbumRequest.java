package hu.nye.albums.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import hu.nye.albums.model.Genres;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@Getter
@JsonDeserialize(builder = AlbumRequest.AlbumRequestBuilder.class)
@ToString
public class AlbumRequest {
    private final String name;
    private final String artist;
    private final Genres genre;
    private final String date;
    private final String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AlbumRequestBuilder {

    }
}
