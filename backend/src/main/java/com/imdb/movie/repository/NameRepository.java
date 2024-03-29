package com.imdb.movie.repository;

import com.imdb.movie.domain.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data repository for the Name entity.
 *
 * @author gbhat on 14/05/2020.
 */
@SuppressWarnings("unused")
@Repository
public interface NameRepository extends JpaRepository<Name, Long> {
    Optional<Name> findByNconst(String nconst);
    List<Name> findByPrimaryName(String name);
    Short findDegreesOfSeparation(String sourceNameId, String targetNameId);
}
