package com.aurindo.myfood.orderService.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSonWrapper {

    private static JSonWrapper jSonWrapper;
    private ObjectMapper mapper;

    private JSonWrapper() {
        this.mapper = new ObjectMapper();
    }

    public static JSonWrapper getInstance() {
        if (jSonWrapper == null) {
            jSonWrapper = new JSonWrapper();
        }

        return jSonWrapper;
    }

    public Object stringToJavaObject(String json, Class clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    public JsonNode stringToJSonObject(String json) throws IOException {
        return mapper.readTree(json);
    }
}
