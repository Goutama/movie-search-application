package com.imdb.movie.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author gbhat on 16/05/2020.
 */
@Data
@Builder
@EqualsAndHashCode(of = {"id", "titleId", "ordering"})
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"titleId", "ordering"}
        )},
        indexes = {
                @Index(name = "title_id_idx", columnList = "titleId")
        })
public class Aka {
    @Id
    @SequenceGenerator(name="seq",sequenceName="aka_seq", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;
    private String titleId;
    private Long ordering;
    private String title;
    private String region;
    private String language;
    @ElementCollection
    private List<String> types;
    @ElementCollection
    private List<String> attributes;
    private boolean isOriginalTitle;

}
