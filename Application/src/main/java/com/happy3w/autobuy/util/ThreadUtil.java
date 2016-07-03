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

    /**线程挂起毫秒。
     * @param millions 毫秒。
     */
    public static void sleep(long millions) {
        try {
            Thread.sleep(millions);
        } catch (InterruptedException e) {
            LogUtil.getLogger().error(e.getMessage(), e);
        }
    }
}
