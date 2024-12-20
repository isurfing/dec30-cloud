package cn.dec30.cloud.provider.service;

import cn.dec30.cloud.base.exception.CloudException;
import cn.dec30.cloud.dubbo.exception.RpcInvokeException;
import cn.dec30.cloud.provider.dto.UserDTO;

import java.util.List;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/24 23:10
 * @description todo
 */
public interface IUserService {

    List<UserDTO> getUsers() throws RpcInvokeException;
}
