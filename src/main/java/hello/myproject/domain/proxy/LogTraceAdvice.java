package hello.myproject.domain.proxy;

import hello.myproject.domain.trace.TraceStatus;
import hello.myproject.domain.trace.logtrace.LogTrace;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Advice
 */
public class LogTraceAdvice implements MethodInterceptor {

    private final LogTrace logTrace;

    public LogTraceAdvice(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        TraceStatus status = null;
        try{
            Method method = invocation.getMethod();
            //로그 메세지
            String message = method.getDeclaringClass().getSimpleName() + "."
                    + method.getName() + "()";
            //공통 로직
            status = logTrace.begin(message);

            //서비스 로직 호출
            Object result = invocation.proceed();

            //공통 로직
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            //공통 로직 -> 예외시
            logTrace.exception(status, e);
            throw e;
        }
    }
}
