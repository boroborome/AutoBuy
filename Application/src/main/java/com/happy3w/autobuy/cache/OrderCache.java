/**
 * 
 */
package com.happy3w.autobuy.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happy3w.autobuy.exe.AtExeSchedulor;
import com.happy3w.autobuy.model.PurchaseOrder;

/**
 * 缓存数据。 Provider将数据存于此，Consumer从此处取走数据。
 * 
 * @version 2016年7月21日 下午4:44:10
 * @author Happy3W Cherry
 *
 */
@Component
public class OrderCache {
	private List<PurchaseOrder> lstOrder = new ArrayList<PurchaseOrder>();
	private Map<String, PurchaseOrder> mapNewOrder = new HashMap<String, PurchaseOrder>();
	private Map<String, PurchaseOrder> mapDoingOrder = new HashMap<String, PurchaseOrder>();
	private AtExeSchedulor schedulor;

	@Autowired
	public OrderCache(AtExeSchedulor schedulor) {
		this.schedulor = schedulor;
	}

	/**
	 * 下载的最新订单数。
	 * 
	 * @return
	 */
	public int sizeOfNew() {
		return mapNewOrder.size();
	}

	/**
	 * 进入定时任务调度的订单数。
	 * 
	 * @return
	 */
	public int sizeOfDoing() {
		return mapDoingOrder.size();
	}

	/**
	 * 加入订单。
	 * <p>
	 * <li>如果订单相同则更新缓存，否则新增缓存。
	 * <li>如果已经在处理，则不作任何处理。
	 * </p>
	 * 
	 * @param orders
	 */
	public void put(PurchaseOrder[] orders) {
		for (PurchaseOrder order : orders) {
			if (!mapDoingOrder.containsKey(order.getOrderid())) {
				mapNewOrder.put(order.getOrderid(), order);
			}
		}
		notifyExe();
	}

	/**
	 * 弹出待处理订单。
	 * <P>
	 * <li>返回最新订单。
	 * <li>并将最新订单放入正在处理的集合中。
	 * </p>
	 * 
	 * @return 返回缓存中的最新订单。
	 */
	public PurchaseOrder[] pop() {
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		lst.addAll(mapNewOrder.values());
		for (String key : mapNewOrder.keySet()) {
			mapDoingOrder.put(key, mapNewOrder.get(key));
		}
		mapNewOrder.clear();
		Collections.sort(lst, new Comparator<PurchaseOrder>() {

			@Override
			public int compare(PurchaseOrder a, PurchaseOrder b) {
				// TODO Auto-generated method stub
				int result = a.getBuytime().compareTo(b.getBuytime());
				if (result == 0) {
					result = a.getOrderid().compareTo(b.getOrderid());
				}
				return result;
			}

		});
		return lst.toArray(new PurchaseOrder[0]);
	}

	/**
	 * 通知执行。
	 * <p>
	 * 有新订单时，通知执行。
	 * </p>
	 */
	private void notifyExe() {
		schedulor.schedule(pop());
	}
}
