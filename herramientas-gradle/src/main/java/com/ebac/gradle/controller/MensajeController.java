package com.ebac.gradle.controller;

import com.ebac.gradle.service.MensajeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(
        MensajeService mensajeService
    ) {
        this.mensajeService = mensajeService;
    }

    @GetMapping
    public String obtenerMensaje(
        @RequestParam(defaultValue = "EBAC") String nombre
    ) {
        return mensajeService.obtenerMensaje(nombre);
    }
}
