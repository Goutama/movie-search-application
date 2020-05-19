package com.imdb.movie.batch.processor;

import com.imdb.movie.domain.Basic;
import com.imdb.movie.repository.BasicRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A item processor for Basic.
 *
 * @author gbhat on 16/05/2020.
 */

@Component
public class BasicProcessor implements ItemProcessor<Basic, Basic> {


    private final BasicRepository basicRepository;

    public BasicProcessor(BasicRepository basicRepository) {
        this.basicRepository = basicRepository;
    }

    @Override
    public Basic process(Basic basic) {
        Optional<Basic> basicFromDb = basicRepository.findByTconst(basic.getTconst());
        return basicFromDb.orElse(basic);
    }

}