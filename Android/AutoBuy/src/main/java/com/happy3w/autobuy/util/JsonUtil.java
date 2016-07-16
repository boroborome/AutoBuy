package com.happy3w.autobuy.util;

import com.happy3w.autobuy.model.PurchaseOrder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * JSON操作类。
 * @author E
 */
public class JsonUtil {
    /**
     * 将数组转换为JSON格式的数据。
     * @param po 数据源
     * @return JSON格式的数据
     */
    public static String changeArrayDateToJson(PurchaseOrder po){
        try {
            JSONObject json = new JSONObject();
            json.put("po",po);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 将数组转换为JSON格式的数据。
     * @param pos 数据源
     * @return JSON格式的数据
     */
    public static String changeArrayDateToJson(PurchaseOrder[] pos){
        try {
            JSONArray array = new JSONArray();
            JSONObject object = new JSONObject();
            for(PurchaseOrder po:pos)
            {
                array.put(po);
            }
            return array.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    /**
//     * 将JSON转化为数组并返回。
//     * @param Json
//     * @return ArrayList<Stone>
//     */
//    public static ArrayList<Stone> changeJsonToArray(String Json){
//        ArrayList<Stone> gameList = new ArrayList<Stone>();
//        try {
//            JSONObject jsonObject = new JSONObject(Json);
//            if (!jsonObject.isNull("stones")) {
//                String aString = jsonObject.getString("stones");
//                JSONArray aJsonArray = new JSONArray(aString);
//                int length = aJsonArray.length();
//                for (int i = 0; i < length; i++) {
//                    JSONObject stoneJson = aJsonArray.getJSONObject(i);
//                    String name = stoneJson.getString("name");
//                    String size = stoneJson.getString("size");
//                    Stone stone = new Stone();
//                    stone.setName(name);
//                    stone.setSize(size);
//                    gameList.add(stone);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return gameList;
//    }

}