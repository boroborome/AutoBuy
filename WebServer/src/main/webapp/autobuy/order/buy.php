<?php
echo getOrder4Buy();
function getOrder4Buy(){
    $items=array(
    array(
	    	"orderid"=>"t01",
	    	"product"=>"YY-C",
	    	"amount"=>"100",
	    	"buytime"=>"2016-10-1 00:00:00",
	    	"task"=>"YY"
	    )
    	);
    return json_encode($items,JSON_UNESCAPED_UNICODE);
}
?>