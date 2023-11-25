package com.atividade1611.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade1611.entities.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

}
