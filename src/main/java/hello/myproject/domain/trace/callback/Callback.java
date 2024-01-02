package hello.myproject.domain.trace.callback;

/**
 * 콜백 전달 인터페이스
 */
public interface Callback<T> {
    T call();
}
