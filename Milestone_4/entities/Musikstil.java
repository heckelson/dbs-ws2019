package entities;

public class Musikstil extends Entity {
    private final Musikgruppe gruppe;
    private final String beschreibung;

    public Musikstil (Musikgruppe gruppe, String beschreibung) {
        if (gruppe == null) {
            throw new IllegalArgumentException("Entries.Musikstil: Invalid Entries.Musikgruppe: Cannot be null");
        }
        if (beschreibung.isEmpty() || beschreibung.length() > 30) {
            throw new IllegalArgumentException("Entries.Musikstil: Invalid Beschreibung: Invalid length");
        }
        this.gruppe = gruppe;
        this.beschreibung = beschreibung;
    }

    public Musikgruppe getGruppe() {
        return gruppe;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    @Override
    public String toString() {
        return gruppe.toString() + ", " + beschreibung;
    }

    @Override
    public String getPrimaryKey() {
        return gruppe.getPrimaryKey() + ",'" + beschreibung + "'";
    }

    @Override
    public String getInsertStatement() {
        return "insert into Musikstil values(" + getPrimaryKey() + ")";
    }
}
