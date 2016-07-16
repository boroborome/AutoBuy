<?php 
include 'head.php';
insert();
function insert(){
	$db  =new DB();
    $dbc= $db->connect();
    $order = json_decode($_POST["order"]);
    $stmt=$dbc->prepare("insert into ab_order(product,amount,buytime,orderstatus)values(?,?,?,?)");
    $buytime = $order->buytime;
    $pro=$order->product;
    $amount=$order->amount;
    $status=0;
    $stmt->bind_param("sssi",$pro,$amount,$buytime,$status);
    $stmt->execute();
    echo "ok:isert to database.";
    $db->close($dbc);
}
?>