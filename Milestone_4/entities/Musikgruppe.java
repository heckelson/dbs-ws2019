package entities;

public class Musikgruppe extends Entity {
    private final String gruppenbezeichnung;
    private final String typ;

    public Musikgruppe(String gruppenbezeichnung, String typ) {
        if (gruppenbezeichnung.isEmpty() || gruppenbezeichnung.length() > 30) {
            throw new IllegalArgumentException("Entries.Musikgruppe: Invalid Gruppenbezeichnung: invalid length");
        }
        if (typ.isEmpty() || typ.length() > 30) {
            throw new IllegalArgumentException("Entries.Musikgruppe: Invalid Typ: invalid length");
        }

        this.gruppenbezeichnung = gruppenbezeichnung;
        this.typ = typ;
    }

    public String getGruppenbezeichnung() {
        return gruppenbezeichnung;
    }

    public String getTyp() {
        return typ;
    }

    @Override
    public String toString() {
        return gruppenbezeichnung + ", " + typ;
    }

    @Override
    public String getPrimaryKey() {
        return "'" + gruppenbezeichnung + "'";
    }

    @Override
    public String getInsertStatement() {
        return "insert into Musikgruppe values(" + getPrimaryKey() + ",'" + typ + "')";
    }

}
