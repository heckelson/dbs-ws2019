package entities;

public class Raum extends Entity {
    private final Gebaeude gebaeude;
    private final int raumnr;
    private final int kapazitaet;

    public Raum(Gebaeude geb, int raumnr, int kapazitaet) {
        if (geb == null) {
            throw new IllegalArgumentException("Entries.Raum: Invalid Entries.Gebaeude: Gebauede cannot be null.");
        }
        if (kapazitaet < 0) {
            throw new IllegalArgumentException("Entries.Raum: Invalid kapazitaet: Kapazitaet cannot be negative.");
        }

        this.gebaeude = geb;
        this.raumnr = raumnr;
        this.kapazitaet = kapazitaet;
    }

    public Gebaeude getGebaeude() {
        return gebaeude;
    }

    public int getRaumnr() {
        return raumnr;
    }

    public int getKapazitaet() {
        return kapazitaet;
    }

    @Override
    public String toString() {
        return gebaeude.toString() + ", " + raumnr + ", " + kapazitaet;
    }

    @Override
    public String getPrimaryKey() {
        return "'" + gebaeude.getBezeichnung() + "'," + raumnr;
    }

    @Override
    public String getInsertStatement() {
        return "insert into raum values(" + gebaeude.getPrimaryKey() + "," + raumnr + "," + kapazitaet + ")";
    }
}
