package hello.myproject.domain.trace.callback;

import hello.myproject.domain.trace.TraceStatus;
import hello.myproject.domain.trace.logtrace.LogTrace;

/**
 * 템플릿 콜백 패턴 이용
 * 템플릿 역할을 한다.
 * 로직 실행 전후에 필요한 공통 기능을 적용한다.
 */
public class TraceTemplate {
    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String message, TraceCallback<T> callback){
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            //로직 호출
            T result = callback.call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
