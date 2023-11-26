package com.atividade1611.controller;

import com.atividade1611.entities.Contato;
import com.atividade1611.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    ContatoRepository contatoRepository;

    @GetMapping
    public List<Contato> getContatos() {
        return contatoRepository.findAll();
    }

    @PostMapping
    public Contato inserirContato(@RequestBody Contato contato) {
        return contatoRepository.save(contato);
    }

    @PutMapping("/{id}")
    public Contato alterarContato(@PathVariable("id") Long id, @RequestBody Contato contato) {
        Optional<Contato> opContato = contatoRepository.findById(id);
        if (opContato.isPresent()) {
            Contato ct = opContato.get();
            ct.setNome(contato.getNome());
            ct.setEmail(contato.getEmail());
            return contatoRepository.save(ct);
        }
        return null;
    }

    @GetMapping("/{id}")
    public Contato getUmContato(@PathVariable("id") long id) {
        Optional<Contato> opContato = contatoRepository.findById(id);
        if (opContato.isPresent()) {
            return opContato.get();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluirUmContato(@PathVariable("id") long id) {
        Optional<Contato> opContato = contatoRepository.findById(id);
        if (opContato.isPresent()) {
            Contato contato = opContato.get();
            contatoRepository.delete(contato);
        }
    }
}