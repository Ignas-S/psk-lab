package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

public class AuditoriumPK implements Serializable {

    @Column(name = "theatre_id")
    @Id
    @Getter @Setter
    private String theatreId;
    @Column(name = "nr")
    @Id
    @Getter @Setter
    private int nr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditoriumPK that = (AuditoriumPK) o;

        if (nr != that.nr) return false;
        if (theatreId != null ? !theatreId.equals(that.theatreId) : that.theatreId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = theatreId != null ? theatreId.hashCode() : 0;
        result = 31 * result + nr;
        return result;
    }
}
