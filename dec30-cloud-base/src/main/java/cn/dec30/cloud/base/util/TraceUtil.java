package cn.dec30.cloud.base.util;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.TraceContext;
import org.springframework.cloud.sleuth.Tracer;

import java.util.Optional;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/25 22:20
 * @description 链路追踪工具类
 */
public class TraceUtil {

    private TraceUtil() {}

    private static final String ERROR = "error";

    public static String getTraceId() {
        return getContext().traceId();
    }

    public static String getSpanId() {
        return getContext().spanId();
    }

    public static TraceContext getContext() {
        return Optional.of(getSpan()).get().context();
    }

    public static Span getSpan() {
        return getTracer().currentSpan();
    }

    public static Tracer getTracer() {
        return SpringUtil.getBean(Tracer.class);
    }

    public static void tagException(Exception e) {
        getSpan().tag(ERROR, ThrowableUtil.genStackString(e));
    }

    public static void tagMessage(String message) {
        getSpan().tag(ERROR, message);
    }
}
