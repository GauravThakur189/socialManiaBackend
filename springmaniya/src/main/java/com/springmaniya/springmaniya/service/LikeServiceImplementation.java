package com.springmaniya.springmaniya.service;

import com.springmaniya.springmaniya.exception.TwitEception;
import com.springmaniya.springmaniya.exception.UserException;
import com.springmaniya.springmaniya.model.Like;
import com.springmaniya.springmaniya.model.Twit;
import com.springmaniya.springmaniya.model.User;
import com.springmaniya.springmaniya.repository.LikeRepository;
import com.springmaniya.springmaniya.repository.TwitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImplementation implements  LikeService{

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TwitService twitService;

    @Autowired
    private TwitRepository twitRepository;

    @Override
    public Like likeTwit(Long twitId, User user) throws UserException, TwitEception {

        Like isLikeExist = likeRepository.isLikeExist(user.getId(),twitId);
        if(isLikeExist != null){
           likeRepository.deleteById(isLikeExist.getId());
           return isLikeExist;
        }
        Twit twit = twitService.findById(twitId);

        Like like = new Like();
        like.setTwit(twit);
        like.setUser(user);
        Like savedLike = likeRepository.save(like);

        twit.getLikes().add(savedLike);
        twitRepository.save(twit);

        return  savedLike;
    }

    @Override
    public List<Like> getAllLikes(Long twitId) throws TwitEception {

        Twit twit = twitService.findById(twitId);

        List<Like> likes = likeRepository.findByTwitId(twitId);

        return likes;
    }
}
