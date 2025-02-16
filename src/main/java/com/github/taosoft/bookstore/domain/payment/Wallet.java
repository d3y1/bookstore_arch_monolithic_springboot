package com.github.taosoft.bookstore.domain.payment;

import com.github.taosoft.bookstore.domain.BaseEntity;
import com.github.taosoft.bookstore.domain.account.Account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 用户钱包
 **/

@Entity
public class Wallet extends BaseEntity {

    // 这里是偷懒，正式项目中请使用BigDecimal来表示金额
    private Double money;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
