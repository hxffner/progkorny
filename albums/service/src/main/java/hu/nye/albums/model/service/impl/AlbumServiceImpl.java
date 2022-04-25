package hu.nye.albums.model.service.impl;

import hu.nye.albums.model.Album;
import hu.nye.albums.model.dto.AlbumDTO;
import hu.nye.albums.model.exception.NotFoundException;
import hu.nye.albums.model.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import hu.nye.albums.repository.AlbumRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final Converter<AlbumDTO, Album> albumDtoToEntityConverter;
    private final Converter<Album, AlbumDTO> albumEntityToDtoConverter;

    @Override
    public List<AlbumDTO> getAllAlbums() {
        log.info("Get all Albums.");
        return albumRepository.findAll().stream()
                .map(albumEntityToDtoConverter::convert)
                .toList();
    }

    @Override
    public AlbumDTO getAlbum(final Long id) {
        log.info("Get a Album with ID:{}.", id);
        return albumRepository.findById(id)
                .map(albumEntityToDtoConverter::convert)
                .orElseThrow(() -> new NotFoundException("There is no Album with ID:" + id));
    }

    @Override
    public AlbumDTO addAlbum(final AlbumDTO album) {
        log.info("Add a Album:{}.", album);
        return Optional.ofNullable(album)
                .map(albumDtoToEntityConverter::convert)
                .map(albumRepository::save)
                .map(albumEntityToDtoConverter::convert)
                .orElseThrow(() -> new IllegalArgumentException("Provided parameter is invalid: " + album));
    }

    @Override
    public AlbumDTO updateAlbum(final Long id, final AlbumDTO albumChanges) {
        log.info("Update Album with ID:{} to {}.", id, albumChanges);
        final Album album = albumRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no Album with ID:" + id));
        album.setName(albumChanges.name());
        album.setDescription(albumChanges.description());
        album.setArtist(albumChanges.artist());
        album.setGenre(albumChanges.genre());
        album.setDate(albumChanges.date());
        final Album updatedAlbum = albumRepository.save(album);
        return albumEntityToDtoConverter.convert(updatedAlbum);
    }

    @Override
    public void deleteAlbum(final Long id) {
        log.info("Delete Album with ID:{}.", id);
        albumRepository.deleteById(id);
    }
}

