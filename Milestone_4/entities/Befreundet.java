package entities;

public class Befreundet extends Entity {
    private final Mitglied m1;
    private final Mitglied m2;

    public Befreundet(Mitglied m1, Mitglied m2) {
        if (m1 == null || m2 == null) {
            throw new IllegalArgumentException("Entries.Befreundet: Invalid Entries.Mitglied: Cannot be null");
        }
        if (m1 == m2) {
            throw new IllegalArgumentException("Entries.Befreundet: Invalid Entries.Mitglied: Cannot befriend themselves");
        }

        this.m1 = m1;
        this.m2 = m2;
    }

    public Mitglied getM1() {
        return m1;
    }

    public Mitglied getM2() {
        return m2;
    }

    @Override
    public String toString() {
        return m1.toString() + ", " + m2.toString();
    }

    @Override
    public String getPrimaryKey() {
        return m1.getPrimaryKey() + "," + m2.getPrimaryKey();
    }

    @Override
    public String getInsertStatement() {
        return "insert into befreundet values(" + getPrimaryKey() + ")";
    }
}
