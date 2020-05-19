package com.imdb.movie.batch.writer;

import com.imdb.movie.domain.Basic;
import com.imdb.movie.repository.BasicRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * A item writer for Basic entity.
 *
 * @author gbhat on 16/05/2020.
 */
@Component
public class BasicWriter implements ItemWriter<Basic> {

    private final BasicRepository basicRepository;

    public BasicWriter(BasicRepository basicRepository) {
        this.basicRepository = basicRepository;
    }

    @Override
    @Transactional
    public void write(List<? extends Basic> basicList) {
        basicRepository.saveAll(basicList);
    }

}