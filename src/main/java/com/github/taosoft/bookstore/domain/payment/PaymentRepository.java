package com.github.taosoft.bookstore.domain.payment;

import org.springframework.data.repository.CrudRepository;

/**
 * 支付单数据仓库
 **/
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

    Payment getByPayId(String payId);

}
