package com.beautiful.mall01.service;

import com.beautiful.mall01.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

public interface MemberReadHistoryService {

    /**
     * 生成浏览记录
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览记录
     */
    List<MemberReadHistory> list(Long memberId);
}
