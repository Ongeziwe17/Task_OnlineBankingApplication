package com.myproject.codealpha.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
public class Contact {
    @Id
    private String email;
    private String mobile;

    protected Contact(){}

    public Contact(Builder builder) {
        this.email = builder.email;
        this.mobile = builder.mobile;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Contact contact = (Contact) object;
        return Objects.equals(email, contact.email) && Objects.equals(mobile, contact.mobile);
    }
    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    @Override
    public int hashCode() { return Objects.hash(email, mobile); }

    public static class Builder{
        private String email;
        private String mobile;
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder copy(Contact contact){
            this.email = contact.email;
            this.mobile = contact.mobile;
            return this;
        }
        public Contact build(){ return new Contact(this); }
    }
}
