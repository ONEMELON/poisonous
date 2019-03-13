package com.sweet.apple.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author zhujialing
 * @Create 2018-10-10 下午5:35
 * @Description:
 */
//@Component
//@Bean
public class CardDto implements Serializable{
    private Set<String> card;
    private String name;
    private String age;
    private Set<String> index;

    public CardDto(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Set<String> getCard() {
        return card;
    }

    public void setCard(Set<String> card) {
        this.card = card;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Set<String> getIndex() {
        return index;
    }

    public void setIndex(Set<String> index) {
        this.index = index;
    }
}
