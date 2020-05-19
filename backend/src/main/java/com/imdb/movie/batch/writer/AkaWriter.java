package com.imdb.movie.batch.writer;

import com.imdb.movie.domain.Aka;
import com.imdb.movie.repository.AkaRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * A writer for Aka.
 *
 * @author gbhat on 16/05/2020.
 */
@Component
public class AkaWriter implements ItemWriter<Aka> {

    private final AkaRepository akaRepository;

    public AkaWriter(AkaRepository akaRepository) {
        this.akaRepository = akaRepository;
    }

    @Override
    @Transactional
    public void write(List<? extends Aka> akaList) {
        akaRepository.saveAll(akaList);
    }

}