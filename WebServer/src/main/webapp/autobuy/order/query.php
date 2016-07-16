<?php
include 'head.php';
echo query();
function query(){
	$db  =new DB();
    $dbc= $db->connect();
    $result=mysqli_query($dbc,"select orderid,product,amount,buytime from ab_order where orderstatus=0");
    $items=array();
    $i=0;
    while($item=mysqli_fetch_object($result)){
    	$items[$i]=$item;
    	$i=$i+1;
    }
    $db->close($dbc);
    return json_encode($items,JSON_UNESCAPED_UNICODE);
}
?>