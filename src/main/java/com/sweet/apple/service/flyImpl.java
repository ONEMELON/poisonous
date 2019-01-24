package com.sweet.apple.service;


import org.springframework.stereotype.Service;

/**
 * @Author zhujialing
 * @Create 2018-11-08 上午10:49
 * @Description:
 */
@Service
public class flyImpl implements Ifly {
    @Override public Object flywing() {
        System.out.println("flyimpl");
        return null;
    }
}
