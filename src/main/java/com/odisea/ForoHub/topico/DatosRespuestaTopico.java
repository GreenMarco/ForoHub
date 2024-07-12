package com.odisea.ForoHub.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   LocalDateTime fechaCreacion,
                                   Boolean status,
                                   String autor,
                                   String curso) {
}
