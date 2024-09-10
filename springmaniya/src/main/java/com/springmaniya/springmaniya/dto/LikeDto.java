package com.springmaniya.springmaniya.dto;

import com.springmaniya.springmaniya.model.Twit;
import com.springmaniya.springmaniya.model.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class LikeDto {

    private Long id;

    private UserDto user;


    private TwitDto twit;
}
