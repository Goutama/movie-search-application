package com.imdb.movie.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * A Name entity.
 *
 * @author gbhat on 16/05/2020.
 */
@NamedNativeQueries(
        value = {
                @NamedNativeQuery(
                        name = "Name.findDegreesOfSeparation",
                        query = "with recursive rec(nconst, distance) as (\n" +
                                "    select n.nconst, 0\n" +
                                "    from name as n\n" +
                                "    where n.nconst = :sourceNameId\n" +
                                "    union\n" +
                                "    select n.nconst, rec.distance + 1\n" +
                                "    from rec\n" +
                                "    join principal as p1 on (rec.nconst = p1.nconst)\n" +
                                "    join title as t on (p1.tconst = t.tconst)\n" +
                                "    join principal as p2 on (t.tconst = p2.tconst)\n" +
                                "    join name as n on (p2.nconst = n.nconst)\n" +
                                "    where rec.nconst <> n.nconst and rec.distance + 1 <= 6\n" +
                                ")\n" +
                                ", dist (nconst, distance) as (\n" +
                                "    select nconst, min(distance) as distance\n" +
                                "    from rec\n" +
                                "    group by nconst\n" +
                                ")\n" +
                                "select dist.distance\n" +
                                "from name as n\n" +
                                "left join dist on (n.nconst = dist.nconst)\n" +
                                "where n.nconst = :targetNameId")
        })
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
    @SequenceGenerator(name = "seq", sequenceName = "name_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
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