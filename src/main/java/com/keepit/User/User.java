package com.keepit.User;

import java.util.Objects;

public class User
{
    private String parent;

    private Boolean subscribed;

    private String product;

    private String created;

    private Boolean enabled;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public User() {
    }

    public User(String parent, Boolean subscribed, String product, String created, Boolean enabled) {
        this.parent = parent;
        this.subscribed = subscribed;
        this.product = product;
        this.created = created;
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getParent(), user.getParent()) &&
                Objects.equals(getSubscribed(), user.getSubscribed()) &&
                Objects.equals(getProduct(), user.getProduct()) &&
                Objects.equals(getCreated(), user.getCreated()) &&
                Objects.equals(getEnabled(), user.getEnabled());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParent(), getSubscribed(), getProduct(), getCreated(), getEnabled());
    }

    @Override
    public String toString()
    {
        return "[parent = "+parent+", subscribed = "+subscribed+", product = "+product+", created = "+created+", enabled = "+enabled+"]";
    }
}