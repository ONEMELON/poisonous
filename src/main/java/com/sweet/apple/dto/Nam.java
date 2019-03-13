package com.sweet.apple.dto;

import java.io.Serializable;

/**
 * @Author zhujialing
 * @Create 2019-01-25 下午5:45
 * @Description:
 */
public class Nam implements Serializable {
    private String name;

    private Nam() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
