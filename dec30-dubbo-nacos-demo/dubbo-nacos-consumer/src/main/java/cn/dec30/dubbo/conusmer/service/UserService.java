package cn.dec30.dubbo.conusmer.service;

import cn.dec30.dubbo.conusmer.vo.UserVO;

import java.util.List;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/24 22:46
 * @description todo
 */
public interface UserService {

    List<UserVO> getUsers();
}
