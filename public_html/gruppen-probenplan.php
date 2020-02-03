<!DOCTYPE html>

<?php
    require_once('header.php');
    require_once('DBHelper.php');
    $dbh = new DBHelper();
?>

<header>
    <a href="index.php">Zur&uuml;ck zum Start</a>
    <h1 class="centered">Gruppen-Probenplan: </h1>
</header>

<?php
    $dbresult = [];
    
    if(isset($_GET['gruppe'])) {
        $gruppe = $_GET['gruppe'];
        $dbresult = $dbh->getProbenWhereGruppeEquals($gruppe);
    }
?>

<div class="centered" id="dbresults">
    <table>
        <tr>
            <th>Zeitpunkt</th>
            <th>Geb&auml;ude</th>
            <th>Raum</th>
        </tr>
        <?php
            foreach ($dbresult as $res) {
                $date = DateTime::createFromFormat("d#M#y H#i#s*A", $res['ZEITPUNKT']);
                $convdate = $date->format('d.m.Y H:i');
                
                echo('<tr class="detail">');
                echo('<td>' . $convdate             . '</td>');
                echo('<td>' . $res['GEBAEUDE']      . '</td>');
                echo('<td class="text-align-r">' . $res['RAUMNR']        . '</td>');
                echo('</tr>');
            }
        ?>
    </table>
</div>

