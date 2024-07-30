package cn.dec30.dubbo.conusmer.controller;

import cn.dec30.cloud.web.Result;
import cn.dec30.cloud.web.StandardResponse;
import cn.dec30.dubbo.conusmer.service.UserService;
import cn.dec30.dubbo.conusmer.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/24 22:46
 * @description todo
 */
@Slf4j
@RestController
@StandardResponse
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public Result<List<UserVO>> getUsers() {
        return Result.ok(userService.getUsers());
    }
}
