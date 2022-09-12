package com.example.caref.repository;

import com.example.caref.models.Fichier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichierRepository extends JpaRepository<Fichier, Long> {

    @Query(value = "SELECT * FROM fichiers where user_id=?", nativeQuery = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    public List<Fichier> findAllByCreatedBy(Long userId);

    public Fichier findByFichierTitle(String fichierTitle);

    public Fichier findById(long id);

    public List<Fichier> findAll();

}
