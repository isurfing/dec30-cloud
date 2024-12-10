package cn.dec30.cloud.base.util;

import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;

import java.util.Arrays;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/11/9 17:46
 * @description 异常处理工具
 */
public class ThrowableUtil {

    private ThrowableUtil() {

    }

    public static String genStackString(Throwable e) {
        StringBuilder builder = new StringBuilder();
        builder.append(e).append("\r\n");
        Arrays.stream(steArrayToStepArray(e.getStackTrace()))
                .limit(5)
                .forEach(e2 -> {
                    builder.append("\t").append(e2.toString());
                    ThrowableProxyUtil.subjoinPackagingData(builder, e2);
                    builder.append(CoreConstants.LINE_SEPARATOR);
                });
        return builder.toString();
    }

    private static StackTraceElementProxy[] steArrayToStepArray(StackTraceElement[] stacks) {
        if(stacks == null) {
            return new StackTraceElementProxy[0];
        }

        StackTraceElementProxy[] proxies = new StackTraceElementProxy[stacks.length];
        for (int i = 0; i < proxies.length; i++) {
            proxies[i] = new StackTraceElementProxy(stacks[i]);
        }

        return proxies;
    }
}
