<?php
	include 'head.php';
	echo finish();
	function finish(){
	    $order = $_POST["orderid"];
	    $orderdb = new OrderDB();
	    $enum = new OrderStatus();
	   	$enum = new OrderStatus();
	    $status=$enum->FINISH;
	    $orderdb->update($order,$status);
	    $result =isFinish($order);
	    if($result)
	    {
	    	return $order;
	    }
	    else
	    {
	    	return "";
	    }
	}
	function isFinish($orderid)
	{
		$db  =new DB();
	    $dbc= $db->connect();
	    $stmt=$dbc->prepare("select orderstatus from ab_order where orderid=?");
	    $stmt->bind_param("s",$orderid);
	    $stmt->execute();
	    $stmt->bind_result($orderstatus);
	    $stmt->fetch();
	    $stmt->close();
	    $db->close($dbc);
	    $enum = new OrderStatus();
	    $result = ($orderstatus==$enum->FINISH);
	    return $result;
	}
?>