package com.springmaniya.springmaniya.service;

import com.springmaniya.springmaniya.exception.TwitEception;
import com.springmaniya.springmaniya.exception.UserException;
import com.springmaniya.springmaniya.model.Twit;
import com.springmaniya.springmaniya.model.User;
import com.springmaniya.springmaniya.request.TwitReplyRequest;

import java.util.List;

public interface TwitService {

    public Twit createTwit(Twit req , User user) throws UserException;

    public List<Twit> findAllTwit();
    public Twit retwit(Long twitId, User user) throws  UserException, TwitEception;
    public Twit findById(Long twitId) throws  TwitEception;

    public void deleteTwitById(Long twitId,Long userId) throws  TwitEception,UserException;

    public Twit removeFromRetwit(Long twitId,User user) throws TwitEception,UserException;

    public  Twit createReply(TwitReplyRequest req, User user) throws  TwitEception;

    public List<Twit> getUserTwit(User user);

    public List<Twit> findByLikesContainsUser(User user);
}
