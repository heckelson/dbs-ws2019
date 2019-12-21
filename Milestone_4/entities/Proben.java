package entities;

public class Proben extends Entity {
    private final Musikgruppe musikgruppe;
    private final Termin zeitpunkt;
    private final Raum raum;

    public Proben(Musikgruppe musikgruppe, Termin zeitpunkt, Raum raum) {
        if (musikgruppe == null) {
            throw new IllegalArgumentException("Entries.Proben: Invalid Entries.Musikgruppe: Cannot be null");
        }
        if (zeitpunkt == null) {
            throw new IllegalArgumentException("Entries.Proben: Invalid Zeitpunkt: Cannot be null");
        }
        if (raum == null) {
            throw new IllegalArgumentException("Entries.Proben: Invalid Entries.Raum: Cannot be null");
        }

        this.musikgruppe = musikgruppe;
        this.zeitpunkt = zeitpunkt;
        this.raum = raum;
    }

    public Musikgruppe getMusikgruppe() {
        return musikgruppe;
    }

    public Termin getZeitpunkt() {
        return zeitpunkt;
    }

    public Raum getRaum() {
        return raum;
    }

    @Override
    public String toString() {
        return musikgruppe.toString() + ", " + zeitpunkt.toString() + ", " + raum.toString();
    }

    @Override
    public String getPrimaryKey() {
        return musikgruppe.getPrimaryKey() + ",TO_TIMESTAMP('" + zeitpunkt.getZeitpunkt() +  "','YYYY-MM-DD HH24:MI:SS.FF')," + raum.getPrimaryKey();
    }

    @Override
    public String getInsertStatement() {
        return "insert into proben values(" + getPrimaryKey() + ")";
    }
}
