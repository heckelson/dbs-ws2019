import entities.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private final RandomDataGenerator rdg;
    private final DBAccessor dba;

    private final int RAUM_AMOUNT                   = 20;
    private final int AUSSTATTUNG_AMOUNT            = 10;
    private final int VEREINSAUSZEICHNUNG_AMOUNT    = 50;
    private final int MITGLIEDER_AMOUNT             = 2500;
    private final int MUSIKER_AMOUNT                = 1300;
    private final int MUSIKGRUPPE_AMOUNT            = 3;
    private final int TERMIN_AMOUNT                 = 2400;


    public Manager(String url, String username, String password) {
        rdg = new RandomDataGenerator();
        dba = new JDBC_DBAccessor(url, username, password);
    }


    // I imagine there could be a bit of optimization potential here
    public List<? extends Entity> generateFullTestSet() {



        System.out.println("Generating Adresse...");
        Adresse adresse = rdg.getAdresse();

        System.out.println("Generating Gebaeude...");
        Gebaeude gebaeude = rdg.getGebaeude(adresse);

        System.out.println("Generating Räume for the Gebaeude");
        List<Raum> raumList = new ArrayList<>();
        // add 20 Raeume to the Gebaeude


        for (int i = 0; i < RAUM_AMOUNT; i++) {
            raumList.add(rdg.getRaum(gebaeude));
        }

        System.out.println("Generating Ausstattung for each Raum...");
        List<Ausstattung> ausstattungList = new ArrayList<>();
        for(Raum r : raumList) {
            for (int i = 0; i < AUSSTATTUNG_AMOUNT; i++) {
                ausstattungList.add(rdg.getAusstattung(r));
            }
        }

        System.out.println("Generating Musikverein...");
        // one Musikverein moves into the Gebaeude
        Musikverein mv = rdg.getMusikverein(gebaeude);

        System.out.println("Generating Vereinsauszeichnungen...");
        List<Vereinsauszeichnung> vereinsauszeichnungList = new ArrayList<>();
        for (int i = 0; i < VEREINSAUSZEICHNUNG_AMOUNT; i++) {
            vereinsauszeichnungList.add(rdg.getVereinsauszeichnung(mv));
        }

        System.out.println("Generating Mitglieder...");
        List<Mitglied> mitgliedList = new ArrayList<>();
        for (int i = 0; i < MITGLIEDER_AMOUNT; i++) {
            mitgliedList.add(rdg.getMitglied(mv));
        }

        System.out.println("Generating Friendships...");
        List<Befreundet> befreundetList = new ArrayList<>();
        // lots of friendships in a Musikverein
        for(int i = 0; i < (MITGLIEDER_AMOUNT-1); i++) {
            befreundetList.add(rdg.getBefreundet(mitgliedList.get(i), mitgliedList.get(i+1)));
            befreundetList.add(rdg.getBefreundet(mitgliedList.get(i+1), mitgliedList.get(i)));
        }

        System.out.println("Generating Musiker...");
        List<Musiker> musikerList = new ArrayList<>();
        // 130 Mitglieder are musikers.
        for (int i = 0; i < MUSIKER_AMOUNT; i++) {
            musikerList.add(rdg.getMusiker(mitgliedList.get(i)));
        }

        System.out.println("Generating Musikerauszeichnungen...");
        List<Musikerauszeichnung> mAuszeichnungen = new ArrayList<>();
        for (int i = 0; i < (MUSIKER_AMOUNT / 2); i++) {
            for (int j = 0; j < 5; j++) {
                mAuszeichnungen.add(rdg.getMusikerAuszeichnung(musikerList.get(i)));
            }
        }

        System.out.println("Generating Instrumente...");
        List<Instrument> instrumentList = new ArrayList<>();
        for (int i = 0; i < MUSIKER_AMOUNT; i++) {
            instrumentList.add(rdg.getInstrument(musikerList.get(i)));
        }
        System.out.println("Generating secondary Instrumente...");
        // the first half 2 instruments
        for (int i = 0; i < (MUSIKER_AMOUNT / 2); i++) {
            instrumentList.add(rdg.getInstrument(musikerList.get(i)));
        }

        System.out.println("Generating Musikgruppen...");
        // 3 groups should suffice
        List<Musikgruppe> musikgruppeList = new ArrayList<>();
        for (int i = 0; i < MUSIKGRUPPE_AMOUNT; i++) {
            musikgruppeList.add(rdg.getMusikgruppe());
        }

        System.out.println("Generating Musikstile...");
        List<Musikstil> musikstilList = new ArrayList<>();
        // each Musigruppe has 2 Stile
        for (int i = 0; i < MUSIKGRUPPE_AMOUNT; i++) {
            musikstilList.add(rdg.getMusikstil(musikgruppeList.get(i)));
            musikstilList.add(rdg.getMusikstil(musikgruppeList.get(i)));
        }

        System.out.println("Generating spielen...");
        List<Spielen> spielenList = new ArrayList<>();
        // everyone spiels in the first Gruppe
        for (int i = 0; i < MUSIKGRUPPE_AMOUNT; i++) {
            spielenList.add(rdg.getSpielen(musikerList.get(i), musikgruppeList.get(0)));
        }

        for (int i = 0; i < (MUSIKER_AMOUNT / 2 - 1); i++) {
            spielenList.add(rdg.getSpielen(musikerList.get(i), musikgruppeList.get(1)));
        }
        // WATCH OUT: i starts at 70 here!
        for (int i = (MUSIKER_AMOUNT / 2); i < MUSIKER_AMOUNT; i++) {
            spielenList.add(rdg.getSpielen(musikerList.get(i), musikgruppeList.get(2)));
        }

        System.out.println("Generating Termine...");
        List<Termin> terminList = new ArrayList<>();
        for (int i = 0; i < TERMIN_AMOUNT; i++) {
            terminList.add(rdg.getTermin());
        }

        System.out.println("Generating proben...");
        List<Proben> probenList = new ArrayList<>();
        // WATCH OUT: Watch the indices!

        for (int i = 0; i < MUSIKGRUPPE_AMOUNT; i++) {
            for (int j = i * (TERMIN_AMOUNT / MUSIKGRUPPE_AMOUNT); j < (i + 1) * (TERMIN_AMOUNT / MUSIKGRUPPE_AMOUNT); j++) {
                probenList.add(rdg.getProben(musikgruppeList.get(i),terminList.get(j), raumList.get(i)));
            }
        }

        /*
        Hopefully the for-for loop replaces the following lines ...

        for (int i = 0; i < (TERMIN_AMOUNT / MUSIKGRUPPE_AMOUNT); i++) {
            probenList.add(rdg.getProben(musikgruppeList.get(0),terminList.get(i), raumList.get(0)));
        }

        for (int i = (TERMIN_AMOUNT / MUSIKGRUPPE_AMOUNT); i < 2 * (TERMIN_AMOUNT / MUSIKGRUPPE_AMOUNT); i++) {
            probenList.add(rdg.getProben(musikgruppeList.get(1),terminList.get(i), raumList.get(1)));
        }

        for (int i = 2 * (TERMIN_AMOUNT / MUSIKGRUPPE_AMOUNT); i < TERMIN_AMOUNT; i++) {
            probenList.add(rdg.getProben(musikgruppeList.get(2),terminList.get(i), raumList.get(2)));
        }
        */

        System.out.println("Generating big list of entries from all other lists...");
        List<Entity> allEntities = new ArrayList<>();
        // a big list
        allEntities.add(adresse);
        allEntities.add(gebaeude);
        allEntities.addAll(raumList);
        allEntities.addAll(ausstattungList);
        allEntities.add(mv);
        allEntities.addAll(vereinsauszeichnungList);
        allEntities.addAll(mitgliedList);
        allEntities.addAll(befreundetList);
        allEntities.addAll(musikerList);
        allEntities.addAll(mAuszeichnungen);
        allEntities.addAll(instrumentList);
        allEntities.addAll(musikgruppeList);
        allEntities.addAll(musikstilList);
        allEntities.addAll(spielenList);
        allEntities.addAll(terminList);
        allEntities.addAll(probenList);

        System.out.println("Done!");

        return allEntities;
    }

    public void insertIntoDatabase(List <? extends Entity> entryList) {
        dba.setAutoCommit(false);

        for(Entity e : entryList) {
            String query = e.getInsertStatement();
            executeQuery(query);
        }

        dba.commit();
        dba.setAutoCommit(true);
    }

    public ResultSet executeQuery(String query) {
        return dba.executeQuery(query);
    }


    public void close() {
        dba.close();
    }
}
