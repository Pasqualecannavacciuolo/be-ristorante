package com.ristorante.beristorante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ristorante.beristorante.domain.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>{}
