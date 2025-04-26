package com.example.demo.Controller;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.Model.Account;
import com.example.demo.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/nalozi")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        List<AccountDTO> accounts = new ArrayList<AccountDTO>();

        for(Account k : accountService.findAll()){
            accounts.add(k.toDto());
        }
        return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO noviAccount){

        Account rezultat = accountService.save(noviAccount.toEntity());
        if(rezultat != null){
            return new ResponseEntity<AccountDTO>(rezultat.toDto(),HttpStatus.CREATED);
        }

        return new ResponseEntity<AccountDTO>(HttpStatus.OK);
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") Long id){

        Optional<Account> account = accountService.findById(id);

        if(account.isPresent()){
            return new ResponseEntity<AccountDTO>(account.get().toDto(),HttpStatus.OK);
        }
        return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable("id") Long id, @RequestBody AccountDTO noviAccount){
        Optional<Account> account = accountService.findById(id);

        if(account.isPresent()){
            account.get().setId(id);
            account.get().setUsername(noviAccount.getUsername());
            account.get().setPassword(noviAccount.getPassword());
            accountService.save(account.get());
            return new ResponseEntity<AccountDTO>(account.get().toDto(),HttpStatus.OK);
        }
        return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable("id") Long id){

        Optional<Account> korisnik = accountService.findById(id);

        if(korisnik.isPresent()){
            accountService.delete(id);
            return new ResponseEntity<AccountDTO>(korisnik.get().toDto(),HttpStatus.OK);
        }

        return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
    }

}
