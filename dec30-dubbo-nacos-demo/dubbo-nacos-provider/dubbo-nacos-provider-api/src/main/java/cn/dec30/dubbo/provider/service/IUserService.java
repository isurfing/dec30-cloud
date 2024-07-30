package cn.dec30.dubbo.provider.service;

import cn.dec30.dubbo.provider.dto.UserDTO;

import java.util.List;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/24 23:10
 * @description todo
 */
public interface IUserService {

    List<UserDTO> getUsers();
}
