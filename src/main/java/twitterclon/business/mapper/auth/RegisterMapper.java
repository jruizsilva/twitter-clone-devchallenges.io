package twitterclon.business.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import twitterclon.domain.dto.auth.RegisterDto;
import twitterclon.domain.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegisterMapper {
    RegisterDto toRegisterDto(UserEntity userEntity);
}
