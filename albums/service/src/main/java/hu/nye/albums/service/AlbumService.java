package hu.nye.albums.service;

import hu.nye.albums.model.dto.AlbumDTO;

import java.util.List;

public interface AlbumService {

    List<AlbumDTO> getAllAlbums();

    AlbumDTO getAlbum(Long id);

    AlbumDTO addAlbum(AlbumDTO product);

    AlbumDTO updateAlbum(Long id, AlbumDTO albumChanges);

    void deleteAlbum(Long id);
}
