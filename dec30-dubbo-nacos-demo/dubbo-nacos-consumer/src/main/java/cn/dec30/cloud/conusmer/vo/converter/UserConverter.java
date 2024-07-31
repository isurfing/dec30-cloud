package cn.dec30.cloud.conusmer.vo.converter;

import cn.dec30.cloud.conusmer.vo.UserVO;
import cn.dec30.cloud.provider.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/24 23:28
 * @description vo&dto 转换器
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserVO dto2Vo(UserDTO userDTO);
    List<UserVO> dto2Vo(List<UserDTO> userDTOList);
    UserDTO vo2Dto(UserVO userVO);
    List<UserDTO> vo2Dto(List<UserVO> userVOList);

}
