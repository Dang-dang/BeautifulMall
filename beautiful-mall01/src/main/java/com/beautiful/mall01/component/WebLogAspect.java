package com.beautiful.mall01.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.beautiful.mall01.dto.WebLog;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
@Order(1)
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(public * com.beautiful.mall01.controller.*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{

    }

    @AfterReturning(value = "webLog()",returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable{

    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime=System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求信息
        WebLog webLog=new WebLog();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature= (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if(method.isAnnotationPresent(ApiOperation.class)){
            ApiOperation apiOperation=method.getAnnotation(ApiOperation.class);
            webLog.setDescription(apiOperation.value());
        }
        long endTime=System.currentTimeMillis();
        String urlStr=request.getRequestURL().toString();
        log.info("urlStr==>{}",urlStr);
        log.info("URLUtil.url(urlStr).getPath()==>{}",URLUtil.url(urlStr).getPath());
        webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
        webLog.setIp(request.getRemoteUser());
        webLog.setMethod(request.getMethod());
        webLog.setParameter(getParameter(method,joinPoint.getArgs()));
        webLog.setResult(result);
        webLog.setSpendTime((int) (endTime-startTime));
        webLog.setStartTime(startTime);
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        log.info("webLog==>{}",webLog);
        Map<String,Object> logMap=new HashMap<>();
        logMap.put("url",webLog.getUrl());
        logMap.put("method",webLog.getMethod());
        logMap.put("parameter",webLog.getParameter());
        logMap.put("spendTime",webLog.getSpendTime());
        logMap.put("description",webLog.getDescription());
        log.info(Markers.appendEntries(logMap),JSONUtil.parse(webLog).toString());
        return result;
    }

    private Object getParameter(Method method,Object[] args){
        List<Object> argList=new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i <parameters.length ; i++) {
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if(requestBody!=null){
                argList.add(args[i]);
            }
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if(requestParam!=null){
                Map<String,Object> map=new HashMap<>();
                String key=parameters[i].getName();
                if(!StringUtils.isEmpty(requestParam.value())){
                    key=requestParam.value();
                }
                map.put(key,args[i]);
                argList.add(map);
            }
        }
        if(argList.size()==0){
            return null;
        }else if(argList.size()==1){
            return argList.get(0);
        }else {
            return argList;
        }
    }
}
