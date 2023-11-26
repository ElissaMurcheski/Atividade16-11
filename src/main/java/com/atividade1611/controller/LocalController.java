package com.atividade1611.controller;

import com.atividade1611.entities.Local;
import com.atividade1611.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locais")
public class LocalController {
    @Autowired
    private LocalRepository localRepository;

    @GetMapping
    public List<Local> getLocais() {
        return localRepository.findAll();
    }

    @PostMapping
    public Local inserirLocal(@RequestBody Local local) {
        return localRepository.save(local);
    }

    @PutMapping("/{id}")
    public Local alterarLocal(@PathVariable("id") Long id, @RequestBody Local local) {
        Optional<Local> opLocal = localRepository.findById(id);
        if (opLocal.isPresent()) {
            Local lc = opLocal.get();
            lc.setNome(local.getNome());
            lc.setCidade(local.getCidade());
            lc.setBairro(local.getBairro());
            lc.setRua(local.getRua());
            lc.setNumero(local.getNumero());
            lc.setCep(local.getCep());
            return localRepository.save(lc);
        }
        return null;
    }

    @GetMapping("/{id}")
    public Local getUmLocal(@PathVariable("id") long id) {
        Optional<Local> opLocal = localRepository.findById(id);
        if (opLocal.isPresent()) {
            return opLocal.get();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluirUmLocal(@PathVariable("id") long id) {
        Optional<Local> opLocal = localRepository.findById(id);
        if (opLocal.isPresent()) {
            Local ct = opLocal.get();
            localRepository.delete(ct);
        }
    }

}
