package cn.dec30.cloud.web;

import cn.dec30.base.exception.CloudError;
import cn.dec30.base.exception.CloudException;
import cn.dec30.base.exception.Error;
import cn.dec30.base.util.TraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/25 22:02
 * @description 统一异常处理
 */
@Slf4j
@RestControllerAdvice(annotations = StandardResponse.class)
public final class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception ex) {
        log.error("出现未知错误", ex);
        TraceUtil.getSpan().error(ex);
        return Result.error(CloudError.SYS_BUSY);
    }

    @ResponseBody
    @ExceptionHandler(CloudException.class)
    public Result<Void> handleCloudException(CloudException ce) {
        log.error("出现业务错误", ce);
        TraceUtil.getSpan().error(ce);
        return Result.error(ce.getError());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleParamException(MethodArgumentNotValidException mEx) {
        log.error("出现参数错误", mEx);
        BindingResult bindingResult = mEx.getBindingResult();
        String msg = bindingResult
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        Error error = Error.build(CloudError.INVALID_PARAM.getCode(), msg);
        TraceUtil.getSpan().error(new CloudException(error));
        return Result.error(error);
    }




}
