/**
 * 
 */
package com.happy3w.autobuy.driver;

import java.util.logging.Level;

import com.happy3w.autobuy.model.UserOrder;
import com.happy3w.autobuy.transfer.TransferProxy;
import com.happy3w.autobuy.util.LogUtil;


/**
 * download
 * @version 2016年10月26日下午4:00:41
 * @author happy3w
 */
public class DownloadRunner implements Runnable{

	private TransferProxy transfer;
	private IDownloadListener[] listeners;
	public DownloadRunner(TransferProxy transfer,IDownloadListener[] listeners)
	{
		this.transfer=transfer;
		this.listeners = listeners;
	}
	@Override
	public void run() {
		try
		{
			UserOrder[] orders =this.transfer.download();
			for(IDownloadListener listener:this.listeners)
			{
				listener.handle(orders);
			}
		}
		catch(Exception e)
		{
			LogUtil.getLogger().log(Level.SEVERE, e.getMessage());
			System.out.println(e.getMessage());
		}
	}
}
