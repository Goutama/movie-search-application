package com.imdb.movie.batch.processor;

import com.imdb.movie.domain.Title;
import com.imdb.movie.repository.TitleRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A item processor for Title entity.
 *
 * @author gbhat on 16/05/2020.
 */

@Component
public class TitleProcessor implements ItemProcessor<Title, Title> {


    private final TitleRepository titleRepository;

    public TitleProcessor(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Override
    public Title process(Title title) {
        Optional<Title> titleFromDb = titleRepository.findByTconst(title.getTconst());
        if (titleFromDb.isPresent()) return null;
        return title;
    }

}