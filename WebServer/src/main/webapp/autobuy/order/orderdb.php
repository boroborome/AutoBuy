<?php
class OrderDB
{
	function update($orderid,$orderstatus){
		$db  =new DB();
	    $dbc= $db->connect();
	    $stmt=$dbc->prepare("update ab_order set orderstatus=? where orderid=?");
	    $stmt->bind_param("is",$orderstatus,$orderid);
	    $stmt->execute();
	    $db->close($dbc);
	    return $stmt->affected_rows;
	}
}
?>