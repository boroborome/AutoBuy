<?php
$result="upload/result.txt";
echo read()."successful";
function read(){
	$myfile = fopen($GLOBALS['result'], "r") or die("Unable to open file!");
	$txt = fread($myfile,filesize($GLOBALS['result']));
	fclose($myfile);
	return $txt;
}
?>