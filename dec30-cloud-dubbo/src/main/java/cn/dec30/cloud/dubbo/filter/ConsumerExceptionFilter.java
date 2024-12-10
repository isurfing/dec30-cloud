package cn.dec30.cloud.dubbo.filter;

import cn.dec30.cloud.base.exception.CloudException;
import cn.dec30.cloud.dubbo.exception.RpcInvokeException;
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
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        if(appResponse.hasException()) {
            Throwable exe = appResponse.getException();
            if(exe instanceof RpcInvokeException rExe) {
                appResponse.setException(new RpcInvokeException(rExe.getCode(), rExe.getMsg()));
            }
        }
    }

    @Override
    public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
    }
}
