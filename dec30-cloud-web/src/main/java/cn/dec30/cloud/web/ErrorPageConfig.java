package cn.dec30.cloud.web;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/30 23:56
 * @description 错误页面配置
 */
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = {
                // 若指定了静态的html页面,则该页面需要存储在 static/ 路径下
                new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html"),
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html"),
                // 如果需要指定 templates/ 路径下的页面,则需要配合get请求的方式,通过ModelAndView进行跳转
//                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/common/500error"),
        };

        registry.addErrorPages(errorPages);

    }
}
