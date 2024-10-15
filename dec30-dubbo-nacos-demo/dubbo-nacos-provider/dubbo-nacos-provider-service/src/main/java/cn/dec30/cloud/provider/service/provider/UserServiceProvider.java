package cn.dec30.cloud.provider.service.provider;

import cn.dec30.cloud.base.exception.CloudError;
import cn.dec30.cloud.base.exception.CloudException;
import cn.dec30.cloud.base.util.TraceUtil;
import cn.dec30.cloud.dubbo.exception.RpcInvokeException;
import cn.dec30.cloud.provider.dto.UserDTO;
import cn.dec30.cloud.provider.service.IUserService;
import cn.dec30.cloud.provider.service.provider.block.UseServiceProviderBlock;
import cn.dec30.cloud.provider.service.provider.fallback.UserServiceProviderFallback;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.sleuth.Span;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/24 23:16
 * @description todo
 */
@Slf4j
@DubboService(version = "${dubbo.version}")
public class UserServiceProvider implements IUserService {
    @Override
//    @SentinelResource(value = "IUserService:getUsers",
//            fallback = "getUsers", fallbackClass = UserServiceProviderFallback.class,
//            blockHandler = "getUsers", blockHandlerClass = UseServiceProviderBlock.class)
    public List<UserDTO> getUsers() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("张国荣");
        userDTO.setAddress("HONGKONG");
        userDTO.setId(UUID.randomUUID().toString());
        log.info("getUsers: {}", userDTO);
//        return Collections.singletonList(userDTO);
        throw RpcInvokeException.build(CloudError.ILLEGAL_OUTPUT);
    }
}
