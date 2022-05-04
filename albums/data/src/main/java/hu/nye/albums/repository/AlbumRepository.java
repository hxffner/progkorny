package hu.nye.albums.repository;

import hu.nye.albums.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Album Repository inferface.
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
