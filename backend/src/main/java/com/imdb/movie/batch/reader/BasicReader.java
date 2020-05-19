package com.imdb.movie.batch.reader;

import com.imdb.movie.batch.mapper.BasicMapper;
import com.imdb.movie.domain.Basic;
import org.springframework.batch.item.file.BufferedReaderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import static org.springframework.batch.item.file.transform.DelimitedLineTokenizer.DELIMITER_TAB;


/**
 * A item reader for Basic.
 *
 * @author gbhat on 16/05/2020.
 */
public class BasicReader extends FlatFileItemReader<Basic> {

    public BasicReader(Resource resource, BufferedReaderFactory bufferedReaderFactory) {
        super();
        setResource(resource);
        setBufferedReaderFactory(bufferedReaderFactory);

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("tconst", "titleType", "primaryTitle", "originalTitle", "isAdult", "startYear", "endYear", "runtimeMinutes", "genres");
        lineTokenizer.setDelimiter(DELIMITER_TAB);

        DefaultLineMapper<Basic> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(new BasicMapper());

        setLineMapper(defaultLineMapper);
    }
}