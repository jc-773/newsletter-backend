package com.newsletter_backend.news_backend.model;

import java.util.List;

public class SubscribeModel {
    private String email;
    private String password;
    private boolean enabled;
    private List<String> topics;

    public SubscribeModel(String email, String password, boolean enabled, List<String> topics) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.topics = topics;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public List<String> getTopics() {
        return topics;
    }
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
