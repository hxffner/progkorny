package hu.nye.albums.service;

import java.util.List;

import hu.nye.albums.model.dto.AlbumDTO;


/**
 * Interface for AlbumSevice.
 */
public interface AlbumService {

    List<AlbumDTO> getAllAlbums();

    AlbumDTO getAlbum(Long id);

    AlbumDTO addAlbum(AlbumDTO album);

    AlbumDTO updateAlbum(Long id, AlbumDTO albumChanges);

    void deleteAlbum(Long id);
}
