package com.sweet.apple.dto;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Map;

public class QuotaResult implements Serializable {
    private static final long serialVersionUID = 8394276107528726019L;

    private String rowKey;
    private boolean isEmpty = true;
    private Map<String, String> quota;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public Map<String, String> getQuota() {
        return quota;
    }

    public void setQuota(Map<String, String> quota) {
        this.quota = quota;
        isEmpty = CollectionUtils.isEmpty(quota);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}