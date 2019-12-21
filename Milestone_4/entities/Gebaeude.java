package entities;

import java.sql.Date;
import java.time.LocalDate;

public class Gebaeude extends Entity {
    private final Adresse adr;
    private final String bezeichnung;
    private final Date baudatum;
    private final String besitzer;

    public Gebaeude(Adresse adr, String bezeichnung, Date baudatum, String besitzer) {
        if (adr == null) {
            throw new IllegalArgumentException("Entries.Gebaeude: Invalid Entries.Adresse: Entries.Adresse cannot be null.");
        }
        if(bezeichnung.length() > 30 || bezeichnung.length() < 1) {
            throw new IllegalArgumentException("Entries.Gebaeude: Invalid Bezeichnung: Bezeichnung has invalid length");
        }
        if(baudatum.after(Date.valueOf(LocalDate.now()))) {
            throw new IllegalArgumentException("Entries.Gebaeude: Invalid Baudatum: Baudatum cannot be null.");
        }
        if(besitzer.length() > 30 || besitzer.length() < 1) {
            throw new IllegalArgumentException("Entries.Gebaeude: Invalid Besitzer: Besitzer has invalid length");
        }

        this.adr = adr;
        this.bezeichnung = bezeichnung;
        this.baudatum = baudatum;
        this.besitzer = besitzer;
    }

    // Getters

    public Adresse getAdr() { return adr; }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Date getBaudatum() {
        return baudatum;
    }

    public String getBesitzer() {
        return besitzer;
    }

    @Override
    public String toString() {
        return adr.toString() + ", " + bezeichnung + ", " + baudatum + ", " + besitzer;
    }

    @Override
    public String getPrimaryKey() {
        return adr.getPrimaryKey() + ",'" + bezeichnung + "'";
    }

    @Override
    public String getInsertStatement() {
        return "insert into Gebaeude values(" + getPrimaryKey() + ",TO_DATE('" + baudatum + "', 'YYYY-MM-DD'),'" + besitzer + "')";
    }

}
