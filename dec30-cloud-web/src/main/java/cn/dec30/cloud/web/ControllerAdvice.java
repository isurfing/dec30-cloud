package cn.dec30.cloud.web;

import cn.dec30.cloud.base.exception.CloudError;
import cn.dec30.cloud.base.exception.CloudException;
import cn.dec30.cloud.base.exception.Error;
import cn.dec30.cloud.base.util.TraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/25 22:02
 * @description 统一异常处理
 */
@Slf4j
@RestControllerAdvice(annotations = StandardResponse.class)
public final class ControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body == null) {
            return null;
        } else if(body instanceof WebResult || body instanceof byte[]) {
            return body;
        } else if(body instanceof Map) {
            return WebResult.ok(body);
        } else {
            return WebResult.error(CloudError.ILLEGAL_OUTPUT);
        }
    }

    @ExceptionHandler(Exception.class)
    public WebResult<Void> handleException(Exception ex) {
        log.error("出现未知错误", ex);
        TraceUtil.getSpan().error(ex);
        return WebResult.error(CloudError.SYS_BUSY);
    }

    @ExceptionHandler(CloudException.class)
    public WebResult<Void> handleCloudException(CloudException ce) {
        log.error("出现业务错误", ce);
        TraceUtil.getSpan().error(ce);
        return WebResult.error(ce.getError());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResult<Void> handleParamException(MethodArgumentNotValidException mEx) {
        log.error("出现参数错误", mEx);
        BindingResult bindingResult = mEx.getBindingResult();
        String msg = bindingResult
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        Error error = Error.build(CloudError.INVALID_PARAM.getCode(), msg);
        TraceUtil.getSpan().error(new CloudException(error));
        return WebResult.error(error);
    }




}
