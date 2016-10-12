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
    $enum = new OrderStatus();
    $status=$enum->INIT;
    $stmt->bind_param("sssi",$pro,$amount,$buytime,$status);
    $stmt->execute();
    return true;
}
?>