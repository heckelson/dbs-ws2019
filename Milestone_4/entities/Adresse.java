package entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Adresse extends Entity {
    private final String ort;
    private final String strasse;
    private final int    hausNr;
    private final String land;

    public final static String ISO3166_codes = "ABW,AFG,AGO,AIA,ALA,ALB,AND,ARE,ARG,ARM,ASM,ATA,ATF,ATG,AUS,AUT," +
            "AZE,BDI,BEL,BEN,BES,BFA,BGD,BGR,BHR,BHS,BIH,BLM,BLR,BLZ,BMU,BOL,BRA,BRB,BRN,BTN,BVT,BWA,CAF,CAN,CCK," +
            "CHE,CHL,CHN,CIV,CMR,COD,COG,COK,COL,COM,CPV,CRI,CUB,CUW,CXR,CYM,CYP,CZE,DEU,DJI,DMA,DNK,DOM,DZA,ECU," +
            "EGY,ERI,ESH,ESP,EST,ETH,FIN,FJI,FLK,FRA,FRO,FSM,GAB,GBR,GEO,GGY,GHA,GIB,GIN,GLP,GMB,GNB,GNQ,GRC,GRD," +
            "GRL,GTM,GUF,GUM,GUY,HKG,HMD,HND,HRV,HTI,HUN,IDN,IMN,IND,IOT,IRL,IRN,IRQ,ISL,ISR,ITA,JAM,JEY,JOR,JPN," +
            "KAZ,KEN,KGZ,KHM,KIR,KNA,KOR,KWT,LAO,LBN,LBR,LBY,LCA,LIE,LKA,LSO,LTU,LUX,LVA,MAC,MAF,MAR,MCO,MDA,MDG," +
            "MDV,MEX,MHL,MKD,MLI,MLT,MMR,MNE,MNG,MNP,MOZ,MRT,MSR,MTQ,MUS,MWI,MYS,MYT,NAM,NCL,NER,NFK,NGA,NIC,NIU," +
            "NLD,NOR,NPL,NRU,NZL,OMN,PAK,PAN,PCN,PER,PHL,PLW,PNG,POL,PRI,PRK,PRT,PRY,PSE,PYF,QAT,REU,ROU,RUS,RWA," +
            "SAU,SDN,SEN,SGP,SGS,SHN,SJM,SLB,SLE,SLV,SMR,SOM,SPM,SRB,SSD,STP,SUR,SVK,SVN,SWE,SWZ,SXM,SYC,SYR,TCA," +
            "TCD,TGO,THA,TJK,TKL,TKM,TLS,TON,TTO,TUN,TUR,TUV,TWN,TZA,UGA,UKR,UMI,URY,USA,UZB,VAT,VCT,VEN,VGB,VIR," +
            "VNM,VUT,WLF,WSM,YEM,ZAF,ZMB,ZWE";

    public Adresse(String ort, String strasse, int hausNr, String land) {
        if (ort.length() > 30 || ort.length() < 1) {
            throw new IllegalArgumentException("Adresse: Ort: Invalid length");
        }
        if (strasse.length() > 30 || strasse.length() < 1) {
            throw new IllegalArgumentException("Adresse: Strasse: Invalid length");
        }
        if (hausNr < 1) {
            throw new IllegalArgumentException("Adresse: Invalid Hausnummer: Cannot be negative");
        }
        if (land.length() != 3 || !isValidCountry(land)) {
            throw new IllegalArgumentException("Adresse: Invalid Land: Not a ISO-3166 alpha-3 country");
        }

        this.ort = ort;
        this.strasse = strasse;
        this.hausNr = hausNr;
        this.land = land;
    }

    // Getters
    public String getOrt() {
        return ort;
    }
    public String getStrasse() {
        return strasse;
    }
    public int getHausNr() {
        return hausNr;
    }
    public String getLand() {
        return land;
    }



    private boolean isValidCountry(String land) {
        String[] ISO_code_array = ISO3166_codes.split(",");

        Set<String> hs = new HashSet<>(Arrays.asList(ISO_code_array));

        return hs.contains(land);
    }

    @Override
    public String toString() {
        return ort + ", " + strasse + ", " + hausNr + ", " + land;
    }

    @Override
    public String getPrimaryKey() {
        return "'" + ort + "','" + strasse + "'," + hausNr + ",'" + land + "'";
    }

    @Override
    public String getInsertStatement() {
        return "insert into Adresse values(" + getPrimaryKey() + ")";
    }

}
