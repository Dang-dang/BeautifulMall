package com.beautiful.service;

import cn.hutool.core.collection.CollUtil;
import com.beautiful.domain.CommonResult;
import com.beautiful.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Slf4j
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.user-service}")
    private String userServiceUrl;

    /**
     * HystrixCommand中的常用参数
     * fallbackMethod：指定服务降级处理方法；
     * ignoreExceptions：忽略某些异常，不发生服务降级；
     * commandKey：命令名称，用于区分不同的命令；
     * groupKey：分组名称，Hystrix会根据不同的分组来统计命令的告警及仪表盘信息；
     * threadPoolKey：线程池名称，用于划分线程池。
     */
    @HystrixCommand(fallbackMethod = "getDefaultUser")
    public CommonResult getUser(Long id){
        CommonResult result = restTemplate.getForObject(userServiceUrl + "/user/getUser/{1}", CommonResult.class, id);
        return result;
    }


    public CommonResult getDefaultUser(@PathVariable Long id){
        User user = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(user);
    }


    @HystrixCommand(fallbackMethod = "getDefaultUser",
        commandKey = "getUserCommand",
        groupKey = "getUserGroup",
        threadPoolKey = "getUserThreadPool")
    public CommonResult getUserCommand(Long id){
        return restTemplate.getForObject(userServiceUrl+"/user/getUser/{1}",CommonResult.class,id);
    }

    @HystrixCommand(fallbackMethod = "getDefaultUser2",ignoreExceptions = {NullPointerException.class})
    public CommonResult getUserException(Long id) {
        if(id==1){
            throw new IndexOutOfBoundsException();
        }else if(id==2){
            //忽略 不做降级处理 所以报错
            throw new NullPointerException();
        }
        return restTemplate.getForObject(userServiceUrl+"/user/getUser/{1}",CommonResult.class,id);
    }

    public CommonResult getDefaultUser2(@PathVariable Long id,Throwable e){
        User defaultUser2 = new User(-2L, "defaultUser2", "112233");
        return new CommonResult<>(defaultUser2);
    }


    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefaultUser",commandKey = "getUserCache")
    public CommonResult getUserCache(Long id) {
        log.info("getUserCache id:{}",id);
        return restTemplate.getForObject(userServiceUrl+"/user/getUser/{1}",CommonResult.class,id);
    }

    private String getCacheKey(Long id){
        return String.valueOf(id);
    }

    @CacheRemove(cacheKeyMethod = "getCacheKey",commandKey = "getUserCache")
    @HystrixCommand(fallbackMethod = "getDefaultUser")
    public CommonResult getUserRemoveCache(Long id) {
        log.info("getUserRemoveCache id:{}",id);
//        return new CommonResult("成功",200);
        return restTemplate.getForObject(userServiceUrl+"/user/getUser/{1}",CommonResult.class,id);
    }

    @HystrixCollapser(batchMethod = "getUserByIds",collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "100")
    })
    public Future<User> getUserFuture(Long id){
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
//                CommonResult commonResult = restTemplate.getForObject(userServiceUrl + "/user/getUser/{1}", CommonResult.class, id);
//                Map data = (Map) commonResult.getData();
//                User user = BeanUtil.mapToBean(data, User.class, true);
//                log.info("getUserById username:{}",user.getUsername());
//                return user;
                return null;
            }
        };
    }

    @HystrixCommand
    public List<User> getUserByIds(List<Long> ids){
        log.info("getUserByIds:{}",ids);
        CommonResult result = restTemplate.getForObject(userServiceUrl + "/user/getUserByIds?ids={1}", CommonResult.class, CollUtil.join(ids,","));
        return (List<User>) result.getData();
    }
}
