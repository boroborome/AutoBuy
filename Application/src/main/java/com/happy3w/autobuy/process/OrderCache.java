/**
 * 
 */
package com.happy3w.autobuy.process;

import java.util.ArrayList;
import java.util.List;

import com.happy3w.autobuy.model.Order;

/**
 *指令缓存表。
 * @version 2016年7月14日 下午8:45:11
 * @author Happy3W Cherry
 *
 */
public class OrderCache {
	private List<Order> listOrder =new ArrayList<Order>();
	public synchronized void add(Order order){
		listOrder.add(order);
	}
	public synchronized  void addAll(List<Order> orders){
		listOrder.addAll(orders);
	}
	public Order[] getOrders()
	{
		return listOrder.toArray(new Order[0]);
	}
}
