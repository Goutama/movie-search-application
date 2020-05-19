package com.imdb.movie.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author gbhat on 16/05/2020.
 */
@Data
@Builder
@EqualsAndHashCode(of = {"nconst"})
@Entity
@Table(indexes = {
        @Index(name = "nconst_idx", columnList = "nconst"),
        @Index(name = "primary_name_idx", columnList = "primaryName")}
        )
public class Name {
    @Id
    @SequenceGenerator(name="seq",sequenceName="name_seq", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;
    private String nconst;
    private String primaryName;
    private Integer birthYear;
    private Integer deathYear;
    @ElementCollection
    private List<String> primaryProfessions;
    @ElementCollection
    private List<String> knownForTitles;
}