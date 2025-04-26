package com.example.demo.DTO;

import com.example.demo.Model.AccountHasReview;

public class AccountHasReviewDTO {
    
    private Long id;
    
    private AccountDTO account;
    
    private ReviewDTO review;

    public AccountHasReviewDTO(Long id, AccountDTO accountDTO, ReviewDTO reviewDTO) {
        this.account = accountDTO;
        this.id = id;
        this.review = reviewDTO;
    }

    public AccountHasReviewDTO() {
    }

    public AccountDTO getAccountDTO() {
        return account;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.account = accountDTO;
    }

    public ReviewDTO getReviewDTO() {
        return review;
    }

    public void setReviewDTO(ReviewDTO reviewDTO) {
        this.review = reviewDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public AccountHasReview toEntity(){
        return new AccountHasReview(this.id,this.account.toEntity(), this.review.toEntity());
    }
}
