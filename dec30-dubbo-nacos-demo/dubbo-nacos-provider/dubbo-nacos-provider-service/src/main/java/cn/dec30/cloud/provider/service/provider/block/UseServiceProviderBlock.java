package cn.dec30.cloud.provider.service.provider.block;

import cn.dec30.cloud.base.exception.CloudException;
import cn.dec30.cloud.provider.dto.UserDTO;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/26 01:48
 * @description todo
 */
@Slf4j
public class UseServiceProviderBlock {

    public static List<UserDTO> getUsers(BlockException blockException) {
        log.error("======block======");
        throw new CloudException(503, "block");
    }
}
