package hello.services.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
public class TestAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Long timeStart;

    @Pointcut("execution(public * hello.services.TestService.*(..))")
    public void callCsvParserTest() {
//        String args = Arrays.stream(jp.getArgs())
//                .map(a -> a.toString())
//                .collect(Collectors.joining(","));
//        logger.info("before " + jp.toString() + ", args=[" + args + "]");
    }

    @Before("callCsvParserTest()")
    public void initStartTime() {
        this.timeStart = System.currentTimeMillis();
        logger.info("START!");
    }

    @After("callCsvParserTest()")
    public void finishTime() {
        logger.info("END! TIME: " + (System.currentTimeMillis() - this.timeStart));
    }
}
