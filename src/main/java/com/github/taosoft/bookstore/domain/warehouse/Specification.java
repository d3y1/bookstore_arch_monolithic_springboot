package com.github.taosoft.bookstore.domain.warehouse;

import com.github.taosoft.bookstore.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 商品规格
 **/
@Entity
public class Specification extends BaseEntity {

    @NotEmpty(message = "商品规格名称不允许为空")
    private String item;

    @NotEmpty(message = "商品规格内容不允许为空")
    private String value;

    @NotNull(message = "商品规格必须归属于指定商品")
    @Column(name = "product_id")
    private Integer productId;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
