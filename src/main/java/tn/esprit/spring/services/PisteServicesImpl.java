package tn.esprit.spring.services;

import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import tn.esprit.spring.entities.Color;  // Assurez-vous d'avoir cette importation


@Service
public class PisteServicesImpl implements IPisteServices {

    @Autowired
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

    @Override
    public List<Piste> getPistesByColor(String color) {
        return pisteRepository.findAll().stream()
                .filter(piste -> piste.getColor().name().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    @Override
    public int calculateMaxSlopeByColor(Color color) {
        return pisteRepository.findAll().stream()
                .filter(piste -> piste.getColor() == color)
                .mapToInt(Piste::getSlope)
                .max()
                .orElse(0);
    }

    @Override
    public List<Piste> retrievePistesLongerThan(int length) {
        return pisteRepository.findAll().stream()
                .filter(piste -> piste.getLength() > length)
                .collect(Collectors.toList());
    }
}
