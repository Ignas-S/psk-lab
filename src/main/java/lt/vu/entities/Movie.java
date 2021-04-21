package lt.vu.entities;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "Movie.allMovies", query = "select m from Movie as m"),
        @NamedQuery(name = "Movie.otherMovies", query = "select m from Movie as m where m.eidr not in :exclude")
})
@Table(name = "movie", schema = "public", catalog = "nvinefdm")
public class Movie {
    @Id
    @Column(name = "eidr")
    private String eidr;

    @Basic
    @Column(name = "director")
    private String director;


    @Basic
    @Column(name = "name")
    private String name;


    @Basic
    @Column(name = "year")
    private int year;


    @Basic
    @Column(name = "genres")
    private String genres;


    @Basic
    @Column(name = "duration")
    private int duration;

    @ManyToMany(
            mappedBy = "movies", targetEntity = lt.vu.entities.MovieTheatre.class,
            fetch = FetchType.EAGER, cascade = CascadeType.PERSIST
    )
    private Set<MovieTheatre> movieTheatres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie that = (Movie) o;

        if (!Objects.equals(eidr, that.eidr)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eidr);
    }

    public Movie(String eidr, String director, String name, int year, String genres, int duration) {
        this.eidr = eidr;
        this.director = director;
        this.name = name;
        this.year = year;
        this.genres = genres;
        this.duration = duration;
    }
    public Movie() {}
}
