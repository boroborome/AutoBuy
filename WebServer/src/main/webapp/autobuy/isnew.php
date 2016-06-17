<?php
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