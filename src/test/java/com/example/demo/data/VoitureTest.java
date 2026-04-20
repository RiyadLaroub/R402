package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoitureTest {

    @Test
     void creerVoiture(){
        Voiture v1 = new Voiture("Mercedes",15000);
        assertEquals(v1.getPrix(),15000);
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

    }


    @Test
    void toStringVoiture(){
        Voiture v3=new Voiture("Tesla",50000);
        v3.setId(95);
        String attendu="Car{" +"marque='" + "Tesla" + '\'' +", prix=" + 50000 +", id=" + 95 +'}';
        assertEquals(v3.toString(),attendu);
    }
}