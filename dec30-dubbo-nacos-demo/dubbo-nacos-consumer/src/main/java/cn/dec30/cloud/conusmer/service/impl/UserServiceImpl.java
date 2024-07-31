package cn.dec30.cloud.conusmer.service.impl;

import cn.dec30.cloud.conusmer.service.UserService;
import cn.dec30.cloud.conusmer.vo.converter.UserConverter;
import cn.dec30.cloud.conusmer.vo.UserVO;
import cn.dec30.cloud.provider.dto.UserDTO;
import cn.dec30.cloud.provider.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/24 22:48
 * @description todo
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserConverter userConverter;

    @DubboReference(version = "${dubbo.version}")
    private IUserService iUserService;

    @Override
    public List<UserVO> getUsers() {
        log.info("getUsers start...");

        List<UserDTO> userDTOList = iUserService.getUsers();

        return userConverter.dto2Vo(userDTOList);
    }
}
