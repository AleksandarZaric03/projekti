package com.example.demo.Service;

import com.example.demo.Model.Account;
import com.example.demo.Model.AccountHasReview;
import com.example.demo.Repository.AccountHasReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountHasReviewService {

    @Autowired
    private AccountHasReviewRepository accountHasReviewRepository;

    public  Iterable<AccountHasReview> findAll(){
        return accountHasReviewRepository.findAll();
    }

    public Optional<AccountHasReview> findById(Long id){

        return accountHasReviewRepository.findById(id);
    }

    public AccountHasReview save(AccountHasReview accountHasReview){

        return accountHasReviewRepository.save(accountHasReview);
    }

    public void delete(AccountHasReview accountHasReview){

        accountHasReviewRepository.delete(accountHasReview);
    }

    public void delete(Long id){

        accountHasReviewRepository.deleteById(id);
    }

    public Optional<List<AccountHasReview>> findByAccountId(Long accountId){
        return accountHasReviewRepository.findAccountHasReviewsByAccount_Id(accountId);
    }
}
