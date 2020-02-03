<!DOCTYPE html>

<?php 
    require_once('header.php');
    require_once('DBHelper.php');
    $dbh = new DBHelper();
?>

<header>
    <a href="index.php">Zur&uuml;ck zum Anfang</a>
    <h1 class="centered">Mitglieder</h1>
</header>

<form method="post" class="searchbar centered">
    <input type="text" value="<?php echo($_POST['search']); ?>" name="search" />
    <input class="clickable" type="submit" value="Suchen" />
</form>

<?php
    $result;
    
    // show all if the search didn't get POSTed.
    if(isset($_POST['search'])) {
        $search_term  = $_POST['search'];
        $result       = $dbh->selectMitgliederWhereNameLike($search_term);
        
        $resultsize = sizeof($result);
        echo("You searched for: " . htmlspecialchars($search_term));
    }
    else {
        $result = $dbh->selectAllMitglieder($_GET['musikverein']);
     }
?>

<div class="centered" id="dbresults">
    <?php
        foreach ($result as $res) {
            $gebdatum = DateTime::createfromFormat('d#M#y', $res['GEBURTSDATUM']);
            $gebdatum = $gebdatum->format('m.d.Y');
        
            $beitrdatum = DateTime::createfromFormat('d#M#y', $res['BEITRITTSDATUM']);
            $beitrdatum = $beitrdatum->format('m.d.Y');
        
        
            echo('<a class="floating card">');
            
            echo('<h3>' . htmlspecialchars($res['NAMEN']) . '</h3>');
            echo('<p class="indented detail">Geburtsdatum: ' . $gebdatum   . '</p>');
            echo('<p class="indented detail">Beitritt:     ' . $beitrdatum . '</p>');
                    
            echo('</a>');
        }
    ?>
</div>
