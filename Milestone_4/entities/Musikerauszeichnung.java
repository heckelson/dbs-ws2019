package entities;

public class Musikerauszeichnung extends Entity {
    private final Musiker musiker;
    private final String bezeichnung;

    public Musikerauszeichnung(Musiker musiker, String bezeichnung) {
        if (musiker == null) {
            throw new IllegalArgumentException("Entries.Musikerauszeichnung: Invalid Entries.Musiker: cannot be null");
        }
        if (bezeichnung.isEmpty() || bezeichnung.length() > 30) {
            throw new IllegalArgumentException("Entries.Musikerauszeichnung: Invalid Bezeichnung: invalid length");
        }

        this.musiker = musiker;
        this.bezeichnung = bezeichnung;
    }

    public Musiker getMusiker() {
        return musiker;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    @Override
    public String toString() {
        return musiker.toString() + ", " + bezeichnung;
    }

    @Override
    public String getPrimaryKey() {
        return musiker.getPrimaryKey() + ",'" + bezeichnung +"'";
    }

    @Override
    public String getInsertStatement() {
        return "insert into Musikerauszeichnung values(" + getPrimaryKey() + ")";
    }
}
