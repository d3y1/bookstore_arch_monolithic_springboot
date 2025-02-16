package com.github.taosoft.bookstore.resource;

import com.github.taosoft.bookstore.applicaiton.payment.PaymentApplicationService;
import com.github.taosoft.bookstore.domain.account.Account;
import com.github.taosoft.bookstore.domain.auth.Role;
import com.github.taosoft.bookstore.domain.payment.Payment;
import com.github.taosoft.bookstore.infrastructure.jaxrs.CommonResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 支付单相关的资源
 **/
@Path("/pay")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    private PaymentApplicationService service;

    /**
     * 修改支付单据的状态
     */
    @PATCH
    @Path("/{payId}")
    @RolesAllowed(Role.USER)
    public Response updatePaymentState(@PathParam("payId") String payId, @QueryParam("state") Payment.State state) {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return updatePaymentStateAlias(payId, account.getId(), state);
    }

    /**
     * 修改支付单状态的GET方法别名
     * 考虑到该动作要由二维码扫描来触发，只能进行GET请求，所以增加一个别名以便通过二维码调用
     * 这个方法原本应该作为银行支付接口的回调，不控制调用权限（谁付款都行），但都认为是购买用户付的款
     */
    @GET
    @Path("/modify/{payId}")
    public Response updatePaymentStateAlias(@PathParam("payId") String payId, @QueryParam("accountId") Integer accountId, @QueryParam("state") Payment.State state) {
        if (state == Payment.State.PAYED) {
            return CommonResponse.op(() -> service.accomplishPayment(accountId, payId));
        } else {
            return CommonResponse.op(() -> service.cancelPayment(payId));
        }
    }

}
