package com.cmj.example.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author mengjie_chen
 * @description date 2020/12/4
 */
public class DiscountInMerchantVo {
    private String discountId;
    /**
     * 优惠门槛类型（1：满xxx元；2：满xxx件）
     */
    private Integer limitType;
    /**
     * 优惠满减数量（满xxx元或满xxx件）
     */
    private BigDecimal limitQuantity;
    /**
     * 优惠方式（1：立减；2：折扣）
     */
    private Integer type;
    /**
     * 立减数量（立减xxx元或打xxx折）
     */
    private BigDecimal discountQuantity;
    /**
     * 商品列表
     */
    private List<EbuyProductForBuy> ebuyProductForBuyList;

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public Integer getLimitType() {
        return limitType;
    }

    public void setLimitType(Integer limitType) {
        this.limitType = limitType;
    }

    public BigDecimal getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(BigDecimal limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(BigDecimal discountQuantity) {
        this.discountQuantity = discountQuantity;
    }

    public List<EbuyProductForBuy> getEbuyProductForBuyList() {
        return ebuyProductForBuyList;
    }

    public void setEbuyProductForBuyList(List<EbuyProductForBuy> ebuyProductForBuyList) {
        this.ebuyProductForBuyList = ebuyProductForBuyList;
    }


    public static final class DiscountInMerchantVoBuilder {
        private String discountId;
        private Integer limitType;
        private BigDecimal limitQuantity;
        private Integer type;
        private BigDecimal discountQuantity;
        private List<EbuyProductForBuy> ebuyProductForBuyList;

        private DiscountInMerchantVoBuilder() {
        }

        public static DiscountInMerchantVoBuilder discountInMerchantVo() {
            return new DiscountInMerchantVoBuilder();
        }

        public DiscountInMerchantVoBuilder discountId(String discountId) {
            this.discountId = discountId;
            return this;
        }

        public DiscountInMerchantVoBuilder limitType(Integer limitType) {
            this.limitType = limitType;
            return this;
        }

        public DiscountInMerchantVoBuilder limitQuantity(BigDecimal limitQuantity) {
            this.limitQuantity = limitQuantity;
            return this;
        }

        public DiscountInMerchantVoBuilder type(Integer type) {
            this.type = type;
            return this;
        }

        public DiscountInMerchantVoBuilder discountQuantity(BigDecimal discountQuantity) {
            this.discountQuantity = discountQuantity;
            return this;
        }

        public DiscountInMerchantVoBuilder ebuyProductForBuyList(List<EbuyProductForBuy> ebuyProductForBuyList) {
            this.ebuyProductForBuyList = ebuyProductForBuyList;
            return this;
        }

        public DiscountInMerchantVo build() {
            DiscountInMerchantVo discountInMerchantVo = new DiscountInMerchantVo();
            discountInMerchantVo.setDiscountId(discountId);
            discountInMerchantVo.setLimitType(limitType);
            discountInMerchantVo.setLimitQuantity(limitQuantity);
            discountInMerchantVo.setType(type);
            discountInMerchantVo.setDiscountQuantity(discountQuantity);
            discountInMerchantVo.setEbuyProductForBuyList(ebuyProductForBuyList);
            return discountInMerchantVo;
        }
    }
}
