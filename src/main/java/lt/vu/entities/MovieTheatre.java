package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "movie_theatre", schema = "public", catalog = "nvinefdm")
@NamedQueries({
        @NamedQuery(name = "MovieTheatre.allTheatres", query = "select t from MovieTheatre as t")
})
@Getter @Setter
public class MovieTheatre {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "name")
    private String name;

    @Column(name = "v")
    @Version
    private Long v;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "theatre_movies",
            joinColumns = @JoinColumn(name = "theatre_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_eidr"))
    private Set<Movie> movies;

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    @OneToMany(targetEntity = Auditorium.class, mappedBy = "theatre")
    private Set<Auditorium> auditoriums;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieTheatre that = (MovieTheatre) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }
}
