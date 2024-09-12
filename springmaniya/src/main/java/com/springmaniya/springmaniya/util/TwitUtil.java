package com.springmaniya.springmaniya.util;

import com.springmaniya.springmaniya.model.Like;
import com.springmaniya.springmaniya.model.Twit;
import com.springmaniya.springmaniya.model.User;

public class TwitUtil {

    public final static  boolean isLikedByReqUser(User reqUser, Twit twit){
        for(Like like: twit.getLikes()){
            if(like.getUser().getId().equals(reqUser.getId())){
                return true;
            }
        }
        return  false;
    }

    public final static boolean isRetwitedByReqUser(User reqUser,Twit twit){
        for(User user: twit.getRetwitUser()){
            if(user.getId().equals(reqUser.getId())){
                return true;
            }
        }
        return  false;
    }

}
