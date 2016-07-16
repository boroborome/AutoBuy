<?php
/*
将识别的结果保存到中转服务器。
*/
include 'head.php';
echo save();
function save(){
	$myfile = fopen($GLOBALS['result'], "w") or die("Unable to open file!");
	$txt = $_POST['verifycode'];
	fwrite($myfile, $txt);
	fclose($myfile);
	return $txt;
}
?>