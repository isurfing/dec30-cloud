package cn.dec30.cloud.dubbo.exception;

import lombok.Getter;
import lombok.Setter;
import cn.dec30.cloud.base.exception.Error;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/10/15 15:16
 * @description todo
 */
@Setter
@Getter
public class RpcInvokeException extends RuntimeException {

    private int code;
    private String msg;

    public RpcInvokeException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RpcInvokeException build(Error error) {
        return new RpcInvokeException(error.getCode(), error.getMsg());
    }
}
