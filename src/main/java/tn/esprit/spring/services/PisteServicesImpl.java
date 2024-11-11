package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PisteServicesImpl implements IPisteServices {

    private IPisteRepository pisteRepository;

    @Override
    public List<Piste> retrieveAllPistes() {
        return pisteRepository.findAll();
    }

    @Override
    public Piste addPiste(Piste piste) {
        return pisteRepository.save(piste);
    }

    @Override
    public void removePiste(Long numPiste) {
        pisteRepository.deleteById(numPiste);
    }

    @Override
    public Piste retrievePiste(Long numPiste) {
        return pisteRepository.findById(numPiste).orElse(null);
    }

    public int calculateMaxSlopeByColor(Color color) {
        List<Piste> pistes = pisteRepository.findAll();

        List<Piste> filteredPistes = pistes.stream()
                .filter(piste -> piste.getColor().equals(color))
                .collect(Collectors.toList());

        if (filteredPistes.isEmpty()) {
            return 0;
        }

        return filteredPistes.stream()
                .mapToInt(Piste::getSlope)
                .max()
                .orElse(0);
    }

    // Nouvelle méthode pour récupérer toutes les pistes dont la longueur est supérieure à une valeur donnée
    public List<Piste> retrievePistesLongerThan(int length) {
        List<Piste> pistes = pisteRepository.findAll();

        return pistes.stream()
                .filter(piste -> piste.getLength() > length)
                .collect(Collectors.toList());
    }
}
