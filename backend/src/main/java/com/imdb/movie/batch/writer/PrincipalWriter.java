package com.imdb.movie.batch.writer;

import com.imdb.movie.domain.Principal;
import com.imdb.movie.repository.PrincipalRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * A item writer for Principal entity.
 *
 * @author gbhat on 16/05/2020.
 */
@Component
public class PrincipalWriter implements ItemWriter<Principal> {

    private final PrincipalRepository principalRepository;

    public PrincipalWriter(PrincipalRepository principalRepository) {
        this.principalRepository = principalRepository;
    }

    @Override
    @Transactional
    public void write(List<? extends Principal> principalList) {
        principalRepository.saveAll(principalList);
    }

}