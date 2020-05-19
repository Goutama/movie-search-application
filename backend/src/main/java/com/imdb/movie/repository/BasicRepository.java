package com.imdb.movie.repository;

import com.imdb.movie.domain.Basic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Spring Data repository for the Basic entity.
 *
 * @author gbhat on 14/05/2020.
 */
@SuppressWarnings("unused")
@Repository
public interface BasicRepository extends JpaRepository<Basic, Long> {
    Optional<Basic> findByTconst(String tconst);
    Set<Basic> findBasicsByNconsts(String nconst);
}
