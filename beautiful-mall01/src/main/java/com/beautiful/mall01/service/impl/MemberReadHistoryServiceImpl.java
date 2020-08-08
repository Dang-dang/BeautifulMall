package com.beautiful.mall01.service.impl;

import com.beautiful.mall01.nosql.mongodb.document.MemberReadHistory;
import com.beautiful.mall01.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.beautiful.mall01.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Autowired
    private MemberReadHistoryRepository readHistoryRepository;
    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        readHistoryRepository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList=new ArrayList<>();
        for (String id:ids) {
            MemberReadHistory memberReadHistory=new MemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }
        readHistoryRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        List<MemberReadHistory> readHistories = readHistoryRepository.findByMemberId(memberId);
        return readHistories;
    }
}
