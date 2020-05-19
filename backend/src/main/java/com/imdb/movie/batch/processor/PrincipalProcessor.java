package com.imdb.movie.batch.processor;

import com.imdb.movie.domain.Principal;
import com.imdb.movie.repository.PrincipalRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A item processor for Principal entity.
 *
 * @author gbhat on 16/05/2020.
 */

@Component
public class PrincipalProcessor implements ItemProcessor<Principal, Principal> {


    private final PrincipalRepository principalRepository;

    public PrincipalProcessor(PrincipalRepository principalRepository) {
        this.principalRepository = principalRepository;
    }

    @Override
    public Principal process(Principal principal) {
        Optional<Principal> principalFromDb = principalRepository.findByTconstAndOrdering(principal.getTconst(), principal.getOrdering());
        return principalFromDb.orElse(principal);
    }

}