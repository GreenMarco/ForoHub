package com.odisea.ForoHub.controller;

import com.odisea.ForoHub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tópicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> publicarPost(@RequestBody @Valid DatosRegistroTopico datos,
                                                             UriComponentsBuilder uriComponentsBuilder){
        System.out.println(datos);
        Topico topico = topicoRepository.save(new Topico(datos));
        DatosRespuestaTopico datosRespuesta;
        datosRespuesta = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor(),
                topico.getCurso());
        URI url = uriComponentsBuilder.path("/tópicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity <Page<DatosListadoTopico>> listadoPost(@PageableDefault(size=2) Pageable pageable){
        return ResponseEntity.ok().body(topicoRepository.findByStatusTrue(pageable)
                .map(DatosListadoTopico::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity <DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor(),
                topico.getCurso());
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    //ResponseEntity
    public ResponseEntity<DatosRespuestaTopico> actualizaTopico(@RequestBody @Valid DatosActualizaTopico datosActualizaTopico, @PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datosActualizaTopico);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor(),
                topico.getCurso());
        return ResponseEntity.ok(datosTopico);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable Long id){
        topicoRepository.deleteById(id);

    }

}
