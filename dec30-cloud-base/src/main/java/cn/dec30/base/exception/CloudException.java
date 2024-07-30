package cn.dec30.base.exception;

import lombok.Getter;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/25 22:09
 * @description 通用异常
 */
@Getter
public class CloudException extends RuntimeException{

    private final transient Error error;


    public CloudException(Error error) {
        super(error.getMsg());
        this.error = error;
    }

    public CloudException(int code, String msg) {
        super(msg);
        this.error = Error.build(code, msg);
    }

    public CloudException(Error error, String overrideMsg) {
        super(overrideMsg);
        this.error = Error.build(error.getCode(), overrideMsg);
    }


}
