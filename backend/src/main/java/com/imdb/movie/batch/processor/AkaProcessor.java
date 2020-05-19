package com.imdb.movie.batch.processor;

import com.imdb.movie.domain.Aka;
import com.imdb.movie.repository.AkaRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A item processor for Aka.
 *
 * @author gbhat on 16/05/2020.
 */

@Component
public class AkaProcessor implements ItemProcessor<Aka, Aka> {


    private final AkaRepository akaRepository;

    public AkaProcessor(AkaRepository akaRepository) {
        this.akaRepository = akaRepository;
    }

    @Override
    public Aka process(Aka aka) {
        Optional<Aka> akaFromDb = akaRepository.findByTitleIdAndOrdering(aka.getTitleId(), aka.getOrdering());
        return akaFromDb.orElse(aka);
    }

}