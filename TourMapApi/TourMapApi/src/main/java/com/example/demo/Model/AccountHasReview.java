package com.example.demo.Model;

import com.example.demo.DTO.AccountHasReviewDTO;
import jakarta.persistence.*;

@Entity
public class AccountHasReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review review;

    public AccountHasReview(Long id, Account account, Review review) {
        this.account = account;
        this.id = id;
        this.review = review;
    }

    public AccountHasReview() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
    
    public AccountHasReviewDTO toDto(){
        return new AccountHasReviewDTO(this.id,this.account.toDto(),this.review.toDto());
    }
}
