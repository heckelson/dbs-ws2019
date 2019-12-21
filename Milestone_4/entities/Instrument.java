package entities;

public class Instrument extends Entity {
    private final Musiker musiker;
    private final String instrumentenName;

    public Instrument(Musiker musiker, String instrumentenName) {
        if (musiker == null) {
            throw new IllegalArgumentException("Entries.Instrument: Invalid Entries.Musiker: Entries.Musiker cannot be null");
        }
        if(instrumentenName.isEmpty() || instrumentenName.length() > 30) {
            throw new IllegalArgumentException("Entries.Instrument: Invalid InstrumentenName: invalid length");
        }

        this.musiker = musiker;
        this.instrumentenName = instrumentenName;
    }

    public Musiker getMusiker() {
        return musiker;
    }

    public String getInstrumentenName() {
        return instrumentenName;
    }

    @Override
    public String toString() {
        return musiker.toString() + ", " + instrumentenName;
    }

    @Override
    public String getPrimaryKey() {
        return musiker.getPrimaryKey() + ",'" + instrumentenName + "'";
    }

    @Override
    public String getInsertStatement() {
        return "insert into Instrument values(" + getPrimaryKey() + ")";
    }

}
