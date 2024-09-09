package com.springmaniya.springmaniya.controller;

import com.springmaniya.springmaniya.service.TwitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/twits")
public class TwitController {

    private TwitService twitService;


}
