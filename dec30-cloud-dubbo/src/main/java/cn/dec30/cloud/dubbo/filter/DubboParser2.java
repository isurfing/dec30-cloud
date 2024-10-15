package cn.dec30.cloud.dubbo.filter;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/10/15 15:48
 * @description todo
 */
import brave.Span;
import brave.internal.Nullable;
import brave.internal.Platform;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetSocketAddress;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

final class DubboParser2 {
    static final Map<Integer, String> ERROR_CODE_NUMBER_TO_NAME = errorCodeNumberToName();

    DubboParser2() {
    }

    static Map<Integer, String> errorCodeNumberToName() {
        Map<Integer, String> result = new LinkedHashMap();
        Field[] var1 = RpcException.class.getDeclaredFields();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Field field = var1[var3];
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.getType() == Integer.TYPE) {
                try {
                    result.put((Integer)field.get((Object)null), field.getName());
                } catch (Exception var6) {
                    assert false : var6.getMessage();

                    Platform.get().log("Error reading error code %s", field, var6);
                }
            }
        }

        return result;
    }

    @Nullable
    static String method(Invocation invocation) {
        String methodName = invocation.getMethodName();
        if ("$invoke".equals(methodName) || "$invokeAsync".equals(methodName)) {
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] instanceof String) {
                methodName = (String)arguments[0];
            } else {
                methodName = null;
            }
        }

        return methodName != null && !methodName.isEmpty() ? methodName : null;
    }

    @Nullable
    static String service(Invoker<?> invoker) {
        URL url = invoker.getUrl();
        if (url == null) {
            return null;
        } else {
            String service = url.getServiceInterface();
            return service != null && !service.isEmpty() ? service : null;
        }
    }

    static boolean parseRemoteIpAndPort(Span span) {
        RpcContext rpcContext = RpcContext.getContext();
        InetSocketAddress remoteAddress = rpcContext.getRemoteAddress();
        return remoteAddress == null ? false : span.remoteIpAndPort(Platform.get().getHostString(remoteAddress), remoteAddress.getPort());
    }

    @Nullable
    static String errorCode(Throwable error) {
        return error instanceof RpcException ? (String)ERROR_CODE_NUMBER_TO_NAME.get(((RpcException)error).getCode()) : null;
    }
}
