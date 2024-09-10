package com.springmaniya.springmaniya.mapper;

import com.springmaniya.springmaniya.dto.LikeDto;
import com.springmaniya.springmaniya.dto.TwitDto;
import com.springmaniya.springmaniya.dto.UserDto;
import com.springmaniya.springmaniya.model.Like;
import com.springmaniya.springmaniya.model.User;

import java.util.ArrayList;
import java.util.List;

public class LikeDtoMapper {

    public static LikeDto tolikeDto(Like like, User reqUser){

        UserDto user = UserDtoMapper.toUserDto(like.getUser());
        UserDto reqUserDto = UserDtoMapper.toUserDto(reqUser);
        TwitDto twit = TwitDtoMapper.toTwitDto(like.getTwit(),reqUser);


        LikeDto likeDto = new LikeDto();
        likeDto.setId(like.getId());
        likeDto.setTwit(twit);
        likeDto.setUser(user);

        return likeDto;
    }
    public static List<LikeDto> toLikeDtos(List<Like> likes,User reqUser){
        List<LikeDto> likeDtos = new ArrayList<>();

        for(Like like:likes){
            UserDto user = UserDtoMapper.toUserDto(like.getUser());
            TwitDto twit = TwitDtoMapper.toTwitDto(like.getTwit(),reqUser);

            LikeDto likeDto = new LikeDto();
            likeDto.setId(like.getId());
            likeDto.setTwit(twit);
            likeDto.setUser(user);
            likeDtos.add(likeDto);



        }
        return likeDtos;
    }
}
