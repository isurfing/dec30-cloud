package cn.dec30.cloud.dubbo.filter;

import cn.dec30.cloud.base.exception.CloudException;
import cn.dec30.cloud.dubbo.exception.RpcErrorResult;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/31 21:48
 * @description 全局异常处理
 */
@Activate(group = CommonConstants.CONSUMER)
public class ConsumerExceptionFilter implements Filter, BaseFilter.Listener {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result result, Invoker<?> invoker, Invocation invocation) {
        if(result.hasException()) {
            RpcErrorResult error = (RpcErrorResult)result.getObjectAttachment(RpcErrorResult.ERROR_ARG_KEY);
            result.setException(new CloudException(error.getCode(), error.getMsg()));
        }
    }

    @Override
    public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
    }
}
