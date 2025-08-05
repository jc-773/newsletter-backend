package com.newsletter_backend.news_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.newsletter_backend.news_backend.data.authentication.UserAuthDocument;
import com.newsletter_backend.news_backend.data.authentication.UserAuthRepository;
import com.newsletter_backend.news_backend.model.SubscribeModel;

import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UnsubscribeController {
    private static Logger log = LoggerFactory.getLogger(UnsubscribeController.class);

    UserAuthRepository userAuthRepo;

    public UnsubscribeController(UserAuthRepository userAuthRepo) {
        this.userAuthRepo = userAuthRepo;
    }

    @PostMapping("/auth/unsubscribe")
    public Mono<String> unsubscribe(@RequestBody SubscribeModel unsubscribeEntity) {
        return Mono.fromCallable(() -> {
            UserAuthDocument userAuthDoc = new UserAuthDocument();
            userAuthRepo.delete(userAuthDoc);
            log.info("User, {}, successfully unsubscribed...");
            return "User successfully unsubscribed...";
        });
    }

}
