<?php
	/**
	* 订单。
	*/
	class Order
	{
		public $product;
		public $amount=0.00;
		public $buytime;
		public $orderstatus=0;
		function __construct(argument)
		{
		}
		public function getProduct(){
			return $this->product;
		}
		public function setProduct($product){
			$this->product=$product;
		}
		public function getAmount(){
			return $this->amount;
		}
		public function setAmount($amount){
			return $this->amount=$amount;
		}
		public function getBuyTime(){
			return $this->buytime;
		}
		public function setBuyTime($buytime){
			$this->buytime=$buytime;
		}
		public function getOrderStatus(){
			return $this->orderstatus;
		}
		public function setOrderStatus($status){
			$this->orderstatus=$status;
		}
	}
?>