package com.ebac.gradle.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {

    @Cacheable("mensajes")
    public String obtenerMensaje(String nombre) {
        System.out.println(
            "Ejecutando capa de negocio para: " + nombre
        );

        return "Hola " + nombre
                + ", este mensaje viene de la capa de negocio.";
    }
}
