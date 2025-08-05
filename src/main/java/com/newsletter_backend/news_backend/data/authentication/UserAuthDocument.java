package com.newsletter_backend.news_backend.data.authentication;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserAuth")
public class UserAuthDocument {
    @Id
    private String ID;
    private UUID uuid;
    private String email;
    private String hashedPassword;
    private boolean isEnabled;
    private List<String> topics;

    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public List<String> getTopics() {
        return topics;
    }
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
