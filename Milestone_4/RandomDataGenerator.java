import entities.*;

public class RandomDataGenerator implements DataGenerator {
    final RandomSequenceGenerator rsg = new RandomSequenceGenerator();

    public RandomDataGenerator() {}

    // dependencies are given through the parameters to enable a 1:n relationship easily

    public Adresse getAdresse() { // has no dependencies
        return new Adresse(rsg.nextString(10,30), rsg.nextString(10,30), rsg.nextInt(1,100), rsg.nextLand());
    }

    public Gebaeude getGebaeude(Adresse adr) {
        return new Gebaeude(adr, rsg.nextString(10,30),rsg.nextDate(1970,2018), rsg.nextString(10,30));
    }

    public Raum getRaum(Gebaeude gebaeude) {
        return new Raum(gebaeude, rsg.nextInt(1, Integer.MAX_VALUE), rsg.nextInt(1,Integer.MAX_VALUE));
    }

    public Ausstattung getAusstattung(Raum raum) {
        return new Ausstattung(raum, rsg.nextString(1,30), rsg.nextInt(1,1000));
    }

    public Musikverein getMusikverein(Gebaeude geb) {
        return new Musikverein(geb, rsg.nextString(10,30), rsg.nextDate(1970, 2018));
    }

    public Vereinsauszeichnung getVereinsauszeichnung(Musikverein mv) {
        return new Vereinsauszeichnung(mv, rsg.nextString(1,30));
    }

    public Mitglied getMitglied(Musikverein mv) {
        return new Mitglied(mv, rsg.nextString(3,30),rsg.nextDate(1970,1989), rsg.nextDate(1990,2018));
    }

    public Befreundet getBefreundet(Mitglied m1, Mitglied m2) { return new Befreundet(m1, m2); }

    public Musiker getMusiker(Mitglied mitglied) {
        return new Musiker(mitglied, rsg.nextDate(1990, 2018));
    }

    public Musikerauszeichnung getMusikerAuszeichnung(Musiker musiker) {
        return new Musikerauszeichnung(musiker, rsg.nextString(10,30));
    }

    public Instrument getInstrument(Musiker musiker) {
        return new Instrument(musiker, rsg.nextString(1,30));
    }

    public Musikgruppe getMusikgruppe() {
        return new Musikgruppe(rsg.nextString(3,30), rsg.nextString(5,30));
    }

    public Musikstil getMusikstil(Musikgruppe musikgruppe) {
        return new Musikstil(musikgruppe, rsg.nextString(10,30));
    }

    public Spielen getSpielen(Musiker musiker, Musikgruppe musikgruppe) {
        return new Spielen(musiker, musikgruppe);
    }

    public Termin getTermin() {
        return new Termin(rsg.nextTimestamp(2021,2050));
    }

    public Proben getProben(Musikgruppe musikgruppe, Termin zeitpunkt, Raum raum) {
        return new Proben(musikgruppe, zeitpunkt, raum);
    }


    @Override
    public Entity getEntry(String type) {
        return null;
    }
}
