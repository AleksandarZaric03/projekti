package com.example.demo.Controller;

import com.example.demo.DTO.AccountHasReviewDTO;
import com.example.demo.DTO.CityDTO;
import com.example.demo.Model.Account;
import com.example.demo.Model.AccountHasReview;
import com.example.demo.Model.City;
import com.example.demo.Model.Review;
import com.example.demo.Service.AccountHasReviewService;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/nalogrecenzija")
public class AccountHasReviewController {
    
    @Autowired
    private AccountHasReviewService accountHasReviewService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(path = "",method = RequestMethod.GET)
    public ResponseEntity<List<AccountHasReviewDTO>> getAllFavourites(){
        List<AccountHasReviewDTO> favoriti = new ArrayList<AccountHasReviewDTO>();

        for(AccountHasReview c : accountHasReviewService.findAll()){
            favoriti.add(c.toDto());
        }

        return new ResponseEntity<List<AccountHasReviewDTO>>(favoriti, HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<AccountHasReviewDTO> createFavourite(@RequestBody AccountHasReviewDTO noviFavorit) {
        Optional<Account> accountOpt = accountService.findById(noviFavorit.getAccountDTO().getId());
        Optional<Review> reviewOpt = reviewService.findById(noviFavorit.getReviewDTO().getId());

        if (accountOpt.isEmpty() || reviewOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AccountHasReview fav = new AccountHasReview();
        fav.setAccount(accountOpt.get()); // managed Account
        fav.setReview(reviewOpt.get());   // managed Review

        AccountHasReview saved = accountHasReviewService.save(fav);
        return new ResponseEntity<>(saved.toDto(), HttpStatus.CREATED);
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<AccountHasReviewDTO>> getFavouritebyAccountId(@PathVariable("id") Long id){

        Optional<List<AccountHasReview>> favourites = accountHasReviewService.findByAccountId(id);
        List<AccountHasReviewDTO> favoritiDTO = new ArrayList<>();
        if(favourites.isPresent()){
            for (AccountHasReview a: favourites.get()){
                favoritiDTO.add(a.toDto());
            }
        }
        return new ResponseEntity<List<AccountHasReviewDTO>>(favoritiDTO, HttpStatus.OK);
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AccountHasReviewDTO> updateFavourite(@PathVariable("id") Long id, @RequestBody AccountHasReviewDTO noviFavorit){
        Optional<AccountHasReview> favorit = accountHasReviewService.findById(id);

        if(favorit.isPresent()){
            favorit.get().setId(id);
            favorit.get().setAccount(noviFavorit.getAccountDTO().toEntity());
            favorit.get().setReview(noviFavorit.getReviewDTO().toEntity());
            accountHasReviewService.save(favorit.get());
            return new ResponseEntity<AccountHasReviewDTO>(favorit.get().toDto(),HttpStatus.OK);
        }
        return new ResponseEntity<AccountHasReviewDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<AccountHasReviewDTO> deleteCity(@PathVariable("id") Long id){

        Optional<AccountHasReview> favorit = accountHasReviewService.findById(id);

        if(favorit.isPresent()){
            accountHasReviewService.delete(id);
            return new ResponseEntity<AccountHasReviewDTO>(favorit.get().toDto(),HttpStatus.OK);
        }

        return new ResponseEntity<AccountHasReviewDTO>(HttpStatus.NOT_FOUND);
    }
}
