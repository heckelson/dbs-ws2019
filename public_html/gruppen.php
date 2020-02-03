<!DOCTYPE html>

<?php
    require_once('header.php');
    require_once('DBHelper.php');
    require_once('URLManager.php');
    
    $dbh = new DBHelper();
    $uma = new URLManager();
?>

<header>
    <a href="index.php">Zur&uuml;ck zum Anfang</a>
    <h1 class="centered">Gruppen</h1>
</header>

<div class="centered" id="dbresults">
    <?php
        $dbresult;
        
        if(isset($_GET['musikverein'])) {
            $mv = $_GET['musikverein'];
            $dbresult = $dbh->getGruppenWhereMusikvereinEquals($mv);
        }
        
        foreach($dbresult as $res) {
        $urlsuffix = $uma->generateURLSuffix(['musikverein' => $_GET['musikverein'],
            'gruppe' => $res['BEZEICHNUNG']]);
        
                echo("<a href=\"gruppen-probenplan.php$urlsuffix\" class=\"floating card\">");
                echo('<h3>' . htmlspecialchars($res['BEZEICHNUNG']) . '</h3>');
                echo('<p class="indented detail">Typ: ' . htmlspecialchars($res["TYP"]) . '</p>');
                
                echo('</a>');
        }
    ?>
</div>

