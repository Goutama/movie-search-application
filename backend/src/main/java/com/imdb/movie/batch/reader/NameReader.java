package com.imdb.movie.batch.reader;

import com.imdb.movie.batch.mapper.NameMapper;
import com.imdb.movie.domain.Name;
import org.springframework.batch.item.file.BufferedReaderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import static org.springframework.batch.item.file.transform.DelimitedLineTokenizer.DELIMITER_TAB;


/**
 * A item reader for Name entity.
 *
 * @author gbhat on 16/05/2020.
 */
public class NameReader extends FlatFileItemReader<Name> {

    public NameReader(Resource resource, BufferedReaderFactory bufferedReaderFactory) {
        super();
        setResource(resource);
        setBufferedReaderFactory(bufferedReaderFactory);

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("nconst", "primaryName", "birthYear", "deathYear", "primaryProfessions", "knownForTitles");
        lineTokenizer.setDelimiter(DELIMITER_TAB);

        DefaultLineMapper<Name> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(new NameMapper());

        setLineMapper(defaultLineMapper);
    }
}