package com.example.myvue.aspect;

import com.example.myvue.myException.ExceptionHandle;
import com.example.myvue.myException.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/2/6.
 */
@Aspect
@Component
@Order(1)
public class HttpAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.example.myvue.controller.*.*(..))")
    private void pointCutMethod() {
    }

    @Autowired
    private ExceptionHandle exceptionHandle;

    @Before("pointCutMethod()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        LOGGER.info("url={}",request.getRequestURL());
        //method
        LOGGER.info("method={}",request.getMethod());
        //ip
        LOGGER.info("id={}",request.getRemoteAddr());
        //class_method
        LOGGER.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());
        //args[]
        LOGGER.info("args={}",joinPoint.getArgs());
    }

    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Result<Object> result = new Result<>();
//        try {
            Object execResult = proceedingJoinPoint.proceed();
            result.setData(execResult);
            result.setMsg("操作成功");
            result.setStatus("1");
            return result;
//        } catch (Exception e) {
//            return exceptionHandle.exceptionGet(e);
//        }
//        if(result == null){
//            return proceedingJoinPoint.proceed();
//        }else {
//            return result;
//        }
    }

    @AfterReturning(pointcut = "pointCutMethod()",returning = "object")//打印输出结果
    public void doAfterReturing(Object object){
        LOGGER.info("response={}",object.toString());
    }
}
