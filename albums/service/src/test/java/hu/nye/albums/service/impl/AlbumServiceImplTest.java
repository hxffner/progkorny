package hu.nye.albums.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import hu.nye.albums.model.Album;
import hu.nye.albums.model.Genres;
import hu.nye.albums.model.dto.AlbumDTO;
import hu.nye.albums.model.exception.NotFoundException;
import hu.nye.albums.repository.AlbumRepository;
import hu.nye.albums.service.AlbumService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.converter.Converter;

class AlbumServiceImplTest {

    private static final Long ALBUM_ID = 1L;
    private static final String ALBUM_NAME = "To Pimp a Butterfly";
    private static final String ARTIST_NAME = "Kendrick Lamar";
    private static final Genres ALBUM_GENRE = Genres.RAP;
    private static final String RELEASE_DATE = "15 March 2015";
    private static final String ALBUM_DESCRIPTION = "political, conscious";
    private static final Album ALBUM = new Album(ALBUM_ID, ALBUM_NAME, ARTIST_NAME, ALBUM_GENRE, RELEASE_DATE, ALBUM_DESCRIPTION);
    private static final AlbumDTO ALBUM_DTO = new AlbumDTO(ALBUM_ID, ALBUM_NAME, ARTIST_NAME, ALBUM_GENRE, RELEASE_DATE, ALBUM_DESCRIPTION);
    private static final List<Album> ALBUMS = Arrays.asList(ALBUM, ALBUM);
    private static final List<AlbumDTO> DTO_ALBUMS = Arrays.asList(ALBUM_DTO, ALBUM_DTO);
    private static final String ALBUM_NOT_FOUND_EXCEPTION_ERROR_MESSAGE = "There is no Album with ID:" + ALBUM_ID;
    private static final String ALBUM_ILLEGAL_ARGUMENT_EXCEPTION_ERROR_MESSAGE = "Provided parameter is invalid: null";

    @Mock
    private AlbumRepository albumRepository;
    @Mock
    private Converter<AlbumDTO, Album> albumDtoToEntityConverter;
    @Mock
    private Converter<Album, AlbumDTO> albumEntityToDtoConverter;

    private AlbumService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new AlbumServiceImpl(albumRepository, albumDtoToEntityConverter, albumEntityToDtoConverter);
    }

    @Test
    void getAllAlbumsShouldReturnConvertedAlbums() {
        // given
        given(albumRepository.findAll()).willReturn(ALBUMS);
        given(albumEntityToDtoConverter.convert(ALBUM)).willReturn(ALBUM_DTO);
        // when
        final List<AlbumDTO> actual = underTest.getAllAlbums();
        // then
        assertThat(actual, Matchers.containsInAnyOrder(DTO_ALBUMS.toArray()));
    }

    @Test
    void getAlbumShouldReturnConvertedAlbumWhenGivenAlbumId() {
        // given
        given(albumRepository.findById(ALBUM_ID)).willReturn(Optional.of(ALBUM));
        given(albumEntityToDtoConverter.convert(ALBUM)).willReturn(ALBUM_DTO);
        // when
        final AlbumDTO actual = underTest.getAlbum(ALBUM_ID);
        // then
        assertThat(actual, equalTo(ALBUM_DTO));
    }

    @Test
    void updateAlbumWhenGivenAlbumIdAndNewDetails() {
        // given
        final Album illmatic = new Album(ALBUM_ID, "Illmatic", "Nas", Genres.RAP,"19 April 1994","urban, crime");
        given(albumRepository.findById(ALBUM_ID)).willReturn(Optional.of(illmatic));
        given(albumEntityToDtoConverter.convert(ALBUM)).willReturn(ALBUM_DTO);
        given(albumRepository.save(ALBUM)).willReturn(ALBUM);
        // when
        final AlbumDTO actual = underTest.updateAlbum(ALBUM_ID, ALBUM_DTO);
        // then
        verify(albumRepository).save(ALBUM);
        assertThat(actual, equalTo(ALBUM_DTO));
    }

    @Test
    void deleteAlbumWhenGivenAlbumId() {
        // given
        // when
        underTest.deleteAlbum(ALBUM_ID);
        // then
        verify(albumRepository).deleteById(ALBUM_ID);
    }


    //Throw Exception Tests
    @Test
    void updateAlbumShouldThrowNotFoundExceptionIfGivenWrongId() {
        // given
        given(albumRepository.findById(ALBUM_ID)).willReturn(Optional.empty());
        // when - then
        final NotFoundException actual = assertThrows(NotFoundException.class, () -> underTest.updateAlbum(ALBUM_ID, ALBUM_DTO));
        assertThat(actual.getMessage(), equalTo(ALBUM_NOT_FOUND_EXCEPTION_ERROR_MESSAGE));
    }

    @Test
    void getAlbumShouldThrowNotFoundExceptionIfGivenWrongId() {
        // given
        given(albumRepository.findById(ALBUM_ID)).willReturn(Optional.empty());
        // when - then
        final NotFoundException actual = assertThrows(NotFoundException.class, () -> underTest.getAlbum(ALBUM_ID));
        assertThat(actual.getMessage(), equalTo(ALBUM_NOT_FOUND_EXCEPTION_ERROR_MESSAGE));
    }

    @Test
    void addAlbumShouldThrowIllegalArgumentExceptionWhenItDoesNotGetAnyDetails() {
        // given
        // when - then
        final IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> underTest.addAlbum(null));
        assertThat(actual.getMessage(), equalTo(ALBUM_ILLEGAL_ARGUMENT_EXCEPTION_ERROR_MESSAGE));
    }
}