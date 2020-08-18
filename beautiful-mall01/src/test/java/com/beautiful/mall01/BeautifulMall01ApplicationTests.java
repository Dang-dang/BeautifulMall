package com.beautiful.mall01;

import com.beautiful.mall01.mbg.model.UmsPermission;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class BeautifulMall01ApplicationTests {

    @Test
    void contextLoads() {
    }


    /**
     * Java 8 Stream API 操作
     */
    @Test
    void streamTest() {
        List<UmsPermission> permissionList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            UmsPermission permission = new UmsPermission();
            permission.setId((long) i);
            permission.setPid((long) i);
            permission.setName("权限" + i);
            permission.setValue(i + "-->" + i);
            permissionList.add(permission);
        }
        //串行流对象
        Stream<UmsPermission> stream = permissionList.stream();
        //并行流对象
        Stream<UmsPermission> parallelStream = permissionList.parallelStream();
        List<UmsPermission> collect = stream.filter(permission -> permission.getId() > 50)
                .collect(Collectors.toList());
        System.out.println("fliter-->" + collect.get(0).getName());
        List<Long> collect1 = collect.stream().map(UmsPermission::getId).collect(Collectors.toList());
        System.out.println("map-->" + collect1.get(0));
        List<Long> collect2 = collect1.stream().limit(10).collect(Collectors.toList());
        System.out.println("limit-->" + collect2.get(collect2.size() - 1));
        long count = collect2.stream().count();
        System.out.println("count-->" + count);
        List<UmsPermission> collect3 = collect.stream().sorted((p1, p2) -> p2.getId().compareTo(p1.getId())).collect(Collectors.toList());
        System.out.println("collect3-sort0" + collect3.get(0).getId());
        System.out.println("collect3-sort1" + collect3.get(1).getId());
        List<UmsPermission> collect4 = collect3.stream().skip(47).collect(Collectors.toList());
        System.out.println("collect4-->" + collect4.get(0).getId() + "-->" + collect4.get(1).getId());
        Map<Long, UmsPermission> permissionMap = collect.stream()
                .collect(Collectors.toMap(UmsPermission::getId, permission -> permission));
        //id 是long值，所以 51后边必须加L 否则报空指针
        System.out.println("map-->51号的ID是" + permissionMap.get(51L).getId());
        List<Long> collect5 = parallelStream
                .filter(permission -> permission.getId() > 90)
                .map(UmsPermission::getId)
                .limit(5)
                .skip(3).collect(Collectors.toList());
        System.out.println("collect5-94->"+collect5.get(0));
    }
}
