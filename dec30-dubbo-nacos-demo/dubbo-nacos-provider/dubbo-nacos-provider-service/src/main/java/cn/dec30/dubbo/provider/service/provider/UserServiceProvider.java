package cn.dec30.dubbo.provider.service.provider;

import cn.dec30.base.exception.CloudException;
import cn.dec30.dubbo.provider.dto.UserDTO;
import cn.dec30.dubbo.provider.service.IUserService;
import cn.dec30.dubbo.provider.service.provider.block.UseServiceProviderBlock;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

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
    @SentinelResource(value = "fucking",
            fallback = "fuckf", blockHandler = "fuckj")
    public List<UserDTO> getUsers() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("zhegourong");
        userDTO.setAddress("HONGKONG");
        userDTO.setId(UUID.randomUUID().toString());

        log.info("getUsers: {}", userDTO);
        return Collections.singletonList(userDTO);
    }


    public List<UserDTO> fuckf(Throwable f) {
        log.error("======fallback======");
        throw new CloudException(503, "fallback");
    }

    public List<UserDTO> fuckj(BlockException f) {
        log.error("======block======");
        UserDTO userDTO = new UserDTO();
        userDTO.setName("block");
        userDTO.setAddress("block");
        userDTO.setId("block");

        log.info("getUsers: {}", userDTO);
        return Collections.singletonList(userDTO);
    }
}
