package entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Termin extends Entity {
    private final Timestamp zeitpunkt;

    public Termin(Timestamp zeitpunkt) {
        if (zeitpunkt == null) {
            throw new IllegalArgumentException("Entries.Termin: Invalid Zeitpunkt: Cannot be null");
        }
        if (zeitpunkt.before(Date.valueOf(LocalDate.now()))) {
            throw new IllegalArgumentException("Entries.Termin: Invalid Zeitpunkt: Cannot be in the past");
        }

        this.zeitpunkt = zeitpunkt;
    }

    public Timestamp getZeitpunkt() {
        return zeitpunkt;
    }

    @Override
    public String toString() {
        return zeitpunkt.toString();
    }

    @Override
    public String getPrimaryKey() {
        return zeitpunkt.toString();
    }

    @Override
    public String getInsertStatement() {
        return "insert into Termin values(TO_TIMESTAMP('" + zeitpunkt + "','YYYY-MM-DD HH24:MI:SS.FF'))";
    }
}
