package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PisteServicesImplTest {

    @Mock
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllPistes() {
        // Arrange
        Piste piste1 = new Piste(1L, "Piste 1", Color.GREEN, 300, 20, null);
        Piste piste2 = new Piste(2L, "Piste 2", Color.RED, 500, 30, null);
        when(pisteRepository.findAll()).thenReturn(Arrays.asList(piste1, piste2));

        // Act
        List<Piste> pistes = pisteServices.retrieveAllPistes();

        // Assert
        assertNotNull(pistes);
        assertEquals(2, pistes.size());
        verify(pisteRepository, times(1)).findAll();
    }

    @Test
    void testAddPiste() {
        // Arrange
        Piste piste = new Piste(null, "Piste Test", Color.BLUE, 400, 25, null);
        when(pisteRepository.save(piste)).thenReturn(new Piste(1L, "Piste Test", Color.BLUE, 400, 25, null));

        // Act
        Piste savedPiste = pisteServices.addPiste(piste);

        // Assert
        assertNotNull(savedPiste);
        assertEquals(1L, savedPiste.getNumPiste());
        assertEquals("Piste Test", savedPiste.getNamePiste());
        verify(pisteRepository, times(1)).save(piste);
    }

    @Test
    void testRemovePiste() {
        // Arrange
        Long numPiste = 1L;

        // Act
        pisteServices.removePiste(numPiste);

        // Assert
        verify(pisteRepository, times(1)).deleteById(numPiste);
    }

    @Test
    void testRetrievePiste() {
        // Arrange
        Long numPiste = 1L;
        Piste piste = new Piste(numPiste, "Piste 1", Color.GREEN, 300, 20, null);
        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        // Act
        Piste foundPiste = pisteServices.retrievePiste(numPiste);

        // Assert
        assertNotNull(foundPiste);
        assertEquals(numPiste, foundPiste.getNumPiste());
        assertEquals("Piste 1", foundPiste.getNamePiste());
        verify(pisteRepository, times(1)).findById(numPiste);
    }

    @Test
    void testRetrievePiste_NotFound() {
        // Arrange
        Long numPiste = 1L;
        when(pisteRepository.findById(numPiste)).thenReturn(Optional.empty());

        // Act
        Piste foundPiste = pisteServices.retrievePiste(numPiste);

        // Assert
        assertNull(foundPiste);
        verify(pisteRepository, times(1)).findById(numPiste);
    }
}
