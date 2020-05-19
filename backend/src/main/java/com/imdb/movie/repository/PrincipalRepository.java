package com.imdb.movie.repository;

import com.imdb.movie.domain.Principal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data repository for the Principal entity.
 *
 * @author gbhat on 14/05/2020.
 */
@SuppressWarnings("unused")
@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Long> {
    Optional<Principal> findByTconstAndOrdering(String tconst, Long ordering);
}
