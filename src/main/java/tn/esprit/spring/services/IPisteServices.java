package tn.esprit.spring.services;

import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Color;

import java.util.List;

public interface IPisteServices {

    List<Piste> retrieveAllPistes();

    Piste addPiste(Piste piste);

    void removePiste(Long numPiste);

    Piste retrievePiste(Long numPiste);

    List<Piste> getPistesByColor(String color);

    int calculateMaxSlopeByColor(Color color);  // Ajoutez cette ligne ici

    List<Piste> retrievePistesLongerThan(int length);
}
