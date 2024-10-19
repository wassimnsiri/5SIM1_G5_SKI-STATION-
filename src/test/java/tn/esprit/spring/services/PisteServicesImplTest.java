package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PisteServicesImplTest {

    @InjectMocks
    private PisteServicesImpl pisteServices;  // Inject the service to test

    @Mock
    private IPisteRepository pisteRepository;  // Mock the repository

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize the mocks
    }

    @Test
    void retrieveAllPistes() {
        // Arrange: Set up test data
        List<Piste> pistes = Arrays.asList(
                new Piste(1L, "Piste 1", Color.BLUE, 500, 15, null),
                new Piste(2L, "Piste 2", Color.RED, 800, 20, null)
        );

        // Mock the repository behavior
        when(pisteRepository.findAll()).thenReturn(pistes);

        // Act: Call the service method
        List<Piste> result = pisteServices.retrieveAllPistes();

        // Assert: Verify the results
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Piste 1", result.get(0).getNamePiste());
        verify(pisteRepository, times(1)).findAll();  // Verify the repository was called once
    }

    @Test
    void addPiste() {
        // Arrange
        Piste piste = new Piste(1L, "Piste 1", Color.GREEN, 600, 10, null);
        when(pisteRepository.save(piste)).thenReturn(piste);

        // Act
        Piste result = pisteServices.addPiste(piste);

        // Assert
        assertNotNull(result);
        assertEquals("Piste 1", result.getNamePiste());
        verify(pisteRepository, times(1)).save(piste);
    }

    @Test
    void removePiste() {
        // Arrange
        Long pisteId = 1L;
        doNothing().when(pisteRepository).deleteById(pisteId);

        // Act
        pisteServices.removePiste(pisteId);

        // Assert
        verify(pisteRepository, times(1)).deleteById(pisteId);  // Verify the repository was called for deletion
    }

    @Test
    void retrievePiste() {
        // Arrange
        Long pisteId = 1L;
        Piste piste = new Piste(1L, "Piste 1", Color.BLACK, 700, 24, null);
        when(pisteRepository.findById(pisteId)).thenReturn(Optional.of(piste));

        // Act
        Piste result = pisteServices.retrievePiste(pisteId);

        // Assert
        assertNotNull(result);
        assertEquals("Piste 1", result.getNamePiste());
        verify(pisteRepository, times(1)).findById(pisteId);
    }

    @Test
    void retrievePiste_notFound() {
        // Arrange
        Long pisteId = 1L;
        when(pisteRepository.findById(pisteId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pisteServices.retrievePiste(pisteId);
        });

        assertEquals("Piste not found", exception.getMessage());
    }

    @Test
    void retrievePiste_withValidId_shouldReturnPiste() {
        // Arrange
        Long pisteId = 2L;
        Piste piste = new Piste(pisteId, "Piste 2", Color.RED, 800, 20, null);
        when(pisteRepository.findById(pisteId)).thenReturn(Optional.of(piste));

        // Act
        Piste result = pisteServices.retrievePiste(pisteId);

        // Assert
        assertNotNull(result);
        assertEquals("Piste 2", result.getNamePiste());
    }

    @Test
    void retrievePiste_withInvalidId_shouldThrowException() {
        // Arrange
        Long pisteId = 3L;
        when(pisteRepository.findById(pisteId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pisteServices.retrievePiste(pisteId);
        });

        assertEquals("Piste not found", exception.getMessage());
    }
}
