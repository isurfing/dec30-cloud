package cn.dec30.cloud.dubbo.exception;

import cn.dec30.cloud.base.exception.Error;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/31 22:28
 * @description Rpc错误集
 */
public enum DubboRpcError implements Error {
    UNKNOWN_ERROR(503, "Rpc未知错误！"),
/*
providerExceptionFilter=cn.dec30.cloud.dubbo.filter.ProviderExceptionFilter
consumerExceptionFilter=cn.dec30.cloud.dubbo.filter.ConsumerExceptionFilter
 */
    ;

    private final int code;
    private final String msg;


    DubboRpcError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
