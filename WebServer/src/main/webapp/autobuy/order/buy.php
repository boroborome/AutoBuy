<?php
include 'head.php';
echo getOrder4Buy();
function getOrder4Buy(){
	$db  =new DB();
    $dbc= $db->connect();
    $enum = new OrderStatus();
    $status=$enum->INIT;
    $result=mysqli_query($dbc,"select orderid,product,amount,buytime from ab_order where orderstatus=".$status);
    $items=array();
    $i=0;
    $orderdb =new OrderDB();
    $status = $enum->BUYING;
    while($item=mysqli_fetch_object($result)){
    	$items[$i]=$item;
    	$i=$i+1;
        $orderdb->update($item->orderid,$status);
    }
    $db->close($dbc);
    return json_encode($items,JSON_UNESCAPED_UNICODE);
}
?>