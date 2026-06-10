package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    // static import of MockMvcRequestBuilders.* and MockMvcResultMatchers.*

    @Test
    void statistiquesVoitureAccessibles() throws Exception { // vérifie que GET /statistique retourne 200 avec le bon prix moyen et nombre de voitures
        when(statistiqueImpl.prixMoyen()).thenReturn(new Echantillon(2, 15000));

        mockMvc.perform(get("/statistique"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDeVoitures").value(2))
                .andExpect(jsonPath("$.prixMoyen").value(15000));
    }

    @Test
    void aucuneVoiture() throws Exception { // vérifie que GET /statistique retourne 400 quand la liste est vide
        when(statistiqueImpl.prixMoyen()).thenThrow(ArithmeticException.class);

        mockMvc.perform(get("/statistique"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postVoitureFonctionne() throws Exception { // vérifie que POST /voiture accepte le JSON et retourne 200
        String voitureJson = "{\"marque\":\"Renault\",\"prix\":10000}";

        mockMvc.perform(post("/voiture")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voitureJson))
                .andExpect(status().isOk());

        verify(statistiqueImpl, times(1)).ajouter(any(Voiture.class));
    }

}
