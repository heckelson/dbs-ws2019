<?php
class DBHelper {
    const username   = 'username';
    const password   = 'password';
    const connection = 'connection';
    
    private $conn;
    
    public function __construct() {
        try {
            $this->conn = @oci_connect(
                DBHelper::username,
                DBHelper::password,
                DBHelper::connection
            );
            
            if (!$this->conn) {
                die("function __construct: Cannot connect to the database.");
            }
        }
        catch (Exception $e) {
            die("function __construct: {$e->getMessage()}");
        }
    }
    
    public function __destruct () {
        @oci_close($this->conn);
    }
    
    
    //============= MUSIKVEREINE 

    public function selectAllMusikvereins() {
        $query = 'select 
                      *
                  from
                      Musikverein';
        $statement = @oci_parse($this->conn, $query);
        
        @oci_execute($statement);
        @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
        @oci_free_statement($statement);
        
        return $result;
    }
    
    public function selectMusikvereinsWhereNameLike($mvname) {
        
        $query = '
                select
                    *
                from
                    Musikverein
                    natural join Adresse
                where
                    upper(Namen) like :name
                    ';
        $statement = @oci_parse($this->conn, $query);
        
        $mvname = '%' . strtoupper($mvname) . '%';
        
        @oci_bind_by_name($statement, ":name", $mvname);
        
        @oci_execute($statement);
        @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
        @oci_free_statement($statement);
        
        return $result;
    }
    public function selectMusikvereinWhereNameIs($mvname) {
        $query = '
                select
                    *
                from
                    Musikverein
                    natural join Adresse
                where
                    upper(Namen) = :name
                    ';
        $statement = @oci_parse($this->conn, $query);
        
        $mvname = strtoupper($mvname);
        
        @oci_bind_by_name($statement, ":name", $mvname);
        
        @oci_execute($statement);
        @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
        @oci_free_statement($statement);
        
        return $result;
    }
    
    
    // ======= MITGLIEDER
    
    public function selectAllMitglieder($mvnamen) {
        $query = '
                select
                    Mitglied.Namen, Geburtsdatum, Beitrittsdatum
                from
                    Mitglied
                    inner join Musikverein on Musikverein.Namen = Mitglied.MVnamen
                where
                    upper(Musikverein.Namen) = :mvnamen';
        $mvnamen = strtoupper($mvnamen);
        $statement = @oci_parse($this->conn, $query);
        
        @oci_bind_by_name($statement, ':mvnamen', $mvnamen);
        @oci_execute($statement);
        @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
        @oci_free_statement($statement);
        
        return $result;
    }
    
    public function selectMitgliederWhereNameLike($mitgliedsname) {
        $query = '
                select 
                    *
                from 
                    Mitglied
                where
                    upper(Namen) like :name
                ';
        $statement = @oci_parse($this->conn, $query);
        
        $mitgliedsname = '%' . strtoupper($mitgliedsname) . '%';
        
        @oci_bind_by_name($statement, ":name", $mitgliedsname);
        
        @oci_execute($statement);
        @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
        @oci_free_statement($statement);
        
        return $result;
    }
    
    public function insertIntoMitgliedValues($mvnamen, $namen, $gebdatum, $beitrdatum) {
        $query = 'insert into Mitglied values(:mvnamen, null, :namen, :gebdatum, :beitrdatum)';
        
        $statement = @oci_parse($this->conn, $query);
        
        @oci_bind_by_name($statement, ":mvnamen", $mvnamen);
        @oci_bind_by_name($statement, ":namen", $namen);
        @oci_bind_by_name($statement, ":gebdatum", $gebdatum);
        @oci_bind_by_name($statement, ":beitrdatum", $beitrdatum);
        
        @oci_execute($statement);
        @oci_free_statement($statement);
        
    }
    
    
    // ====== MUSIKER
    
    public function selectMusikerWhereNameLike($musikername) {
        $query = '
                select 
                    Mitglied.Namen, Instrumentenname
                from 
                    Musiker
                    natural join Mitglied
                    natural left outer join Instrument
                where
                    upper(Namen) like :name
                ';
        $statement = @oci_parse($this->conn, $query);
        
        
        $musikername = '%' . strtoupper($musikername) . '%';
        @oci_bind_by_name($statement, ":name", $musikername);
        
        @oci_execute($statement);
        @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
        @oci_free_statement($statement);
        
        return $result;
    }
    
    
    public function selectAllMusiker($mvname) {
        $query = '
                select
                    Mitglied.Namen Namen, Instrumentenname
                from
                    Mitglied
                    natural join Musiker
                    natural left outer join Instrument
                    inner join Musikverein on Mitglied.MVNamen = Musikverein.Namen
                where
                    upper(Musikverein.Namen) = :mvname
                ';
        
        $mvname = strtoupper($mvname);
        
        $statement = @oci_parse($this->conn, $query);
        
        @oci_bind_by_name($statement, ':mvname', $mvname);
        @oci_execute($statement);
        @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
        
        @oci_free_statement($statement);
        
        return $result;
    }
    
    public function insertIntoMusikerValues($mvnamen, $namen, $gebdatum,
        $beitrdatum, $musizbeginn) {
        $query1 = 'insert into Mitglied values(:mvnamen, null, :namen, :gebdatum, :beitrdatum) returning Mitgliedsnummer into :mitgliedsnr';
        $statement1 = @oci_parse($this->conn, $query1);
        
        @oci_bind_by_name($statement1, ':mvnamen', $mvnamen);
        @oci_bind_by_name($statement1, ':namen', $namen);
        @oci_bind_by_name($statement1, ':gebdatum', $gebdatum);
        @oci_bind_by_name($statement1, ':beitrdatum', $beitrdatum);
        
        // for later in this function, we're grabbing the Mitgliedsnummer
        @oci_bind_by_name($statement1, ':mitgliedsnr', $mnr, 64);
        
        @oci_execute($statement1);

        $query2 = "insert into Musiker values($mnr, :musizbeginn)";
        $statement2 = @oci_parse($this->conn, $query2);
        
        @oci_bind_by_name($statement2, ':musizbeginn', $musizbeginn);
        
        @oci_execute($statement2);
        
        @oci_free_statement($statement1);
        @oci_free_statement($statement2);
    }
    
    
    // ======== PROBEN
    
    public function selectProbenWhereNameEquals($mname) {
        $futuredate = strtoupper(date('d-M-Y', strtotime('+3 year')));
        
        $query = "
                select
                    Namen, Bezeichnung, Zeitpunkt, Gebaeude, Raumnr
                from
                    proben
                    natural join Musikgruppe
                    natural join spielen
                    natural join Raum
                    natural join Mitglied
                    natural join Musiker
                where
                    upper(Namen) = :name
                    and
                    Zeitpunkt < '$futuredate'
                order by Zeitpunkt
                ";
        
        $statement = @oci_parse($this->conn, $query);
        $mname = strtoupper($mname);
        
        @oci_bind_by_name($statement, ":name", $mname);
        @oci_execute($statement);
        @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
        
        @oci_free_statement($statement);
        
        return $result;
    }
    
    // ======= GRUPPEN
    
    public function selectAllGruppen() {
        $query = '
                select
                    *
                from
                    Musikgruppe
        ';
        
        $statement = @oci_parse($this->conn, $query);
        
        @oci_execute($statement);
        @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
        @oci_free_statement($statement);
        
        return $result;
    }
    
    public function getGruppenWhereMusikvereinEquals($mvname) {    
        $query = '
                select
                    distinct(Bezeichnung), Typ
                from
                    Musikgruppe
                    natural join spielen
                    natural join Musiker
                    natural join Mitglied
                    inner join Musikverein on Mitglied.MVNamen = Musikverein.Namen
                where
                    upper(Musikverein.Namen) = :name
                ';
                
            $statement = @oci_parse($this->conn, $query);
            $mvname = strtoupper($mvname);
            
            @oci_bind_by_name($statement, ":name", $mvname);
            @oci_execute($statement);
            @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
            
            @oci_free_statement($statement);
            return $result;
    }
    
    public function getProbenWhereGruppeEquals($gname) {
        $futuredate = strtoupper(date('d-M-Y', strtotime('+3 year')));
        
        $query = "
                select
                    distinct(Zeitpunkt), Gebaeude, Raumnr
                from
                    proben
                    natural join Musikgruppe
                    natural join spielen
                    natural join Raum
                where
                    upper(Bezeichnung) = :gname
                    and
                    Zeitpunkt < '$futuredate'
                order by Zeitpunkt
                ";
        
        $statement = @oci_parse($this->conn, $query);
        $gname = strtoupper($gname);
        
        @oci_bind_by_name($statement, ":gname", $gname);
        @oci_execute($statement);
        @oci_fetch_all($statement, $result, 0, 0, OCI_FETCHSTATEMENT_BY_ROW);
        
        @oci_free_statement($statement);
        
        return $result;
    }
    
}

?>
