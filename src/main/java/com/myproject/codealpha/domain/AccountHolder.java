package com.myproject.codealpha.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class AccountHolder {
    @Id
    private long accountHolderId;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_address")
    private Contact contact;

    protected AccountHolder(){}

    public AccountHolder(Builder builder) {
        this.accountHolderId = builder.accountHolderId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.contact = builder.contact;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AccountHolder that = (AccountHolder) object;
        return Objects.equals(accountHolderId, that.accountHolderId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(contact, that.contact);
    }
    @Override
    public int hashCode() { return Objects.hash(accountHolderId, firstName, lastName, contact); }
    @Override
    public String toString() {
        return "AccountHolder{" +
                "accountHolderId=" + accountHolderId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact=" + contact +
                '}';
    }

    public static class Builder{
        private long accountHolderId;
        private String firstName;
        private String lastName;
        private Contact contact;

        public Builder setAccountHolderId(long accountHolderId) {
            this.accountHolderId = accountHolderId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }
        public Builder copy(AccountHolder accountHolder){
            this.accountHolderId = accountHolder.accountHolderId;
            this.firstName = accountHolder.firstName;
            this.lastName = accountHolder.lastName;
            this.contact = accountHolder.contact;
            return this;
        }
        public AccountHolder build(){ return new AccountHolder(this); }
    }
}
