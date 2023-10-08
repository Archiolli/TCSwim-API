package com.example.TCSwim.responses;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ResponseData {
    private Map<String, String> data = new HashMap<>();

    public ResponseData(String message) {
        data.put("message", message);
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
