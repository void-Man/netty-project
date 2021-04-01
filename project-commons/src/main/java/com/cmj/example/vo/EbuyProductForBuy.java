package com.cmj.example.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class EbuyProductForBuy implements Serializable {
    private static final long serialVersionUID = -4951650261424540861L;
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 商品货架ID
     */
    private String ebuyProductShelfId;
    /**
     * 购物车ID
     */
    private String ebuyBuyCartId;
    /**
     * 促销ID
     */
    private String promotionId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品推广名称
     */
    private String productMiniName;
    /**
     * 商品原价价格
     */
    private BigDecimal price;
    /**
     * 商品促销价
     */
    private BigDecimal promotionPrice;
    /**
     * 购买原价商品数量
     */
    private Integer buyQty;
    /**
     * 购买促销商品数量
     */
    private Integer activityBuyQty;
    /**
     * 促销限购数量
     */
    private Integer maxCanBuy;
    /**
     * 商品图片
     */
    private String picBaseMini;
    /**
     * 是否包含活动商品
     */
    private Integer isHasAcitvityProduct;
    /**
     * 是否还有库存（1有，0没有）
     */
    private Integer isHasLeftCount;
    /**
     * 是否售罄：1 是， 0否
     */
    private Integer isSellOut;
    /**
     * 下架状态
     */
    private Integer shelfState;
    /**
     * 促销库存
     */
    private Integer activityLeftcount;
    /**
     * 用户购买的促销商品数量
     */
    private Integer activityBuyCount;
    /**
     * 商品参与满减数量
     */
    private Integer discountQuantity;
    /**
     * 商品参数
     */
    private List<EbuyProductParametersVo> ebuyProductParametersVoList;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getEbuyProductShelfId() {
        return ebuyProductShelfId;
    }

    public void setEbuyProductShelfId(String ebuyProductShelfId) {
        this.ebuyProductShelfId = ebuyProductShelfId;
    }

    public String getEbuyBuyCartId() {
        return ebuyBuyCartId;
    }

    public void setEbuyBuyCartId(String ebuyBuyCartId) {
        this.ebuyBuyCartId = ebuyBuyCartId;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductMiniName() {
        return productMiniName;
    }

    public void setProductMiniName(String productMiniName) {
        this.productMiniName = productMiniName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getBuyQty() {
        return buyQty;
    }

    public void setBuyQty(Integer buyQty) {
        this.buyQty = buyQty;
    }

    public Integer getActivityBuyQty() {
        return activityBuyQty;
    }

    public void setActivityBuyQty(Integer activityBuyQty) {
        this.activityBuyQty = activityBuyQty;
    }

    public Integer getMaxCanBuy() {
        return maxCanBuy;
    }

    public void setMaxCanBuy(Integer maxCanBuy) {
        this.maxCanBuy = maxCanBuy;
    }

    public String getPicBaseMini() {
        return picBaseMini;
    }

    public void setPicBaseMini(String picBaseMini) {
        this.picBaseMini = picBaseMini;
    }

    public Integer getIsHasAcitvityProduct() {
        return isHasAcitvityProduct;
    }

    public void setIsHasAcitvityProduct(Integer isHasAcitvityProduct) {
        this.isHasAcitvityProduct = isHasAcitvityProduct;
    }

    public Integer getIsHasLeftCount() {
        return isHasLeftCount;
    }

    public void setIsHasLeftCount(Integer isHasLeftCount) {
        this.isHasLeftCount = isHasLeftCount;
    }

    public Integer getIsSellOut() {
        return isSellOut;
    }

    public void setIsSellOut(Integer isSellOut) {
        this.isSellOut = isSellOut;
    }

    public Integer getShelfState() {
        return shelfState;
    }

    public void setShelfState(Integer shelfState) {
        this.shelfState = shelfState;
    }

    public Integer getActivityLeftcount() {
        return activityLeftcount;
    }

    public void setActivityLeftcount(Integer activityLeftcount) {
        this.activityLeftcount = activityLeftcount;
    }

    public Integer getActivityBuyCount() {
        return activityBuyCount;
    }

    public void setActivityBuyCount(Integer activityBuyCount) {
        this.activityBuyCount = activityBuyCount;
    }

    public Integer getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(Integer discountQuantity) {
        this.discountQuantity = discountQuantity;
    }

    public List<EbuyProductParametersVo> getEbuyProductParametersVoList() {
        return ebuyProductParametersVoList;
    }

    public void setEbuyProductParametersVoList(List<EbuyProductParametersVo> ebuyProductParametersVoList) {
        this.ebuyProductParametersVoList = ebuyProductParametersVoList;
    }


    public static final class EbuyProductForBuyBuilder {
        private String productId;
        private String ebuyProductShelfId;
        private String ebuyBuyCartId;
        private String promotionId;
        private String productName;
        private String productMiniName;
        private BigDecimal price;
        private BigDecimal promotionPrice;
        private Integer buyQty;
        private Integer activityBuyQty;
        private Integer maxCanBuy;
        private String picBaseMini;
        private Integer isHasAcitvityProduct;
        private Integer isHasLeftCount;
        private Integer isSellOut;
        private Integer shelfState;
        private Integer activityLeftcount;
        private Integer activityBuyCount;
        private Integer discountQuantity;
        private List<EbuyProductParametersVo> ebuyProductParametersVoList;

        private EbuyProductForBuyBuilder() {
        }

        public static EbuyProductForBuyBuilder ebuyProductForBuy() {
            return new EbuyProductForBuyBuilder();
        }

        public EbuyProductForBuyBuilder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public EbuyProductForBuyBuilder ebuyProductShelfId(String ebuyProductShelfId) {
            this.ebuyProductShelfId = ebuyProductShelfId;
            return this;
        }

        public EbuyProductForBuyBuilder ebuyBuyCartId(String ebuyBuyCartId) {
            this.ebuyBuyCartId = ebuyBuyCartId;
            return this;
        }

        public EbuyProductForBuyBuilder promotionId(String promotionId) {
            this.promotionId = promotionId;
            return this;
        }

        public EbuyProductForBuyBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public EbuyProductForBuyBuilder productMiniName(String productMiniName) {
            this.productMiniName = productMiniName;
            return this;
        }

        public EbuyProductForBuyBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public EbuyProductForBuyBuilder promotionPrice(BigDecimal promotionPrice) {
            this.promotionPrice = promotionPrice;
            return this;
        }

        public EbuyProductForBuyBuilder buyQty(Integer buyQty) {
            this.buyQty = buyQty;
            return this;
        }

        public EbuyProductForBuyBuilder activityBuyQty(Integer activityBuyQty) {
            this.activityBuyQty = activityBuyQty;
            return this;
        }

        public EbuyProductForBuyBuilder maxCanBuy(Integer maxCanBuy) {
            this.maxCanBuy = maxCanBuy;
            return this;
        }

        public EbuyProductForBuyBuilder picBaseMini(String picBaseMini) {
            this.picBaseMini = picBaseMini;
            return this;
        }

        public EbuyProductForBuyBuilder isHasAcitvityProduct(Integer isHasAcitvityProduct) {
            this.isHasAcitvityProduct = isHasAcitvityProduct;
            return this;
        }

        public EbuyProductForBuyBuilder isHasLeftCount(Integer isHasLeftCount) {
            this.isHasLeftCount = isHasLeftCount;
            return this;
        }

        public EbuyProductForBuyBuilder isSellOut(Integer isSellOut) {
            this.isSellOut = isSellOut;
            return this;
        }

        public EbuyProductForBuyBuilder shelfState(Integer shelfState) {
            this.shelfState = shelfState;
            return this;
        }

        public EbuyProductForBuyBuilder activityLeftcount(Integer activityLeftcount) {
            this.activityLeftcount = activityLeftcount;
            return this;
        }

        public EbuyProductForBuyBuilder activityBuyCount(Integer activityBuyCount) {
            this.activityBuyCount = activityBuyCount;
            return this;
        }

        public EbuyProductForBuyBuilder discountQuantity(Integer discountQuantity) {
            this.discountQuantity = discountQuantity;
            return this;
        }

        public EbuyProductForBuyBuilder ebuyProductParametersVoList(List<EbuyProductParametersVo> ebuyProductParametersVoList) {
            this.ebuyProductParametersVoList = ebuyProductParametersVoList;
            return this;
        }

        public EbuyProductForBuy build() {
            EbuyProductForBuy ebuyProductForBuy = new EbuyProductForBuy();
            ebuyProductForBuy.setProductId(productId);
            ebuyProductForBuy.setEbuyProductShelfId(ebuyProductShelfId);
            ebuyProductForBuy.setEbuyBuyCartId(ebuyBuyCartId);
            ebuyProductForBuy.setPromotionId(promotionId);
            ebuyProductForBuy.setProductName(productName);
            ebuyProductForBuy.setProductMiniName(productMiniName);
            ebuyProductForBuy.setPrice(price);
            ebuyProductForBuy.setPromotionPrice(promotionPrice);
            ebuyProductForBuy.setBuyQty(buyQty);
            ebuyProductForBuy.setActivityBuyQty(activityBuyQty);
            ebuyProductForBuy.setMaxCanBuy(maxCanBuy);
            ebuyProductForBuy.setPicBaseMini(picBaseMini);
            ebuyProductForBuy.setIsHasAcitvityProduct(isHasAcitvityProduct);
            ebuyProductForBuy.setIsHasLeftCount(isHasLeftCount);
            ebuyProductForBuy.setIsSellOut(isSellOut);
            ebuyProductForBuy.setShelfState(shelfState);
            ebuyProductForBuy.setActivityLeftcount(activityLeftcount);
            ebuyProductForBuy.setActivityBuyCount(activityBuyCount);
            ebuyProductForBuy.setDiscountQuantity(discountQuantity);
            ebuyProductForBuy.setEbuyProductParametersVoList(ebuyProductParametersVoList);
            return ebuyProductForBuy;
        }
    }
}
