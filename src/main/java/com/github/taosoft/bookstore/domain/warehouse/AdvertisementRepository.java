package com.github.taosoft.bookstore.domain.warehouse;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

/**
 * 广告对象数据仓库
 **/
public interface AdvertisementRepository extends CrudRepository<Advertisement, Integer> {
    Iterable<Advertisement> findAll() throws DataAccessException;
}
