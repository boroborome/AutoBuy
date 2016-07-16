<?php
include 'head.php';
echo update();
function update(){
	$db  =new DB();
    $dbc= $db->connect();
    $order = $_POST["orderid"];
    $stmt=$dbc->prepare("update ab_order set orderstatus=? where orderid=?");
    $status=1;
    $stmt->bind_param("is",$status,$order);
    $stmt->execute();
    echo "ok:update to database.";
    $db->close($dbc);
}
?>