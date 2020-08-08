package com.beautiful.mall01.nosql.mongodb.repository;


import com.beautiful.mall01.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

//command+F12 查看类的所有方法
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {

    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long id);

    @Query("{'memberId' : ?0}")
    List<MemberReadHistory> findByMemberId(Long id);


}
