package hello.myproject.domain.trace;

public class TraceStatus {

    /**
     * 현재 로그의 상태정보 나타냄
     */
    private TraceId traceId;    //트랜잭션 ID
    private Long startTimeMs;   //시작 시간
    private String message;    //로그 메시지

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
