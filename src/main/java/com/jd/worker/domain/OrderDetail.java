package com.jd.worker.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 订单详情类
 * 
 * @author changhaichao
 *
 */
public class OrderDetail {
	/** 订单ID*/
	private long orderId;
	
	/** 帐号*/
    private String pin;

	/** 机构ID*/
    private int orgId;

	/** 配送中心ID*/
    private int deliveryId;

	/** 机构ID(原始)*/
    private int orgIdOld;

    /** 配送中心ID(原始) */
    private int deliveryIdOld;

    /** 订单类型 */
    private int orderType;

    /** 商品ID */
	private long skuId;
	
	/** 
	 * pop商家的商品ID
	 * 当是京东自营订单时，和jdSkuId一致
	 */
	private long popSkuId;

	/** 数量 */
	private int num;

	/** 单价 */
	private BigDecimal price;

	/** 库存状态（原始） */
	private int stockStateBefore;

	/** 库存状态(当前) */
	private int stockStateCurr;

    /** 更新时间 */
    private Date updateDate;
    
    /** ofw回传时使用的库房类型, 默认为自营 */
    // private Integer stockType = new Integer(Constants.ORDERDETAIL_PRODUCTTYPE_SELF);
    
    /** 使用库房类型（原始）*/
    private Integer stockTypeBefore;
    
    /** 除自营外可以提供生产的库存类型 */
    private Integer productFlag;
    
    /**是否库存预占   1 表示未占用库存，0 和 2和null 表示占用库存	*/
    private Integer preOccupied;
    
    /**	明细库房占货标记串, 加载进来后转成对象。 */
    private String detailOccupy;
    
	/**
	 * 克隆仓一品占多仓开关， 
	 * true 一品占一仓
	 * false 一品占多仓
	 * 临时在这里用，二期完全打开后可以去掉，到时候优化
	 */
	private boolean canSplitDiffSid = false;
	
	/**
	 * 克隆仓一品占多仓打印日志开关， 
	 * 第一次打印，之后不再打印。每个订单中的每个sku，只会打印一次。
	 */
	private Set<Integer> printLogSet = new HashSet<Integer>();

	/**
	 * 克隆仓二期定位到的仓群对应的库房号
	 */
	private Integer wareGroupStoreId;
	
	/**
	 * sku的预计到货时效,long型
	 */
	private long arriveStockTime;

	public boolean isCanSplitDiffSid() {
		return canSplitDiffSid;
	}

	public void setCanSplitDiffSid(boolean canSplitDiffSid) {
		this.canSplitDiffSid = canSplitDiffSid;
	}
    
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public int getOrgIdOld() {
		return orgIdOld;
	}

	public void setOrgIdOld(int orgIdOld) {
		this.orgIdOld = orgIdOld;
	}

	public int getDeliveryIdOld() {
		return deliveryIdOld;
	}

	public void setDeliveryIdOld(int deliveryIdOld) {
		this.deliveryIdOld = deliveryIdOld;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

//	public Integer getStoreId() {
//		return storeId;
//	}
//
//	public void setStoreId(Integer storeId) {
//		this.storeId = storeId;
//	}

	public long getSkuId() {
		return skuId;
	}

	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}

	/*public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}*/

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStockStateBefore() {
		return stockStateBefore;
	}

	public void setStockStateBefore(int stockStateBefore) {
		this.stockStateBefore = stockStateBefore;
	}

	public int getStockStateCurr() {
		return stockStateCurr;
	}

	public void setStockStateCurr(int stockStateCurr) {
		this.stockStateCurr = stockStateCurr;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getStockTypeBefore() {
		return stockTypeBefore;
	}

	public void setStockTypeBefore(Integer stockTypeBefore) {
		this.stockTypeBefore = stockTypeBefore;
	}

	public Integer getProductFlag() {
		return productFlag;
	}

	public void setProductFlag(Integer productFlag) {
		this.productFlag = productFlag;
	}

	public Integer getPreOccupied() {
		return preOccupied;
	}

	public void setPreOccupied(Integer preOccupied) {
		this.preOccupied = preOccupied;
	}
	
	public String getDetailOccupy() {
		return detailOccupy;
	}

	public void setDetailOccupy(String detailOccupy) {
		this.detailOccupy = detailOccupy;
	}
	
	public long getPopSkuId() {
		return popSkuId;
	}

	public void setPopSkuId(long popSkuId) {
		this.popSkuId = popSkuId;
	}

	public Integer getWareGroupStoreId() {
		return wareGroupStoreId;
	}

	public void setWareGroupStoreId(Integer wareGroupStoreId) {
		this.wareGroupStoreId = wareGroupStoreId;
	}
	
	public long getArriveStockTime() {
		return arriveStockTime;
	}

	public void setArriveStockTime(long arriveStockTime) {
		this.arriveStockTime = arriveStockTime;
	}

}
