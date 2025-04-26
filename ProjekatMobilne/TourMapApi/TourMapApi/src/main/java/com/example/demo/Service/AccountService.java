package com.example.demo.Service;

import com.example.demo.Model.Account;
import com.example.demo.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public  Iterable<Account> findAll(){
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long id){return accountRepository.findById(id);}

    public Account save(Account account){return accountRepository.save(account);}

    public void delete(Account account){accountRepository.delete(account);}

    public void delete(Long id){accountRepository.deleteById(id);}
}
