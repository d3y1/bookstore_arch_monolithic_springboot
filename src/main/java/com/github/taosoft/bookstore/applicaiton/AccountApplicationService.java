package com.github.taosoft.bookstore.applicaiton;

import com.github.taosoft.bookstore.domain.account.Account;
import com.github.taosoft.bookstore.domain.account.AccountRepository;
import com.github.taosoft.bookstore.infrastructure.utility.Encryption;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * 用户资源的应用服务接口
 **/
@Named
@Transactional
public class AccountApplicationService {

    @Inject
    private AccountRepository repository;

    @Inject
    private Encryption encoder;

    public void createAccount(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        repository.save(account);
    }

    public Account findAccountByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void updateAccount(Account account) {
        repository.save(account);
    }

}
