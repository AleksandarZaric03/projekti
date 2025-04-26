package com.example.demo.Repository;


import com.example.demo.Model.AccountHasReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHasReviewRepository extends CrudRepository<AccountHasReview,Long> {
    Optional<List<AccountHasReview>> findAccountHasReviewsByAccount_Id(Long accountId);
}
