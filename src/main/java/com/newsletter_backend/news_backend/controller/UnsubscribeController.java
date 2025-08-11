package com.newsletter_backend.news_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.newsletter_backend.news_backend.data.authentication.UserAuthDocument;
import com.newsletter_backend.news_backend.data.authentication.UserAuthRepository;
import com.newsletter_backend.news_backend.model.SubscribeModel;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
    public Mono<UserAuthDocument> unsubscribe(@RequestBody SubscribeModel unsubscribeEntity) {
        return userAuthRepo
            .findByEmail(unsubscribeEntity.getEmail())
            .switchIfEmpty( Mono.fromRunnable(() -> {
                logUserNotFound();
            }))
            .doOnNext(l -> logPipeline(l))
            .flatMap(u -> userAuthRepo.delete(u).thenReturn(u))
            .subscribeOn(Schedulers.boundedElastic());
    }

    private void logPipeline(UserAuthDocument u) {
        log.info("user {} deleted successfully", u);
    }

    private void logUserNotFound() {
        log.info("user not found");
    }
}
