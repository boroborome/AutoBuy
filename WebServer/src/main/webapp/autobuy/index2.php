<html>
<body>
<?php
static $x=1;
$x=$x+1;
?>
<form action="upload.php" method="post"
enctype="multipart/form-data">
<label for="file">Filename: x=<?php global $x; echo $x; ?></label>
<input type="file" name="file" id="file" /> 
<br />
<input type="submit" name="submit" value="Submit" />
</form>

</body>
</html>