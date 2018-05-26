package com.example.myvue.aspect;

import com.example.myvue.myException.DataBaseException;
import com.example.myvue.myException.ExceptionHandle;
import com.example.myvue.myException.McException;
import com.example.myvue.myException.Result;
import com.example.myvue.myannotation.LoginValid;
import com.example.myvue.myannotation.ParamsValid;
import com.example.myvue.service.ValidationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

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

    @Resource
    private ValidationService validationService;

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
        try {
            doValidBefore(proceedingJoinPoint);

            Object execResult = proceedingJoinPoint.proceed();
            result.setData(execResult);
            result.setMsg("操作成功");
            result.setStatus("1");
            return result;
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    /**
     * 在执行方法之前进行数据的校验
     * @param proceedingJoinPoint
     */
    private void doValidBefore(ProceedingJoinPoint proceedingJoinPoint) throws McException, DataBaseException {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

        if ( method.isAnnotationPresent(ParamsValid.class)) {
            Object[] args = proceedingJoinPoint.getArgs();
            if (args == null || args.length == 0) {
                throw new McException("字段校验不通过！","MCE-LOSE-PARAM");
            }
            Object arg = args[0];
            if (arg == null || !(arg instanceof Map)) {
                throw new McException("字段校验不通过！","MCE-LOSE-PARAM");
            }
            Map<String, Object> params = (Map<String, Object>) arg;
            validationService.paramsValid(method.getAnnotation(ParamsValid.class),params);
        }

        //url
        LOGGER.info("url={}",request.getRequestURL());
        // 这里判断是否需要登陆
        if ( proceedingJoinPoint.getTarget().getClass().isAnnotationPresent(LoginValid.class)) {
            validationService.loginValid(request.getParameter("userId"));
        }
    }

    @AfterReturning(pointcut = "pointCutMethod()",returning = "object")//打印输出结果
    public void doAfterReturing(Object object){
        LOGGER.info("response={}",object.toString());
    }
}
