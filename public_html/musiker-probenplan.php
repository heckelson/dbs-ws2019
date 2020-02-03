<!DOCTYPE html>

<?php
    require_once('header.php');
    require_once('DBHelper.php');
    
    $dbh = new DBHelper();
?>

<header>
    <a href="index.php">Zur&uuml;ck zum Start</a>
    <h1 class="centered">Musiker-Probenplan</h1>
</header>

<?php
    $result = [];
    
    if(isset($_GET['musiker'])) {
        $musiker = $_GET['musiker'];
        $result = $dbh->selectProbenWhereNameEquals($musiker);
    }
?>

<div class="centered" id="dbresults">
    <table>
        <tr>
            <th>Zeitpunkt</th>
            <th>Geb&auml;ude</th>
            <th>Raum</th>
            <th>Gruppe</th>
        </tr>
        <?php
            foreach ($result as $res) {
                $date = DateTime::createFromFormat("d#M#y H#i#s*A", $res['ZEITPUNKT']);
                $date = $date->format('d.m.Y H:i');
                
                echo('<tr class="detail">');
                echo('<td>' . $date                 . '</td>');
                echo('<td>' . $res['GEBAEUDE']      . '</td>');
                echo('<td class="text-align-r">' . $res['RAUMNR'] . '</td>');
                echo('<td>' . $res['BEZEICHNUNG']   . '</td>');
                echo('</tr>');
            }
        ?>
    </table>
</div>
