package com.newsletter_backend.news_backend.data.authentication;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;

public interface UserAuthRepository extends ReactiveMongoRepository<UserAuthDocument, String> {
    Mono<UserAuthDocument> findByEmail(String email);
}
