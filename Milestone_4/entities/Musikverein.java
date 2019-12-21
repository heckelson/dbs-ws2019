package entities;

import java.sql.Date;
import java.time.LocalDate;

public class Musikverein extends Entity {
    private final Gebaeude gebaeude;
    private final String namen;
    private final Date gruendungsdatum;

    public Musikverein(Gebaeude vereinsgebaeude, String namen, Date gruendungsdatum) {
        if(vereinsgebaeude == null) {
            throw new IllegalArgumentException("Entries.Musikverein: Invalid vereinsgebaeude: Vereinsgebaeude cannot be null");
        }
        if (namen.length() > 30 || namen.length() < 1) {
            throw new IllegalArgumentException("Entries.Musikverein: Invalid Namen: Namen has invalid length");
        }
        if (gruendungsdatum.after(Date.valueOf(LocalDate.now()))) {
            throw new IllegalArgumentException("Entries.Musikverein: Invalid Gruendungsdatum: Gruendungsdatum cannot be in the future");
        }

        this.gebaeude = vereinsgebaeude;
        this.namen = namen;
        this.gruendungsdatum = gruendungsdatum;
    }

    public Gebaeude getGebaeude() {
        return gebaeude;
    }

    public String getNamen() {
        return namen;
    }

    public Date getGruendungsdatum() {
        return gruendungsdatum;
    }

    @Override
    public String toString() {
        return gebaeude.toString() + ", " + namen + ", " +  gruendungsdatum;
    }

    @Override
    public String getPrimaryKey() {
        return  "'" + namen + "'";
    }

    @Override
    public String getInsertStatement() {
        return "insert into Musikverein values(" + gebaeude.getPrimaryKey() + ",'" + namen + "',TO_DATE('" + gruendungsdatum + "','YYYY-MM-DD'))";
    }
}
