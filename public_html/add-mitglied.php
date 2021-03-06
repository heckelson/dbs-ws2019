<!DOCTYPE html>

<?php
    require_once('header.php');
    require_once('DBHelper.php');
    
    $dbh = new DBHelper();
?>

<header>
    <a href="index.php">Zur&uuml;ck zum Anfang</a>
    <h1 class="centered">Mitglied hinzuf&uuml;gen</h1>
</header>

<form class="centered" method="post" id="submit-new">
    <label>Musikverein:</label>
    <select type="choice"  name="musikverein"  required>
        <?php 
            $dbresult = $dbh->selectAllMusikvereins();
            
            foreach($dbresult as $res) {
                echo ('<option>' . htmlspecialchars($res["NAMEN"]) . '</option>');
            }
        ?>
    </select>

    <label>Namen:</label>
    <input  type="text"     name="namen" maxlength="30" required />
    
    <label>Geburtsdatum:</label>
    <input  type="date"     name="gebdat" required />
    
    <label>Beitrittsdatum:</label>
    <input  type="date"     name="beitrdat"     required />

    <label>Hinzuf&uuml;gen</label>
    <input  type="submit"   value="Hinzuf&uuml;gen"      />
</form>


<?php
    if(isset($_POST["musikverein"])
        && isset($_POST["namen"])
        && isset($_POST["gebdat"])
        && isset($_POST["beitrdat"])) {
        
        $mvnamen =  $_POST["musikverein"];
        $namen =    $_POST["namen"];
        $gebdat =   DateTime::createFromFormat('Y-m-d', $_POST["gebdat"]);
        $beitrdat = DateTime::createFromFormat('Y-m-d', $_POST["beitrdat"]);
        
        $gebdat = $gebdat->format('d-M-Y');
        $beitrdat = $beitrdat->format('d-M-Y');
        
        $dbh->insertIntoMitgliedValues($mvnamen, $namen, $gebdat, $beitrdat);
    
        echo('<p class="success">Mitglied hinzugefügt: ' .
            htmlspecialchars($mvnamen) . 
            ', ' . htmlspecialchars($namen) .
            ', GEB: '   . $gebdat .
            ', BEITR: ' . $beitrdat .
            '</p>');
    }
    
?>
