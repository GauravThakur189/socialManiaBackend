package com.springmaniya.springmaniya.service;

import com.springmaniya.springmaniya.exception.TwitEception;
import com.springmaniya.springmaniya.exception.UserException;
import com.springmaniya.springmaniya.model.Like;
import com.springmaniya.springmaniya.model.User;

import java.util.List;

public interface LikeService {

    public Like likeTwit(Long twitId, User user) throws UserException, TwitEception;

    public List<Like > getAllLikes(Long twitId) throws  TwitEception;


}
