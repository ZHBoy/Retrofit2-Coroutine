package com.zhboy.retrofit2_coroutine.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * @author : HaoBoy
 * @date : 2018/8/17
 * @description :
 **/
public class GsonUtils {

    /*将json转成map*/
    public static HashMap<String, String> parseJSON2Map(String jsonStr) {
        return getIntGson().fromJson(jsonStr, HashMap.class);
    }

    /**
     * 解决gson默认将int转换为double
     *
     * @return
     */
    public static Gson getIntGson() {
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                    @Override
                    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                        if (src == src.longValue())
                            return new JsonPrimitive(src.longValue());
                        return new JsonPrimitive(src);
                    }
                }).create();
        return gson;
    }
}
