<?php
/*
检测中转服务器的验证码是否为最新的（5分钟内）。
*/
include 'head.php';
$verifycode="upload/verifycode.jpg";
echo isnew();
function isnew(){
	global $verifycode;
	if(file_exists($verifycode))
	{
		$span=time() - filemtime($verifycode);
		if($span<300){
			return filemtime($verifycode);
		}
		else
			return -1;
	}

	return -1;
}
?>