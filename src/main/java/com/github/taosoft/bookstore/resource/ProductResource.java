package com.github.taosoft.bookstore.resource;

import com.github.taosoft.bookstore.applicaiton.ProductApplicationService;
import com.github.taosoft.bookstore.domain.auth.Role;
import com.github.taosoft.bookstore.domain.payment.Stockpile;
import com.github.taosoft.bookstore.domain.warehouse.Product;
import com.github.taosoft.bookstore.infrastructure.jaxrs.CommonResponse;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 产品相关的资源
 **/

@Path("/products")
@Component
@CacheConfig(cacheNames = "resource.product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductApplicationService service;

    /**
     * 获取仓库中所有的货物信息
     */
    @GET
    @Cacheable(key = "'ALL_PRODUCT'")
    public Iterable<Product> getAllProducts() {
        return service.getAllProducts();
    }

    /**
     * 获取仓库中指定的货物信息
     */
    @GET
    @Path("/{id}")
    @Cacheable(key = "#id")
    public Product getProduct(@PathParam("id") Integer id) {
        return service.getProduct(id);
    }

    /**
     * 更新产品信息
     */
    @PUT
    @Caching(evict = {
            @CacheEvict(key = "#product.id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    @RolesAllowed(Role.ADMIN)
    public Response updateProduct(@Valid Product product) {
        return CommonResponse.op(() -> service.saveProduct(product));
    }

    /**
     * 创建新的产品
     */
    @POST
    @Caching(evict = {
            @CacheEvict(key = "#product.id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    @RolesAllowed(Role.ADMIN)
    public Product createProduct(@Valid Product product) {
        return service.saveProduct(product);
    }

    /**
     * 删除新的产品
     */
    @DELETE
    @Path("/{id}")
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    @RolesAllowed(Role.ADMIN)
    public Response removeProduct(@PathParam("id") Integer id) {
        return CommonResponse.op(() -> service.removeProduct(id));
    }

    /**
     * 将指定的产品库存调整为指定数额
     */
    @PATCH
    @Path("/stockpile/{productId}")
    @RolesAllowed(Role.ADMIN)
    public Response updateStockpile(@PathParam("productId") Integer productId, @QueryParam("amount") Integer amount) {
        return CommonResponse.op(() -> service.setStockpileAmountByProductId(productId, amount));
    }

    /**
     * 根据产品查询库存
     */
    @GET
    @Path("/stockpile/{productId}")
    @RolesAllowed(Role.ADMIN)
    public Stockpile queryStockpile(@PathParam("productId") Integer productId) {
        return service.getStockpile(productId);
    }

}
