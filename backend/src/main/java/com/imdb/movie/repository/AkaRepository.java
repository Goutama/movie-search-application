package com.imdb.movie.repository;

import com.imdb.movie.domain.Aka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data repository for the Name entity.
 *
 * @author gbhat on 14/05/2020.
 */
@SuppressWarnings("unused")
@Repository
public interface AkaRepository extends JpaRepository<Aka, Long> {
    Optional<Aka> findByTitleIdAndOrdering(String titleId, Long ordering);
}
