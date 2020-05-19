package com.imdb.movie.batch.writer;

import com.imdb.movie.domain.Name;
import com.imdb.movie.repository.NameRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * A item writer for Name entity.
 *
 * @author gbhat on 16/05/2020.
 */
@Component
public class NameWriter implements ItemWriter<Name> {

    private final NameRepository nameRepository;

    public NameWriter(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    @Override
    @Transactional
    public void write(List<? extends Name> nameList) {
        nameRepository.saveAll(nameList);
    }

}