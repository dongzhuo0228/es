package com.example.es.mapper;

import com.example.es.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User,String> {

    //按userName like查询
    List<User> findByUserNameLike(String userName);

}