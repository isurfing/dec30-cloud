package cn.dec30.dubbo.provider.service.provider.block;

import cn.dec30.base.exception.CloudException;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/26 01:48
 * @description todo
 */
@Slf4j
@Component
public class UseServiceProviderBlock {

    public void getUsersBlock(BlockException blockException) {
        log.error("======block======");
        throw new CloudException(503, "block");
    }
}
