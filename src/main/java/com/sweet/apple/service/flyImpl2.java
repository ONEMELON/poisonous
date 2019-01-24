package com.sweet.apple.service;

import org.springframework.stereotype.Service;

/**
 * @Author zhujialing
 * @Create 2018-11-08 上午10:50
 * @Description:
 */
@Service
public class flyImpl2 implements Ifly {
    @Override public Object flywing() {
        System.out.println("flyImpl2");
        return null;
    }
}
