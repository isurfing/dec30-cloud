package cn.dec30.base.util;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.TraceContext;
import org.springframework.cloud.sleuth.Tracer;

import java.util.Optional;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/25 22:20
 * @description todo
 */
public class TraceUtil {

    private TraceUtil() {}

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
}
