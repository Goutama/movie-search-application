package com.imdb.movie.repository;

import com.imdb.movie.domain.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Spring Data repository for the Title entity.
 *
 * @author gbhat on 14/05/2020.
 */
@SuppressWarnings("unused")
@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    Optional<Title> findByTconst(String tconst);
    Set<Title> findTitlesByNconsts(String nconst);
}
