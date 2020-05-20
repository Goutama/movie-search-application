package com.imdb.movie.batch.writer;

import com.imdb.movie.domain.Title;
import com.imdb.movie.repository.TitleRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * A item writer for Title entity.
 *
 * @author gbhat on 16/05/2020.
 */
@Component
public class TitleWriter implements ItemWriter<Title> {

    private final TitleRepository titleRepository;

    public TitleWriter(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Override
    @Transactional
    public void write(List<? extends Title> titleList) {
        titleRepository.saveAll(titleList);
    }

}