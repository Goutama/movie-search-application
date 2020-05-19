package com.imdb.movie.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * @author gbhat on 16/05/2020.
 */
@NamedQueries(
        value = {
                @NamedQuery(
                        name = "Basic.findBasicsByNconsts",
                        query = "select b from Basic b inner join Principal p on b.tconst = p.tconst and p.nconst in :nconst")
        })
@Data
@Builder
@EqualsAndHashCode(of = {"tconst"})
@Entity
@Table(indexes = {@Index(name = "tconst_idx", columnList = "tconst")})
public class Basic {
    @Id
    @SequenceGenerator(name="seq",sequenceName="basic_seq", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private Boolean isAdult;
    private String startYear;
    private String endYear;
    private Integer runtimeMinutes;
    @ElementCollection
    private Set<String> genres;
}
