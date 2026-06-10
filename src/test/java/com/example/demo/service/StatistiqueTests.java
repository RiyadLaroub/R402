package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StatistiqueTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Test
    void prixMoyenUneVoiture() throws ArithmeticException { // vérifie que prixMoyen retourne le bon résultat pour un échantillon d'une voiture
        Echantillon echantillon = new Echantillon(1, 10000);
        when(statistiqueImpl.prixMoyen()).thenReturn(echantillon);

        Echantillon e = statistiqueImpl.prixMoyen();
        assertEquals(1, e.getNombreDeVoitures());
        assertEquals(10000, e.getPrixMoyen());
    }

    @Test
    void prixMoyenPlusieursVoitures() throws ArithmeticException { // vérifie que prixMoyen calcule correctement la moyenne sur plusieurs voitures
        Echantillon echantillon = new Echantillon(2, 15000);
        when(statistiqueImpl.prixMoyen()).thenReturn(echantillon);

        Echantillon e = statistiqueImpl.prixMoyen();
        assertEquals(2, e.getNombreDeVoitures());
        assertEquals(15000, e.getPrixMoyen());
    }

    @Test
    void ajouterVoitureAppeleBien() { // vérifie que la méthode ajouter est bien appelée une fois avec la voiture passée en paramètre
        Voiture v = new Voiture("Renault", 10000);
        assertEquals(10000, v.getPrix());
        statistiqueImpl.ajouter(v);
        verify(statistiqueImpl, times(1)).ajouter(v);


    }
}
