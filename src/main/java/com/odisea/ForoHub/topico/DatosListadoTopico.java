package com.odisea.ForoHub.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(Long id,
                                 String titulo,
                                 String mensaje,
                                 Boolean status) {

    public DatosListadoTopico(Topico topico){
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getStatus());
    }
}
