<!DOCTYPE html>

<?php
    require_once('header.php');
    require_once('DBHelper.php');
    require_once('URLManager.php');
    
    $dbh = new DBHelper();
    $uma = new URLManager();
?>

<header>
    <a href="./index.php">Zur&uuml;ck zum Anfang</a>
    <h1 class="centered">Musiker</h1>
</header>

<form method="post" class="searchbar centered">
    <input type="text" value="<?php echo($_POST['search']); ?>" name="search" />
    <input class="clickable" type="submit" value="Suchen" />
</form>

<?php
    $dbresult;
    // show all if the search didn't get POSTed.
    if(isset($_POST['search'])) {
        $search_term  = $_POST['search'];
        $dbresult       = $dbh->selectMusikerWhereNameLike($search_term);
    }
    else {
        $dbresult = $dbh->selectAllMusiker($_GET['musikverein']);
     }
     
    if(isset($_GET['musikverein'])) {
        $mvnamen = $_GET['musikverein'];
    }
?>

<div class="centered" id="dbresults">
    <?php
        
        // creating a Hashmap with the format:
        // key:   String (NAMEN)
        // value: Hashmap of Strings (INSTRUMENTENNAME)
        // e.g. $namesAndInstruments = ['Alexander' => ['Posaune', 'Klavier'], 'Florian' => ['Posaune']]
        
        $namesAndInstruments = [];
        foreach ($dbresult as $res) {
            $namesAndInstruments[$res['NAMEN']] = [];
        }
        foreach ($dbresult as $res) {
            array_push($namesAndInstruments[$res['NAMEN']], $res['INSTRUMENTENNAME']);
        }
        
        foreach($namesAndInstruments as $namen => $instrumente) {
            $urlsuffix = $uma->generateURLSuffix(['musikverein' => $mvnamen, 'musiker' => $namen]);
            
            echo("<a class=\"floating card\" href=\"musiker-probenplan.php$urlsuffix\">");
            
            echo('<h3>' . htmlspecialchars($namen) . '</h3>');
            foreach($instrumente as $instrument) {
                if ($instrument != "") {
                    echo('<p class="indented detail">Instrument: ' . htmlspecialchars($instrument) . '</p>');
                }
            }
        
            echo('</a>');
        }
    ?>
</div>
