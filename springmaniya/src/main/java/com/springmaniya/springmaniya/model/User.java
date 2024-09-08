package com.springmaniya.springmaniya.model;


import jakarta.persistence.*;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private  String location;
    private  String website;
    private  String birthDate;
    private String email;
    private  String password;
    private  String mobile;
    private  String image;
    private  String backgroundImage;
    private  String bio;
    private  boolean req_user;
    private  boolean login_with_google;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Twit> twit = new ArrayList<Twit>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<Like>();

    @Embedded
    private Varification varification;

    @ManyToMany
    private  List<User> followers = new ArrayList<User>();

    @JsonIgnore
    @ManyToMany
    private  List<User> following = new ArrayList<User>();

}
