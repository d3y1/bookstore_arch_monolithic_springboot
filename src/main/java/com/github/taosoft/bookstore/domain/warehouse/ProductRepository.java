package com.github.taosoft.bookstore.domain.warehouse;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * 商品对象数据仓库
 **/
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Collection<Product> findByIdIn(Collection<Integer> ids);

}
