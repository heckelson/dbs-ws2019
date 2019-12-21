package entities;

public class Spielen extends Entity {
    private final Musiker musiker;
    private final Musikgruppe musikgruppe;

    public Spielen(Musiker musiker, Musikgruppe musikgruppe) {
        if (musiker == null) {
            throw new IllegalArgumentException("Entries.Spielen: Invalid Entries.Musiker: Cannot be null");
        }
        if (musikgruppe == null) {
            throw new IllegalArgumentException("Entries.Spielen: Invalid Entries.Musikgruppe: Cannot be null");
        }

        this.musiker = musiker;
        this.musikgruppe = musikgruppe;
    }

    public Musiker getMusiker() {
        return musiker;
    }
    public Musikgruppe getMusikgruppe() {
        return musikgruppe;
    }

    @Override
    public String toString() {
        return musiker.toString() + ", " + musikgruppe.toString();
    }

    @Override
    public String getPrimaryKey() {
        return musiker.getPrimaryKey() + "," + musikgruppe.getPrimaryKey();
    }

    @Override
    public String getInsertStatement() {
        return "insert into spielen values(" + getPrimaryKey() + ")";
    }
}
