package com.springmaniya.springmaniya.repository;

import com.springmaniya.springmaniya.model.Twit;
import com.springmaniya.springmaniya.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TwitRepository extends JpaRepository<Twit,Long> {

    List<Twit> findAllByIsTwitTrueOrderByCreatedAtDesc();

    List<Twit> findByRetwitUserContainingOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(User user,Long userId);

    List<Twit> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("Select t From Twit t JOIN t.likes l where l.user.id=:userId")
    List<Twit> findByLikesUser_id(Long userId);
}
