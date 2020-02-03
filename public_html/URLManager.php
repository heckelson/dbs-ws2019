<?php

class URLManager {
    
    function generateURLSuffix($array) {
        $first = true;
        $str = '?';
        
        foreach($array as $key => $val) {
            if(!$first) {
                $str = $str . '&';
            }
            $str = $str .  $key . '=' . $val;
            $first = false;
        }
        
        return $str;
    }
}

?>
