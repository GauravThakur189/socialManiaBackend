package com.springmaniya.springmaniya.service;

import com.springmaniya.springmaniya.exception.TwitEception;
import com.springmaniya.springmaniya.exception.UserException;
import com.springmaniya.springmaniya.model.Twit;
import com.springmaniya.springmaniya.model.User;
import com.springmaniya.springmaniya.repository.TwitRepository;
import com.springmaniya.springmaniya.request.TwitReplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TwitServiceImplementation implements  TwitService{

    @Autowired
    private TwitRepository twitRepository;

    @Override
    public Twit createTwit(Twit req, User user) throws UserException {
          Twit twit = new Twit();
          twit.setContent(req.getContent());
          twit.setCreatedAt(LocalDateTime.now());
          twit.setImage(req.getImage());
          twit.setUser(user);
          twit.setReply(false);
          twit.setTwit(true);
          twit.setVideo(req.getVideo());

          return twitRepository.save(twit);
    }

    @Override
    public List<Twit> findAllTwit() {

        return twitRepository.findAllByIsTwitTrueOrderByCreatedAtDesc();

    }

    @Override
    public Twit retwit(Long twitId, User user) throws UserException, TwitEception {
        Twit twit = findById(twitId);
        if(twit.getRetwitUser().contains(user)){
            twit.getRetwitUser().remove(user);
        }
        else{
            twit.getRetwitUser().add(user);
        }
        return twitRepository.save(twit);
    }

    @Override
    public Twit findById(Long twitId) throws TwitEception {
        Twit twit = twitRepository.findById(twitId)
                .orElseThrow(()-> new TwitEception("twit not found with id "+twitId));
        return twit;
    }

    @Override
    public void deleteTwitById(Long twitId, Long userId) throws TwitEception, UserException {
        Twit twit = findById(twitId);

        if(!userId.equals(twit.getUser().getId())){
            throw  new UserException("you can't delete another user's twit");
        }
        twitRepository.deleteById(twit.getId());

    }

    @Override
    public Twit removeFromRetwit(Long twitId, User user) throws TwitEception, UserException {
        return null;
    }

    @Override
    public Twit createReply(TwitReplyRequest req, User user) throws TwitEception {

        Twit replyFor = findById(req.getTwitId());

        Twit twit = new Twit();
        twit.setContent(req.getContent());
        twit.setCreatedAt(LocalDateTime.now());
        twit.setImage(req.getImage());
        twit.setUser(user);
        twit.setReply(true);
        twit.setTwit(false);
        twit.setReplyFor(replyFor);

        Twit savedReply = twitRepository.save(twit);
        twit.getReplyTwits().add(savedReply);
        twitRepository.save(replyFor);


        return replyFor;
    }

    @Override
    public List<Twit> getUserTwit(User user) {
        return twitRepository.findByRetwitUserContainingOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(user,user.getId());
    }

    @Override
    public List<Twit> findByLikesContainsUser(User user) {
        return twitRepository.findByLikesUser_id(user.getId());
    }
}
