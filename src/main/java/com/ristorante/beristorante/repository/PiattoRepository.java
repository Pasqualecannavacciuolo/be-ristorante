package com.ristorante.beristorante.repository;

import com.ristorante.beristorante.domain.Piatto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiattoRepository extends JpaRepository<Piatto, Integer> {
    List<Piatto> findByMenuId(Integer menu_id);
}