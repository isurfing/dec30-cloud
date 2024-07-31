package cn.dec30.cloud.provider.service.provider.fallback;

import cn.dec30.cloud.base.exception.CloudException;
import cn.dec30.cloud.provider.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/31 20:58
 * @description todo
 */
@Slf4j
public class UserServiceProviderFallback {

    public static List<UserDTO> getUsers(Throwable throwable) {
        log.error("======fallback======", throwable);
        throw new CloudException(503, "fallback");
    }
}
