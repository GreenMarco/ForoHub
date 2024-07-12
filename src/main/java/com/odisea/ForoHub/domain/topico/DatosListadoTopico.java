package com.odisea.ForoHub.domain.topico;

public record DatosListadoTopico(Long id,
                                 String titulo,
                                 String mensaje,
                                 Boolean status) {

    public DatosListadoTopico(Topico topico){
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getStatus());
    }
}
