package com.example.es;

import com.example.es.mapper.UserRepository;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class StandardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 批量新增
     **/
    public void batchAddUser(List<User> users) {
        if(CollectionUtils.isEmpty(users)) {
            return ;
        }
        List<IndexQuery> queries =new ArrayList<>(4);
        IndexQuery indexItem  = null;
        for(User user :users) {
            indexItem = new IndexQuery();
            indexItem.setObject(user);
            queries.add(indexItem);
        }
        elasticsearchTemplate.bulkIndex(queries);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void deletedUserById(String id) {
        userRepository.deleteById(id);
    }

    /**
     * 根据userId更新信息
     */
    public void updateUser(User user) {
        UpdateQuery updateQuery = new UpdateQuery();
        updateQuery.setId(user.getId());
        updateQuery.setClazz(User.class);
        user.setId(null);
        UpdateRequest request = new UpdateRequest();
//        request.doc(JsonUtils.beanToJson(user));
        updateQuery.setUpdateRequest(request);
        elasticsearchTemplate.update(updateQuery);
    }

    public List<User> queryByUserName(String userName) {
        return userRepository.findByUserNameLike(userName);
    }


    public Iterable<User> search(){
        QueryBuilder queryBuilder  = QueryBuilders.termsQuery("userName","dz","dz1");
        return userRepository.search(queryBuilder);
    }


}