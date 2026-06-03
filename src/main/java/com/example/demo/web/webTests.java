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

     @Test
    void testCreerVoiture() throws Exception {
        mockMvc.perform(post("/voiture")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"marque\":\"f\",\"prix\":100}"))
        .andExpect(status().isOk());
       
        verify(statistiqueImpl, times(1)).ajouter(any(Voiture.class));
    }
      
    @Test
      void testGetStatistiques() throws Exception {
            Echantillon echantillon = new Echantillon(1, 100);
            when(statistiqueImpl.prixMoyen()).thenReturn(echantillon);
            mockMvc.perform(get("/statistique"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombreDeVoitures").value(1))
            .andExpect(jsonPath("$.prixMoyen").value(100));
        }
      
        @Test
          void testGetStatistiquesEmpty() throws Exception {
            when(statistiqueImpl.prixMoyen()).thenThrow(new ArithmeticException());
            mockMvc.perform(get("/statistique"))
            .andExpect(status().isBadRequest());
       }

  

}