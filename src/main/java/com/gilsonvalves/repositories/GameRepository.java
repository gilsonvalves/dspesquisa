package com.gilsonvalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsonvalves.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
