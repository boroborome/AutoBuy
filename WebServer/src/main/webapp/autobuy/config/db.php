<?php
	class DB
	{	
		 function connect(){
			$dbc= new mysqli("localhost","root","","autobuy");
			 if(!$dbc)  {
        		echo"error:数据库链接错误!";
    		 }
			return $dbc;
		}
		function close($dbc){
			mysqli_close($dbc);
		}
	}
?>