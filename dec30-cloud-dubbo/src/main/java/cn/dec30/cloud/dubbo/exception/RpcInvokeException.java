package cn.dec30.cloud.dubbo.exception;

import lombok.Getter;
import lombok.Setter;
import cn.dec30.cloud.base.exception.Error;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/10/15 15:16
 * @description RPC调用异常
 */
@Setter
@Getter
public class RpcInvokeException extends RuntimeException {

    private int code;
    private String msg;

    public RpcInvokeException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public RpcInvokeException(int code, String msg, String traceMsg) {
        super(traceMsg);
        this.code = code;
        this.msg = msg;
    }

    public static RpcInvokeException build(Error error) {
        return new RpcInvokeException(error.getCode(), error.getMsg());
    }
}
