package com.imdb.movie.batch.processor;

import com.imdb.movie.domain.Name;
import com.imdb.movie.repository.NameRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A item processor for Name.
 *
 * @author gbhat on 16/05/2020.
 */

@Component
public class NameProcessor implements ItemProcessor<Name, Name> {


    private final NameRepository nameRepository;

    public NameProcessor(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    @Override
    public Name process(Name name) {
        Optional<Name> nameFromDb = nameRepository.findByNconst(name.getNconst());
        return nameFromDb.orElse(name);
    }

}