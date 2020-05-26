package com.imdb.movie.batch.reader;

import com.imdb.movie.batch.mapper.PrincipalMapper;
import com.imdb.movie.domain.Principal;
import org.springframework.batch.item.file.BufferedReaderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import static org.springframework.batch.item.file.transform.DelimitedLineTokenizer.DELIMITER_TAB;


/**
 * A item reader for Principal entity.
 *
 * @author gbhat on 16/05/2020.
 */
public class PrincipalReader extends FlatFileItemReader<Principal> {

    public PrincipalReader(Resource resource, BufferedReaderFactory bufferedReaderFactory) {
        super();
        setResource(resource);
        setBufferedReaderFactory(bufferedReaderFactory);

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("tconst", "ordering", "nconst", "category", "job", "characters");
        lineTokenizer.setDelimiter(DELIMITER_TAB);

        DefaultLineMapper<Principal> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(new PrincipalMapper());

        setLineMapper(defaultLineMapper);
        setLinesToSkip(1);
        setSaveState(false);
    }
}