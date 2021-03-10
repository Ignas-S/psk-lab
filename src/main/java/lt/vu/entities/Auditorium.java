package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(AuditoriumPK.class)
@Table(name = "auditorium", schema = "public", catalog = "nvinefdm")
public class Auditorium {

    @Getter @Setter
    @ManyToOne(targetEntity = MovieTheatre.class)
    @JoinColumn(name = "theatre_id", insertable = false, updatable = false)
    private MovieTheatre theatre;

    @Id
    @Column(name = "theatre_id")
    @Getter @Setter
    private String theatreId;

    @Id
    @Column(name = "nr")
    @Getter @Setter
    private int nr;

    @Basic
    @Column(name = "seats")
    @Getter @Setter
    private int seats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auditorium that = (Auditorium) o;

        if (nr != that.nr) return false;
        if (seats != that.seats) return false;
        if (theatreId != null ? !theatreId.equals(that.theatreId) : that.theatreId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = theatreId != null ? theatreId.hashCode() : 0;
        result = 31 * result + nr;
        result = 31 * result + seats;
        return result;
    }
}
