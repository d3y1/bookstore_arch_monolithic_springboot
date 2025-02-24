package com.github.taosoft.bookstore.domain.payment;

import com.github.taosoft.bookstore.domain.account.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 用户钱包的领域服务
 * <p>
 * 由于本工程中冻结、解冻款项的方法是为了在微服务中演示TCC事务所准备的，单体服务中由于与本地事务一同提交，无需用到
 **/
@Named
public class WalletService {

    private static final Logger log = LoggerFactory.getLogger(WalletService.class);

    @Inject
    private WalletRepository repository;

    /**
     * 账户资金减少
     */
    public void decrease(Integer accountId, Double amount) {
        Wallet wallet = repository.findByAccountId(accountId).orElseGet(() -> {
            Wallet newWallet = new Wallet();
            Account account = new Account();
            account.setId(accountId);
            newWallet.setMoney(0D);
            newWallet.setAccount(account);
            repository.save(newWallet);
            return newWallet;
        });
        if (wallet.getMoney() > amount) {
            wallet.setMoney(wallet.getMoney() - amount);
            repository.save(wallet);
            log.info("支付成功。用户余额：{}，本次消费：{}", wallet.getMoney(), amount);
        } else {
            throw new RuntimeException("用户余额不足以支付，请先充值");
        }
    }

    /**
     * 账户资金增加（演示程序，没有做充值入口，实际这个方法无用）
     */
    public void increase(Integer accountId, Double amount) {
    }

    // 以下两个方法是为TCC事务准备的，在单体架构中不需要实现

    /**
     * 账户资金冻结
     * 从正常资金中移动指定数量至冻结状态
     */
    public void frozen(Integer accountId, Double amount) {
    }

    /**
     * 账户资金解冻
     * 从冻结资金中移动指定数量至正常状态
     */
    public void thawed(Integer accountId, Double amount) {
    }


}
