package hello.myproject.domain.trace.logtrace;


import hello.myproject.domain.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
