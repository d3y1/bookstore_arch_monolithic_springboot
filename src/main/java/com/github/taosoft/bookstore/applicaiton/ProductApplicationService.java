package com.github.taosoft.bookstore.applicaiton;

import com.github.taosoft.bookstore.domain.payment.Stockpile;
import com.github.taosoft.bookstore.domain.payment.StockpileService;
import com.github.taosoft.bookstore.domain.warehouse.Product;
import com.github.taosoft.bookstore.domain.warehouse.ProductService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * 产品的应用服务接口
 **/
@Named
@Transactional
public class ProductApplicationService {

    @Inject
    private ProductService service;

    @Inject
    private StockpileService stockpileService;

    /**
     * 获取仓库中所有的货物信息
     */
    public Iterable<Product> getAllProducts() {
        return service.getAllProducts();
    }

    /**
     * 获取仓库中指定的货物信息
     */
    public Product getProduct(Integer id) {
        return service.getProduct(id);
    }

    /**
     * 创建或更新产品信息
     */
    public Product saveProduct(Product product) {
        return service.saveProduct(product);
    }

    /**
     * 删除指定产品
     */
    public void removeProduct(Integer id) {
        service.removeProduct(id);
    }


    /**
     * 根据产品查询库存
     */
    public Stockpile getStockpile(Integer productId) {
        return stockpileService.getByProductId(productId);
    }

    /**
     * 将指定的产品库存调整为指定数额
     */
    public void setStockpileAmountByProductId(Integer productId, Integer amount) {
        stockpileService.set(productId, amount);
    }
}
