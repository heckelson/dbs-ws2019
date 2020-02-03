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
    <h1 class="centered">Musikverein:
        <?php
            if(isset($_GET['musikverein'])) {
                echo(htmlspecialchars($_GET['musikverein']));
            }
        ?>
    </h1>
    
    <?php
        $urlsuffix = $uma->generateURLSuffix([
            'musikverein'=> htmlspecialchars($_GET['musikverein'])
        ]);
        
        $dbresult = $dbh->selectMusikvereinWhereNameIs($_GET['musikverein'])[0];
    ?>
    
    <h3 class="centered">Liste anzeigen:</h3>
    <div class="centered vertical">
        <a href="./musiker.php<?php    echo($urlsuffix); ?>" >Musiker</a>
        <a href="./gruppen.php<?php    echo($urlsuffix); ?>" >Gruppen</a>
        <a href="./mitglieder.php<?php echo($urlsuffix); ?>" >Mitglieder</a>
    </div>
    
</header>

<div class="floating element">
    <?php
        $date = DateTime::createFromFormat('d#M#y', $dbresult['GRUENDUNGSDATUM']); 
        $convdate = $date->format('d.m.Y');
    ?>
    <h3>Adresse:</h3>
    <p class="indented detail">
        <?php 
            echo(   $dbresult['STRASSE'] . ' ' .
                    $dbresult['HAUSNUMMER'] . ', ' . 
                    $dbresult['ORT'] . " ({$dbresult['LAND']})"); 
        ?>
    </p>
    <h3>Gegr&uuml;ndet:</h3>
    <p class="indented detail"><?php echo($convdate); ?></p>
    
</div>
    

