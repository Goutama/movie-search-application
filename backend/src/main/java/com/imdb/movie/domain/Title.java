package com.imdb.movie.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * A Title entity.
 *
 * @author gbhat on 16/05/2020.
 */
@NamedQueries(
        value = {
                @NamedQuery(
                        name = "Title.findTitlesByNconsts",
                        query = "select b from Title b inner join Principal p on b.tconst = p.tconst and p.nconst in :nconst")
        })
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"tconst"})
@Entity
@Table(indexes = {@Index(name = "tconst_idx", columnList = "tconst")})
public class Title {
    @Id
    @SequenceGenerator(name="title_seq_gen",sequenceName="title_seq", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="title_seq_gen")
    private Long id;
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private Boolean isAdult;
    private Integer startYear;
    private Integer endYear;
    private Integer runtimeMinutes;
    @ElementCollection
    private Set<String> genres;
}
