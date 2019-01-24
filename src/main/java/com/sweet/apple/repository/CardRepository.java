package com.sweet.apple.repository;

import com.sweet.apple.domain.cardDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhujialing
 * @Create 2019-01-23 下午3:06
 * @Description:
 */
@Repository
public interface CardRepository extends CrudRepository<cardDomain, Integer> {

    public List<cardDomain> findByName(String name);




}
