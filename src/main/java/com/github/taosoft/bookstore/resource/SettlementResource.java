package com.github.taosoft.bookstore.resource;

import com.github.taosoft.bookstore.applicaiton.payment.PaymentApplicationService;
import com.github.taosoft.bookstore.applicaiton.payment.dto.Settlement;
import com.github.taosoft.bookstore.domain.auth.Role;
import com.github.taosoft.bookstore.domain.payment.Payment;
import com.github.taosoft.bookstore.domain.payment.validation.SufficientStock;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 结算清单相关的资源
 **/
@Path("/settlements")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SettlementResource {

    @Inject
    private PaymentApplicationService service;

    /**
     * 提交一张交易结算单，根据结算单中的物品，生成支付单
     */
    @POST
    @RolesAllowed(Role.USER)
    public Payment executeSettlement(@Valid @SufficientStock Settlement settlement) {
        return service.executeBySettlement(settlement);
    }

}
