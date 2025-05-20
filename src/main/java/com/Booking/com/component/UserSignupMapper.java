package com.Booking.com.component;


import com.Booking.com.dto.UserSignupDto;
import com.Booking.com.entity.UserSignup;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserSignupMapper {

    private ModelMapper modelMapper;

    public UserSignupMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


     public UserSignupDto mapToDto(UserSignup userSignup){
         UserSignupDto mapToDto = modelMapper.map(userSignup, UserSignupDto.class);
         return mapToDto;

     }

     public UserSignup mapToEntity(UserSignupDto userSignupDto){
         UserSignup mapToEntity = modelMapper.map(userSignupDto, UserSignup.class);
         return mapToEntity;
     }
}
