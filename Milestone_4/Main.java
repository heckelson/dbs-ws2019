import entities.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    Manager m = new Manager("<<CONNECTION>>",
                       "<<USERNAME>>",
                        "<<PASSWORD>>");

        List<? extends Entity> entityList = m.generateFullTestSet();
        m.insertIntoDatabase(entityList);

        m.close();


/*      TEST ENTRIES PLEASE IGNORE

        Adresse adr = new Adresse("Ziersdorf", "Kremser Straße", 5, "AUT");
        Gebaeude geb = new Gebaeude(adr, "Musikhaus", Date.valueOf("2019-11-11"), "Gemeinde Ziersdorf");
        Raum ra = new Raum(geb, 1, 100);
        Ausstattung au = new Ausstattung(ra, "Sessel", 12);
        Musikverein mv = new Musikverein(geb, "Trachtenkapelle Ziersdorf", Date.valueOf("1912-09-18"));
        Vereinsauszeichnung v = new Vereinsauszeichnung(mv, "Gold");
        Mitglied mi = new Mitglied(mv, "Alexander Hecke", Date.valueOf("1998-06-03"), Date.valueOf("2007-09-12"));
        Musiker mu = new Musiker(mi, Date.valueOf("2007-09-12"));
        Instrument ins = new Instrument(mu, "Posaune");
        Musikerauszeichnung mau = new Musikerauszeichnung(mu, "Silber");
        Musikgruppe mg = new Musikgruppe("Posazzle", "Quartett");
        Musikstil ms = new Musikstil(mg, "Jazz");
        Spielen sp = new Spielen(mu,mg);
        Termin te = new Termin(Timestamp.valueOf("2021-11-11 11:00:00"));
        Proben pr = new Proben(mg, te, ra);

        System.out.println(adr.getInsertStatement());
        System.out.println(geb.getInsertStatement());
        System.out.println(ra.getInsertStatement());
        System.out.println(au.getInsertStatement());
        System.out.println(mv.getInsertStatement());
        System.out.println(v.getInsertStatement());
        System.out.println(mi.getInsertStatement());
        System.out.println(mu.getInsertStatement());
        System.out.println(ins.getInsertStatement());
        System.out.println(mau.getInsertStatement());
        System.out.println(mg.getInsertStatement());
        System.out.println(ms.getInsertStatement());
        System.out.println(sp.getInsertStatement());
        System.out.println(te.getInsertStatement());
        System.out.println(pr.getInsertStatement());

*/


    }
}
