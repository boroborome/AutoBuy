<?php
/*
master上传本地验证码图片到中转站。
*/
include 'head.php';
$dir="upload/";
$resul="upload/verifycode.txt";
if ((($_FILES["file"]["type"] == "image/gif")
|| ($_FILES["file"]["type"] == "image/jpeg")
|| ($_FILES["file"]["type"] == "image/pjpeg"))
&& ($_FILES["file"]["size"] < 20480))
  {
  if ($_FILES["file"]["error"] > 0)
    {
    echo "Return Code: " . $_FILES["file"]["error"] . "<br />";
    }
  else
    {
    echo "Upload: " . $_FILES["file"]["name"] . "<br />";
    echo "Type: " . $_FILES["file"]["type"] . "<br />";
    echo "Size: " . ($_FILES["file"]["size"] / 1024) . " Kb<br />";
    echo "Temp file: " . $_FILES["file"]["tmp_name"] . "<br />";
    $fileName=$dir.$_FILES["file"]["name"];
		if(!file_exists($dir))
		{
			mkdir($dir);
		}
    if (file_exists($fileName))
      {
	      if (!unlink($fileName))
	  		{
	  			echo ("Error deleting $fileName<br />");
	  		}
			else
	  		{
	  			echo ("Deleted $fileName<br />");
	  		}
      }
      move_uploaded_file($_FILES["file"]["tmp_name"],
      $fileName);
      if(file_exists($resul)){
        unlink($resul);
      }
      echo "Stored in:".$fileName;
    }
  }
else
  {
  echo "Invalid file";
  }
?>