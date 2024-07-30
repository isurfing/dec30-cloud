package cn.dec30.cloud.web;

import cn.dec30.base.exception.CloudError;
import cn.dec30.base.exception.Error;
import cn.dec30.base.util.TraceUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/25 22:04
 * @description 统一返回值-web
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private int code;
    private String msg;
    private T data;
    private String traceId;
    private String responseTime;

    public static <T> Result<T> ok(T data) {
        return Result.<T>builder()
                .data(data)
                .code(CloudError.OK.getCode())
                .msg(CloudError.OK.getMsg())
                .build();
    }

    public static <T> Result<T> error(Error error) {
        return Result.<T>builder()
                .msg(error.getMsg())
                .code(error.getCode())
                .traceId(TraceUtil.getTraceId())
                .responseTime(LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_MS_FORMATTER))
                .build();
    }
}
