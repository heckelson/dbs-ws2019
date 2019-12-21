package entities;

public class Ausstattung extends Entity {
    private final Raum raum;
    private final String beschreibung;
    private final int anzahl;

    public Ausstattung (Raum raum, String beschreibung, int anzahl) {
        if (raum == null) {
            throw new IllegalArgumentException("Entries.Ausstattung: Invalid Entries.Raum: Entries.Raum cannot be null");
        }
        if(beschreibung.isEmpty() || beschreibung.length() > 30) {
            throw new IllegalArgumentException("Entries.Ausstattung: Invalid Beschreibung: Invalid length");
        }
        if(anzahl < 1) {
            throw new IllegalArgumentException("Entries.Ausstattung: Invalid Anzahl: Anzahl cannot be < 1");
        }

        this.raum = raum;
        this.beschreibung = beschreibung;
        this.anzahl = anzahl;
    }

    public Raum getRaum() {
        return raum;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public int getAnzahl() {
        return anzahl;
    }

    @Override
    public String toString() {
        return raum.toString() + ", " + beschreibung + ", " + anzahl;
    }

    @Override
    public String getPrimaryKey() {
        return raum.getPrimaryKey() + ",'" + beschreibung + "'";
    }

    @Override
    public String getInsertStatement() {
        return "insert into Ausstattung values(" + getPrimaryKey() + "," + anzahl + ")";
    }
}
