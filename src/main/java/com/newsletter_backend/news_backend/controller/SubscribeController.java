package com.newsletter_backend.news_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.newsletter_backend.news_backend.data.authentication.UserAuthDocument;
import com.newsletter_backend.news_backend.data.authentication.UserAuthRepository;
import com.newsletter_backend.news_backend.model.SubscribeModel;

import at.favre.lib.crypto.bcrypt.BCrypt;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SubscribeController {
    private static Logger log = LoggerFactory.getLogger(SubscribeController.class);

    UserAuthRepository userAuthRepo;

    @Autowired
    public SubscribeController(UserAuthRepository userAuthRepo) {
        this.userAuthRepo = userAuthRepo;
    }

    @PostMapping("/auth/subscribe")
    public Mono<UserAuthDocument> authenticateToSubscribe(@RequestBody SubscribeModel subscribeEntity) {
        return userAuthRepo
                .findByEmail(subscribeEntity.getEmail())
                .flatMap(email -> {
                    log.info("email, {}, already exists", email);
                    return Mono.just(email);
                })
                .switchIfEmpty( // this basically means user was not found, continue registration
                        Mono.defer(() -> Mono.fromCallable(() -> {
                            UserAuthDocument userAuthDoc = new UserAuthDocument();
                            userAuthDoc.setID(UUID.randomUUID().toString());
                            userAuthDoc.setEmail(subscribeEntity.getEmail());
                            userAuthDoc.setEnabled(true);
                            userAuthDoc.setUuid(UUID.randomUUID());
                            userAuthDoc.setTopics(subscribeEntity.getTopics());
                            return userAuthDoc;
                        })
                                .subscribeOn(Schedulers.boundedElastic())
                                .flatMap(r -> userAuthRepo.save(r))))
                .onErrorResume(e -> {
                    log.error("Error during registration", e);
                    return Mono.empty();
                });
    }

}
