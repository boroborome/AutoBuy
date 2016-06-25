/**
 * 
 */
package com.happy3w.autobuy.util;

/**
 *
 * @version 2016年6月25日 下午12:31:45
 * @author Happy3W Chen
 *
 */
public class ThreadUtil {

    public static void sleep(int second) {
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            LogUtil.getLogger().error(e.getMessage(), e);
        }
    }
}
