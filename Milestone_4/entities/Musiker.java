package entities;

import java.sql.Date;
import java.time.LocalDate;

public class Musiker extends Entity {
    private final Mitglied mitglied;
    private final Date musizierBeginn;

    public Musiker(Mitglied mitglied, Date musizierBeginn) {
        if (mitglied == null) {
            throw new IllegalArgumentException("Entries.Musiker: Invalid Entries.Mitglied: cannot be null");
        }
        if (musizierBeginn.after(Date.valueOf(LocalDate.now()))) {
            throw new IllegalArgumentException("Entries.Musiker: Invalid Musizierbeginn: cannot be in the future");
        }

        this.mitglied = mitglied;
        this.musizierBeginn = musizierBeginn;

    }

    public Mitglied getThisMitglied() {
        return mitglied;
    }

    public Date getMusizierBeginn() {
        return musizierBeginn;
    }

    @Override
    public String toString() {
        return mitglied.toString() + ", " + musizierBeginn;
    }

    @Override
    public String getPrimaryKey() {
        return Integer.toString(mitglied.getMitgliedsnummer());
    }

    @Override
    public String getInsertStatement() {
        return "insert into Musiker values(" + mitglied.getPrimaryKey() + ",TO_DATE('" + musizierBeginn + "', 'YYYY-MM-DD'))";
    }
}
