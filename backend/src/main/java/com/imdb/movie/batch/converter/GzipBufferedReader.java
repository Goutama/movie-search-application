package com.imdb.movie.batch.converter;

import org.springframework.batch.item.file.BufferedReaderFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

/**
 * A buffer reader factory for gzip files.
 *
 * @author gbhat on 16/05/2020.
 */
@Component
public class GzipBufferedReader implements BufferedReaderFactory {
    @Override
    public BufferedReader create(Resource resource, String encoding) throws IOException {
            if (Objects.requireNonNull(resource.getFilename()).endsWith(".gz")
                    || resource.getDescription().endsWith(".gzip")) {
                return new BufferedReader(new InputStreamReader(new GZIPInputStream(resource.getInputStream()), encoding));
            }
         return new BufferedReader(new InputStreamReader(resource.getInputStream(), encoding));
    }
}
