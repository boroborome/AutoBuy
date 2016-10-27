<?php
/*
master从中转站读取验证码识别结果。
*/
include 'head.php';
echo read();
function read(){
	$result="upload/".$_GET["file"].".txt";
	if(!file_exists($result)){
		$txt="";
	}
	else
	{
		$txt =file_get_contents($result);
		unlink($result);
		$imageFile = "upload/".$_GET["file"].".jpg";
		if(file_exists($imageFile))
		{
			unlink($imageFile);
		}
	}
	return $txt;
}
?>