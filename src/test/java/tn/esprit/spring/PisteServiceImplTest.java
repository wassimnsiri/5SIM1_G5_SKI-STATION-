package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PisteServiceImplTest {

    @Mock
    IPisteRepository pisteRepository;

    @InjectMocks
    PisteServicesImpl pisteService;

    private Piste piste;

    @BeforeEach
    void setUp() {
        piste = new Piste();
        piste.setNumPiste(1L);
        piste.setNamePiste("Green Valley");
        piste.setColor(Color.GREEN);
        piste.setLength(1000);
        piste.setSlope(15);
    }

    @Test
    void retrieveAllPistesTest() {
        List<Piste> pistesList = new ArrayList<>();
        pistesList.add(piste);

        when(pisteRepository.findAll()).thenReturn(pistesList);

        List<Piste> retrievedPistesList = pisteService.retrieveAllPistes();

        assertThat(retrievedPistesList).hasSize(1);
        verify(pisteRepository).findAll();
    }

    @Test
    void addPisteTest() {
        when(pisteRepository.save(Mockito.any(Piste.class))).thenReturn(piste);

        Piste savedPiste = pisteService.addPiste(piste);

        assertThat(savedPiste).isNotNull();
        verify(pisteRepository).save(Mockito.any(Piste.class));
    }

    @Test
    void deletePisteTest() {
        Long pisteId = 1L;
        doNothing().when(pisteRepository).deleteById(pisteId);

        pisteService.removePiste(pisteId);

        verify(pisteRepository, times(1)).deleteById(pisteId);
    }

    @Test
    void retrievePisteTest() {
        Long pisteId = 1L;
        when(pisteRepository.findById(pisteId)).thenReturn(Optional.of(piste));

        Piste retrievedPiste = pisteService.retrievePiste(pisteId);

        assertThat(retrievedPiste).isNotNull();
        verify(pisteRepository).findById(pisteId);
    }

    @Test
    void calculateMaxSlopeByColorTest() {
        List<Piste> pistes = List.of(
                new Piste(1L, "Green Valley", Color.GREEN, 1000, 15, null),
                new Piste(2L, "Blue Ridge", Color.BLUE, 1500, 20, null),
                new Piste(3L, "Black Diamond", Color.BLACK, 2000, 30, null),
                new Piste(4L, "Green Peak", Color.GREEN, 1200, 25, null)
        );

        when(pisteRepository.findAll()).thenReturn(pistes);

        int maxSlopeGreen = pisteService.calculateMaxSlopeByColor(Color.GREEN);

        assertThat(maxSlopeGreen).isEqualTo(25);
        verify(pisteRepository).findAll();
    }

    @Test
    void retrievePistesLongerThanTest() {
        List<Piste> pistes = List.of(
                new Piste(1L, "Green Valley", Color.GREEN, 1000, 15, null),
                new Piste(2L, "Blue Ridge", Color.BLUE, 1500, 20, null),
                new Piste(3L, "Black Diamond", Color.BLACK, 2000, 30, null),
                new Piste(4L, "Green Peak", Color.GREEN, 1200, 25, null)
        );

        when(pisteRepository.findAll()).thenReturn(pistes);

        List<Piste> longPistes = pisteService.retrievePistesLongerThan(1200);

        assertThat(longPistes).hasSize(2); // Should return the 2 pistes with length > 1200
        assertThat(longPistes).extracting(Piste::getNamePiste).containsExactly("Blue Ridge", "Black Diamond");
        verify(pisteRepository).findAll();
    }
}
