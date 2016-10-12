/**
 * 
 */
package testkit.com.happy3w.autoby;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.happy3w.autobuy.model.PurchaseOrder;

/**
 *
 * @version 2016年9月8日 下午2:44:00
 * @author Happy3W Cherry
 *
 */
public class TestUtil {
	/**
	 * 生成json数据。
	 * 
	 * @return
	 */
	public static String getJson(int count, int start) {
		PurchaseOrder[] orders = getOrders(count, start);
		Gson gson = new Gson();
		return gson.toJson(orders);

	}

	/**
	 * 生成测试用订单数据。
	 * 
	 * @param count
	 * @param start
	 * @return
	 */
	public static PurchaseOrder[] getOrders(int count, int start) {
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		for (int i = 0; i < count; i++) {
			PurchaseOrder order = new PurchaseOrder();
			order.setAmount(100);
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				order.setBuytime(date.parse("2016/09/08 09:00:00"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			order.setOrderid(String.format("T%02d", i + start));
			order.setProduct("YY-A");
			lst.add(order);
		}
		return lst.toArray(new PurchaseOrder[0]);
	}
}
