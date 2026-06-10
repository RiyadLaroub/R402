package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VoitureTest {


    @Test
    void creerVoitureAvecConstructeurVide() { // vérifie que le constructeur vide initialise prix à 0 et marque à null
        Voiture v = new Voiture();
        assertEquals(0, v.getPrix());
        assertNull(v.getMarque());
    }

    @Test
    void creerVoitureAvecMarqueEtPrix() { // vérifie que le constructeur avec paramètres initialise correctement marque et prix
        Voiture v = new Voiture("Renault", 15000);
        assertEquals("Renault", v.getMarque());
        assertEquals(15000, v.getPrix());
    }
    @Test 
    void getsetVoiture(){
        Voiture v=new Voiture();
        v.setMarque("Opel");
        v.setId(1);
        v.setPrix(5000);
        assertEquals(v.getPrix(),5000);
        assertEquals(v.getId(),1);
        assertEquals(v.getMarque(),"Opel");

    @Test
    void modifierMarque() { // vérifie que setMarque met bien à jour la marque de la voiture
        Voiture v = new Voiture("Peugeot", 10000);
        v.setMarque("Toyota");
        assertEquals("Toyota", v.getMarque());
    }

    @Test
    void modifierPrix() { // vérifie que setPrix met bien à jour le prix de la voiture
        Voiture v = new Voiture("BMW", 30000);
        v.setPrix(25000);
        assertEquals(25000, v.getPrix());
    }

    @Test
    void modifierId() { // vérifie que setId met bien à jour l'identifiant de la voiture
        Voiture v = new Voiture();
        v.setId(42);
        assertEquals(42, v.getId());
    }

    @Test
    void toStringContientMarqueEtPrix() { // vérifie que toString inclut bien la marque et le prix dans sa sortie
        Voiture v = new Voiture("Ferrari", 200000);
        v.setId(1);
        String s = v.toString();
        assertTrue(s.contains("Ferrari"));
        assertTrue(s.contains("200000"));
    }
}
