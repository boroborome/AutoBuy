<?php
/*
master从中转站读取验证码识别结果。
*/
$result="upload/verifycode.txt";
echo save();
function save(){
	if(!file_exists($GLOBALS['result'])){
		$txt="";
	}
	else
	{
		$txt =file_get_contents($GLOBALS['result']);
		unlink($GLOBALS['result']);
		$imageFile = "upload/verifycode.jpg";
		if(file_exists($imageFile))
		{
			unlink($imageFile);
		}
	}
	return $txt;
}
?>