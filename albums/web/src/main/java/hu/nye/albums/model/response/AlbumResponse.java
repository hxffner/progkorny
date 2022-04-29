package hu.nye.albums.model.response;

import hu.nye.albums.model.Genres;

public record AlbumResponse(Long id, String name, String artist, Genres genre, String date, String description) {
}
