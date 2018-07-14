package com.star.util;

import com.star.bean.BaseInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 多层json生成器
 *
 * @author jimxjiao
 */
public class JsonProducter {
    /**
     * @param info    头信息
     * @param dataMap body信息：Map格式的数据，key为String,Values分三种情况： 1、String：直接作为标签内容
     *                2、Map： 递归新增一层标签 3、List： 并列新增标签，循环递归新增标签
     * @return json结果
     * @throws Exception 类型不属于以上三种类型
     */
    public synchronized static String produceJson(Map<String, Object> dataMap,
                                                  BaseInfo info) throws Exception {
        if (null == dataMap) {
            return null;
        }
        try {
            // 最外层对象
            JSONObject object = new JSONObject();

            // head部分
            JSONObject headObject = new JSONObject();
            headObject.put("timestamp", info.timestamp);
            headObject.put("flowmark", info.flowmark);
            headObject.put("expire", info.expire);
            headObject.put("imei", info.imei);
            headObject.put("token", info.token);
            object.put("head", headObject);

            // body部分
            JSONObject bodyObject = new JSONObject();
            produceSubJson(bodyObject, dataMap);
            object.put("body", bodyObject);

            return object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static void produceSubJson(JSONObject jsonObject,
                                       Map<String, Object> dataMap) throws Exception {
        try {
            for (String key : dataMap.keySet()) {
                Object temp = dataMap.get(key);
                if (temp instanceof String) {
                    jsonObject.put(key, temp);
                } else if (temp instanceof Map<?, ?>) {
                    JSONObject subObject = new JSONObject();

                    produceSubJson(subObject, (Map<String, Object>) temp);

                    jsonObject.put(key, subObject);
                } else if (temp instanceof List<?>) {
                    List<Map<String, Object>> data = (List<Map<String, Object>>) temp;
                    JSONArray arrayObject = new JSONArray();
                    for (Map<String, Object> map : data) {
                        JSONObject subObject = new JSONObject();
                        produceSubJson(subObject, map);
                        arrayObject.put(subObject);
                    }
                    jsonObject.put(key, arrayObject);
                } else {
                    throw new Exception(
                            "the value of the map is only to be String or Map<?,?> or list,check the map");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
