package cn.dec30.cloud.dubbo.exception;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/31 22:27
 * @description todo
 */
@Data
@Builder
public class RpcErrorResult implements Serializable {

    private int code;
    private String msg;
    public static final String ERROR_ARG_KEY = "ERROR_ARG_KEY";
}
