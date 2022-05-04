package hu.nye.albums.model.dto;

import hu.nye.albums.model.Genres;

/**
 * DTO Record for Albums.
 */
public record AlbumDTO(Long id, String name, String artist, Genres genre, String date, String description) {
}

