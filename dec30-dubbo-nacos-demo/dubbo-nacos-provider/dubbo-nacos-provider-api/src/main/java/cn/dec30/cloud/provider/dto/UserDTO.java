package cn.dec30.cloud.provider.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/24 23:09
 * @description todo
 */

@Data
public class UserDTO implements Serializable {

    private String id;
    private String name;
    private String address;
}
