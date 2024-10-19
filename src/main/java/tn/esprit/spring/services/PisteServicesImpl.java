package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class PisteServicesImpl implements IPisteServices {

    private static final Logger logger = LoggerFactory.getLogger(PisteServicesImpl.class);
    private final IPisteRepository pisteRepository;

    @Override
    public List<Piste> retrieveAllPistes() {
        logger.info("Retrieving all pistes");
        return pisteRepository.findAll();
    }

    @Override
    public Piste addPiste(Piste piste) {
        logger.info("Adding piste: {}", piste);
        // Vous pouvez ajouter ici une logique de validation si nécessaire
        return pisteRepository.save(piste);
    }

    @Override
    public void removePiste(Long numPiste) {
        logger.info("Removing piste with id: {}", numPiste);
        // Vous pouvez ajouter ici une logique de validation si nécessaire
        pisteRepository.deleteById(numPiste);
    }

    @Override
    public Piste retrievePiste(Long numPiste) {
        logger.info("Retrieving piste with id: {}", numPiste);
        return pisteRepository.findById(numPiste)
                .orElseThrow(() -> new RuntimeException("Piste not found"));
    }
}
