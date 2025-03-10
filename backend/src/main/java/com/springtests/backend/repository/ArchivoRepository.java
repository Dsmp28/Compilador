package com.springtests.backend.repository;

import com.springtests.backend.entity.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
}
