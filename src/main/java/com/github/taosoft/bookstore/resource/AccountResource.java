package com.github.taosoft.bookstore.resource;

import com.github.taosoft.bookstore.applicaiton.AccountApplicationService;
import com.github.taosoft.bookstore.domain.account.Account;
import com.github.taosoft.bookstore.domain.account.validation.AuthenticatedAccount;
import com.github.taosoft.bookstore.domain.account.validation.NotConflictAccount;
import com.github.taosoft.bookstore.domain.account.validation.UniqueAccount;
import com.github.taosoft.bookstore.infrastructure.jaxrs.CommonResponse;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 用户资源
 * <p>
 * 对客户端以Restful形式暴露资源，提供对用户资源{@link Account}的管理入口
 **/
@Path("/accounts")
@Component
@CacheConfig(cacheNames = "resource.account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    private AccountApplicationService service;

    /**
     * 根据用户名称获取用户详情
     */
    @GET
    @Path("/{username}")
    @Cacheable(key = "#username")
    public Account getUser(@PathParam("username") String username) {
        return service.findAccountByUsername(username);
    }

    /**
     * 创建新的用户
     */
    @POST
    @CacheEvict(key = "#user.username")
    public Response createUser(@Valid @UniqueAccount Account user) {
        return CommonResponse.op(() -> service.createAccount(user));
    }

    /**
     * 更新用户信息
     */
    @PUT
    @CacheEvict(key = "#user.username")
    public Response updateUser(@Valid @AuthenticatedAccount @NotConflictAccount Account user) {
        return CommonResponse.op(() -> service.updateAccount(user));
    }
}
