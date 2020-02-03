<!DOCTYPE html>

<?php
    require_once('header.php');
    require_once('DBHelper.php');
    require_once('URLManager.php');
    
    $dbh = new DBHelper();
    $uma = new URLManager();
    
?>

<header>
    <div class="centered"><img  style="width:10%;height:10%" src="img/logo_transparent.png" /></div>
    <h1 class="centered">Musikvereins-Verwaltung</h1>
    <h3 class="centered">Hinzufügen:</h3>
    <div class="centered vertical">
        <a href="add-mitglied.php">Mitglied</a>
        <a href="add-musiker.php">Musiker</a>
    </div>
    

</header>

<form method="post" class="searchbar centered">
    <input type="text" value="<?php echo($_POST['search']); ?>" name="search" />
    
    <input class="clickable" type="submit" value="Suchen" />
</form>

<?php
    $dbresult = [];
    
    // search functionality
    if(isset($_POST['search'])) {
        $search_term  = $_POST['search'];
        $dbresult       = $dbh->selectMusikvereinsWhereNameLike($search_term);
    }
    else {
        $dbresult = $dbh->selectAllMusikvereins();
     }
?>

<div class="centered" id="dbresults">
    <?php
        foreach ($dbresult as $res) {
            $suffix = $uma->generateURLSuffix(['musikverein' => $res['NAMEN']]);
            $date = DateTime::createFromFormat('d#M#y', $res['GRUENDUNGSDATUM']);
            $date = $date->format('d.m.Y');
            

            echo("<a href=\"musikverein.php$suffix\" class='floating card'>");
    
            echo('<h3>'. $res['NAMEN'] . '</h3>');
            echo('<p class="indented detail">Gegr&uuml;ndet: ' . $date . '</p>');
                    
            echo('</a>');

        }
    ?>    
</div>

