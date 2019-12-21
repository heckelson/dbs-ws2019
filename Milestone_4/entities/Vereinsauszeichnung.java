package entities;

public class Vereinsauszeichnung extends Entity {
    private final Musikverein mv;
    private final String bezeichnung;

    public Vereinsauszeichnung (Musikverein mv, String bezeichnung) {
        if (mv == null) {
            throw new IllegalArgumentException("Entries.Vereinsauszeichnung: Invalid Entries.Musikverein: Cannot be null");
        }
        if (bezeichnung.isEmpty() || bezeichnung.length() > 30) {
            throw new IllegalArgumentException("Entries.Vereinsauszeichnung: Invalid Bezeichnung: invalid length");
        }
        this.mv = mv;
        this.bezeichnung = bezeichnung;
    }

    public Musikverein getMv() {
        return mv;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    @Override
    public String toString() {
        return mv.toString() + ", " + bezeichnung;
    }

    @Override
    public String getPrimaryKey() {
        return "'" + mv.getNamen() + "','" + bezeichnung + "'";
    }

    @Override
    public String getInsertStatement() {
        return "insert into Vereinsauszeichnung values(" + getPrimaryKey() + ")";
    }
}
