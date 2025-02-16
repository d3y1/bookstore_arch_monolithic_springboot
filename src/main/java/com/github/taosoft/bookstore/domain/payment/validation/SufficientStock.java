package com.github.taosoft.bookstore.domain.payment.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 判断结算单中货物存量是充足的
 **/
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
@Constraint(validatedBy = SettlementValidator.class)
public @interface SufficientStock {
    String message() default "商品库存不足";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
