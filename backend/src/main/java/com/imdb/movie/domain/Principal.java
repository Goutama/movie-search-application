package com.imdb.movie.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author gbhat on 16/05/2020.
 */
@Data
@Builder
@EqualsAndHashCode(of = {"id","tconst", "ordering"})
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tconst", "ordering"}
        )},
        indexes = {
                @Index(name = "title_tconst_idx", columnList = "tconst"),
                @Index(name = "name_nconst_idx", columnList = "nconst")
        })
@Entity
public class Principal {
    @Id
    @SequenceGenerator(name="seq",sequenceName="principal_seq", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;
    private String tconst;
    private Long ordering;
    // index
    private String nconst;
    private String category;
    private String job;
    private String characters;

}
