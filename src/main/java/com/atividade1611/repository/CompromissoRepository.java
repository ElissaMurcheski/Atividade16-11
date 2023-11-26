package com.atividade1611.repository;

import com.atividade1611.entities.Contato;
import com.atividade1611.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atividade1611.entities.Compromisso;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long>{

    @Query("SELECT c FROM Compromisso c WHERE c.local = :local")
    List<Compromisso> getCompromissoPorLocalId(@Param("local") Local local);

    @Query("SELECT c FROM Compromisso c WHERE c.contato = :contato")
    List<Compromisso> getCompromissoPorContatoId(@Param("contato") Contato contato);

    @Query("SELECT c FROM Compromisso c WHERE c.data BETWEEN :de AND :ate")
    List<Compromisso> getCompromissoPorData(@Param("de") LocalDate de, @Param("ate") LocalDate ate);
}