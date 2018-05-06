/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.example.myvue.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;


/**
 * 
 *
 * @version $Revision$ $Date$
 * @since 3.3.6
 */
@Component
@Aspect
@Order(2)
public class LogAspect {

    @Resource
    private Environment env;

    @Pointcut("execution (public * com.example.myvue.service..*.*(..))")
    private void pointCutMethod() {
    }

    @Around(value = "pointCutMethod()")
    public Object traceMethod(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnVal = null;
        final Logger log = getLog(proceedingJoinPoint);
        final String methodName = proceedingJoinPoint.getSignature().getName();

        try {
            if ("true".equals(env.getProperty("traceSwitch"))) {
                final Object[] args = proceedingJoinPoint.getArgs();
                final String arguments;
                if (args == null || args.length == 0) {
                    arguments = "";
                } else {
                    arguments = Arrays.deepToString(args);
                }
//                log.info("Entering method [" + methodName + " with arguments [" + arguments + "]");
                log.info("Entering method {} with arguments {}",methodName,arguments);
            }
            returnVal = proceedingJoinPoint.proceed();
            return returnVal;
        } finally {
            if ("true".equals(env.getProperty("traceSwitch"))) {
//                log.info("Leaving method [" + methodName + "] with return value [" + (returnVal != null ? returnVal.toString() : "null") + "].");
                log.info("Leaving method {}  with return value {} ",methodName,returnVal != null ? returnVal.toString() : "null");
            }
        }
    }

    protected Logger getLog(final JoinPoint joinPoint) {
        final Object target = joinPoint.getTarget();

        if (target != null) {
            return LoggerFactory.getLogger(target.getClass());
        }

        return LoggerFactory.getLogger(getClass());
    }

}
