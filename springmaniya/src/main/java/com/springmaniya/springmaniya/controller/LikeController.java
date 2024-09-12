package com.springmaniya.springmaniya.controller;

import com.springmaniya.springmaniya.dto.LikeDto;
import com.springmaniya.springmaniya.exception.TwitEception;
import com.springmaniya.springmaniya.exception.UserException;
import com.springmaniya.springmaniya.mapper.LikeDtoMapper;
import com.springmaniya.springmaniya.model.Like;
import com.springmaniya.springmaniya.model.User;
import com.springmaniya.springmaniya.service.LikeService;
import com.springmaniya.springmaniya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LikeController {

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @PostMapping("/{twitId}/likes")
    public ResponseEntity<LikeDto> likeTwit(@PathVariable Long twitId,
                                            @RequestHeader("Authorization") String jwt)
                                      throws UserException, TwitEception {
        User user = userService.findUserProfileByJwt(jwt);
        Like like = likeService.likeTwit(twitId,user);

        LikeDto likeDto = LikeDtoMapper.tolikeDto(like,user);

        return new ResponseEntity<>(likeDto, HttpStatus.CREATED);
    }

    @PostMapping("/twit/{twitId}")
    public ResponseEntity<List<LikeDto>> getAllLikes(@PathVariable Long twitId,
                                                    @RequestHeader("Authorization") String jwt)
            throws UserException, TwitEception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Like> likes = likeService.getAllLikes(twitId);

        List<LikeDto> likeDtos = LikeDtoMapper.toLikeDtos(likes,user);

        return new ResponseEntity<>(likeDtos, HttpStatus.CREATED);
    }
}
