<html>
<body>
<?php
 static $x;
$x=$x+1;
?>
<form action="upload.php" method="post"
enctype="multipart/form-data">
<label for="file">Filename: x=<?php echo $x; ?></label>
<input type="file" name="file" id="file" /> 
<br />
<input type="submit" name="submit" value="Submit" />
</form>

</body>
</html>