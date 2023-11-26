package com.atividade1611.controller;

import com.atividade1611.entities.Compromisso;
import com.atividade1611.entities.Contato;
import com.atividade1611.entities.Local;
import com.atividade1611.enums.Status;
import com.atividade1611.repository.CompromissoRepository;
import com.atividade1611.repository.ContatoRepository;
import com.atividade1611.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compromissos")
public class CompromissoController {

    @Autowired
    CompromissoRepository compromissoRepository;

    @Autowired
    ContatoRepository contatoRepository;

    @Autowired
    LocalRepository localRepository;

    @GetMapping()
    public List<Compromisso> getCompromisso() {
        return compromissoRepository.findAll();
    }

    @PostMapping()
    public Compromisso inserirCompromisso(@RequestBody Compromisso compromisso) {
        compromisso.setStatus(Status.ANDAMENTO);
        return compromissoRepository.save(compromisso);
    }

    @PutMapping("/{id}")
    public Compromisso alterarCompromisso(@PathVariable("id") Long id, @RequestBody Compromisso compromisso) {
        Optional<Compromisso> opCompromisso = compromissoRepository.findById(id);
        if (opCompromisso.isPresent()) {
            Compromisso cp = opCompromisso.get();
            cp.setData(compromisso.getData());
            cp.setHora(compromisso.getHora());
            cp.setStatus(compromisso.getStatus());
            cp.setContato(compromisso.getContato());
            cp.setLocal(compromisso.getLocal());
            return compromissoRepository.save(cp);
        }
        return null;
    }

    @GetMapping("/{id}")
    public Compromisso getUmCompromisso(@PathVariable("id") long id) {
        Optional<Compromisso> opCompromisso = compromissoRepository.findById(id);
        if (opCompromisso.isPresent()) {
            return opCompromisso.get();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluirUmCompromisso(@PathVariable("id") long id) {
        Optional<Compromisso> opCompromisso = compromissoRepository.findById(id);
        if (opCompromisso.isPresent()) {
            Compromisso cp = opCompromisso.get();
            compromissoRepository.delete(cp);
        }
    }

    @GetMapping("/local/{id}")
    public List<Compromisso> getCompromissoPorLocalId(@PathVariable("id") long id) {
        Optional<Local> local = localRepository.findById(id);
        if (local.isPresent()) {
            return compromissoRepository.getCompromissoPorLocalId(local.get());
        }
        return null;
    }

    @GetMapping("/contato/{id}")
    public List<Compromisso> getCompromissoPorContatoId(@PathVariable("id") long id) {
        Optional<Contato> contato = contatoRepository.findById(id);
        if (contato.isPresent()) {
            return compromissoRepository.getCompromissoPorContatoId(contato.get());
        }
        return null;
    }

    @GetMapping("/data/{de}/{ate}")
    public List<Compromisso> getCompromissoPorData(@PathVariable("de") LocalDate de, @PathVariable("ate") LocalDate ate) {
        return compromissoRepository.getCompromissoPorData(de, ate);
    }

    @PutMapping("/finalizar/{id}")
    public Compromisso finalizarCompromisso(@PathVariable("id") long id) {
        Compromisso compromisso = getUmCompromisso(id);
        if (compromisso != null) {
            compromisso.setStatus(Status.FINALIZADO);
            return compromissoRepository.save(compromisso);
        }
        return null;
    }
}

