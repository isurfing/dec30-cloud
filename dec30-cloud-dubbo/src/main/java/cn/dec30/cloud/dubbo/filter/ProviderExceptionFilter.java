package cn.dec30.cloud.dubbo.filter;

import cn.dec30.cloud.base.exception.CloudException;
import cn.dec30.cloud.base.exception.Error;
import cn.dec30.cloud.dubbo.exception.RpcErrorResult;
import cn.dec30.cloud.dubbo.exception.DubboRpcError;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.extension.DisableInject;
import org.apache.dubbo.common.logger.ErrorTypeAwareLogger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.support.RpcUtils;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/31 21:48
 * @description 全局异常处理
 */
@Activate(group = CommonConstants.PROVIDER)
public class ProviderExceptionFilter implements Filter, BaseFilter.Listener {

    private ErrorTypeAwareLogger logger = LoggerFactory.getErrorTypeAwareLogger(ProviderExceptionFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result result, Invoker<?> invoker, Invocation invocation) {
        if (result.hasException()) {
            RpcErrorResult rpcErrorResult;
            Throwable exception = result.getException();
            if(exception instanceof CloudException) {
                Error error = ((CloudException) exception).getError();
                rpcErrorResult = RpcErrorResult.builder()
                        .msg(error.getMsg())
                        .code(error.getCode())
                        .build();
            } else {
                rpcErrorResult = RpcErrorResult.builder()
                        .msg(DubboRpcError.UNKNOWN_ERROR.getMsg())
                        .code(DubboRpcError.UNKNOWN_ERROR.getCode())
                        .build();
            }
            result.setObjectAttachment(RpcErrorResult.ERROR_ARG_KEY, rpcErrorResult);
        }
    }

    @Override
    public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
        this.logger.error("5-36", "", "", "Got unchecked and undeclared exception which called by " + RpcContext.getServiceContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + RpcUtils.getMethodName(invocation) + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
    }

    @DisableInject
    public void mockLogger(ErrorTypeAwareLogger logger) {
        this.logger = logger;
    }
}
