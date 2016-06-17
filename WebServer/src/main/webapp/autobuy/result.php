<?php
$result="upload/result.txt";
echo save();
function save(){
	$myfile = fopen($GLOBALS['result'], "w") or die("Unable to open file!");
	$txt = $_POST['verifycode'];
	fwrite($myfile, $txt);
	fclose($myfile);

	$imageFile = "upload/verifycode.jpg"
	@unlink($imageFile);
}
?>