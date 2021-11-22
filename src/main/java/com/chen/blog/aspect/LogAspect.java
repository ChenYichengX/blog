package com.chen.blog.aspect;

import com.chen.blog.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 陈奕成
 * @create 2020 05 09 14:18
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogUtil logUtil;

    /*@Pointcut("execution(* com.chen.blog.web.*.*(..)) && !execution(* com.chen.blog.web.IndexController.ioReadPicture(..)) && !execution(* com.chen.blog.web.IndexController.newBlogList(..))")
    public void log(){}*/

    @Pointcut("@annotation(com.chen.blog.aspect.SystemLog)")
    public void log() {
    }

    /**
     * @Author ChenYicheng
     * @Description 环绕通知
     * @Date 2021/10/28 17:47
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        logger.info("记录日志操作");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLog ocsLog = method.getAnnotation(SystemLog.class);
        Object proceed = null;

        try {
            proceed = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable var14) {
            var14.printStackTrace();
        }
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logUtil.insertEsLog(ocsLog.serviceName(), ocsLog.module(), ocsLog.action(), proceed, parameterNames, args,request, request.getSession());

        return proceed;
    }

    /*@Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);

        logger.info("-----doBefore-----");
        logger.info("requestLog:"+requestLog);
    }

    @After("log()")
    public void doAfter(){
        logger.info("-----doAfter-----");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("result:"+result);
    }*/

    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
