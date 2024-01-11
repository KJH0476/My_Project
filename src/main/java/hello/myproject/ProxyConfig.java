package hello.myproject;

import hello.myproject.domain.proxy.LogTraceAdvice;
import hello.myproject.domain.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyConfig {

    /**
     * AspectJExpressionPointcut : AspectJ 포인트컷 표현식을 적용
     */
    @Bean
    public Advisor advisor(LogTrace logTrace) {
        //pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //포인트 컷 적용. myproject 패키지 하위의 모든 메서드에 적용
        pointcut.setExpression("execution(* hello.myproject..*(..))");
        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
