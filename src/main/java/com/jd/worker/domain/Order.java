package com.jd.worker.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Order{
    /** 订单ID */
    private long orderId;
    
    /** 原始父单号 */
    private long parentId;
    
	/** 帐号 */
    private String pin;
    
    /** 机构ID */
    private int orgId;
    
    /** 配送中心ID */
    private int deliveryId;
    
    /** 机构ID(原始) */
    private int orgIdOld;
    
    /** 配送中心ID(原始) */
    private int deliveryIdOld;
    
    /** 订单类型 */
    private int orderType;
    
    /** 库房ID */
    private Integer storeId;
    
    /** old 库房ID */
    private Integer storeIdOld;
    
	/** 拆分状态 */
    private int splitType;
    
    /** 提货时间 */
    private Date codDate;
    
    /** 省份ID */
    private int idProvince;
    
    /** 市ID */
    private int idCity;
    
    /** 区id */
    private int idArea;
    
    /** 配送地-县  原名称：townId */
	private Integer townId;
	
	/** 详细地址 */
	private String addr;
    
    /** sendPay */
    private String tags;
    
    /** 付款确认时间 */
    private Date paysureDate;
    
    /** 订单状态 */
    private Integer state;
    private Integer state2;
    private Integer printx;
    private Integer ziti;
    
    
    /** 下单时间 */
    private Date createDate;
    
    /** 更新时间 */
    private Date updateDate;
    
    /** 更新者 */
    private String operator;
    
    /** 订单版本 */
    private long orderVersion;
    
    /** 内部版本 */
    private long innerVersion;
    
    /** 订单转移时间 */
    private Date transferDate;
    
    /** 平行库存标志 */
	private String parallelWareStockFlag;
	
	/** 是否库存预占   1 表示未占用库存，0 和 2和null 表示占用库存 */
	private Integer preOccupiedFlag;
	
	/** 消息防重码 */
	private String mesAntiKey;
	
	/** 客户地址纬度信息 */
	private String addrLng;
	
	/** 客户地址经度信息 */
	private String addrLat;

	/**
     * 增加一hashmap，记录skuid：坏仓对应的替代的配送中心号的set
     * Map<skuId, Set<dcIds>>
     */
	private HashMap<Long, LinkedHashSet<Integer>> sku2ReplaceDeliveryMap = new HashMap<Long, LinkedHashSet<Integer>>();
	
    /**
     * 增加一hashmap，记录 坏仓对应的替代的配送中心号:skuid的串
     * Map<dcId, set<skuIds> >
     */
	private HashMap<Integer, Set<Long>> replaceDelivery2SkusMap = new HashMap<Integer, Set<Long>>();
	
    /**
     * 记录当前直接现货商品的skuid数量
     */	
	private int skusDirectionInStock;
	
	/** 
	 * 坏仓替代订单
	 * 全部坏仓&&坏仓对应的好仓位于同一配送中心 
	 * 标记为true
	 */
	private boolean isBadStore;
	
	/** 
	 * 坏仓对应配送中心ID
	 * 订单命中坏仓的配送中心id
	 */
	private Integer originalBadStoreDcId;
	
	/**
	 * 订单是否含有图书采销预售的商品
	 */
	private boolean isPreSaleForBookFlag;

	/** 下单时间 */
    private Date orderCreateDate;
    
    /** 订单排序号 */
    private long sortNum;
    
    /**
     * 最后从promise获取转移时间的时间 
     * 用来和最后清空转移时间的时间做比较.
     */
    private Date lastGetTransferDate;
    
    /**
     * J仓现货/在途/EDI足的sku数量,不是订单中sku的需货量.
     * 例:订单中有ABC三个商品,A和B在J仓是现货/在途/EDI足,inStockSkuNum4J就是2.
     */
    private int inStockSkuNum4J;
    
    /**
     *  截单点时间
     */
    private Date endOrderDate;
    
    /** 支付类型 */
	private int paymentType;
	
	/** 配送方式类型 */
	private int shipmentType;
    
    /**
     * 装J仓现货的skuId的set
     */
    private Set<Long> inStockSku4JSet = new HashSet<Long>();
    
	
	/** 主动停标志 */
	private boolean isActiveStop;
	
	 /** 内部 扩展标示 */
	private String code;
	
	/** 下次调用promise接口时间  */
    private Date fastestTransferDate;
    
    /** 送货日期类型, @see枚举 DeliveryDateType */
    private int deliveryDateType;
    
    /** 商品详情列表 */
    private List<OrderDetail> orderDetails;
    
    /** 本地配送中心id, 库房列表 <dcId, Set<storeId>> */
	private Map<Integer, Set<Integer>> localDeliveryIdStoreIdsMap = new LinkedHashMap<Integer, Set<Integer>>();
	
	/** 平行库存配送中心id, 库房列表 <dcId, Set<storeId>> */
	private Map<Integer, Set<Integer>> parallelDeliveryIdStoreIdsMap = new LinkedHashMap<Integer, Set<Integer>>();
	
	/** 坏仓替代配送中心id, 库房列表 <dcId, Set<storeId>> */
	private Map<Integer, Set<Integer>> badDeliveryIdStoreIdsMap = new LinkedHashMap<Integer, Set<Integer>>();
	
	/** 全量配送中心id, 库房列表 <dcId, Set<storeId>> */
	private Map<Integer, Set<Integer>> allDeliveryIdStoreIdsMap = new LinkedHashMap<Integer, Set<Integer>>();
	
	/**
	 *  有效的配送中心列表（至少有一个sku能有现货的配送中心列表）
	 *  Set<deliveryId>
	 */
	private Set<Integer> effectiveDcIds = new LinkedHashSet<Integer>();
	
	/**
	 * 上轮redis给出的满足同一DC，同一store现货且妥投时间最短且相同的配送中心库房列表
	 */
	private String onTimeDcStore;

	/**
	 * 标记该订单本轮是否因斗转星移逻辑修改了DC
	 */
	private boolean isChangeDCByStarChange = false;
	
	/** 站点信息 */
	private Integer siteId;

	/**
	 * 整单的预计到货时间
	 */
	private long arriveStockTime;

	/** 订单主动停的仓群编号 */
	private String wareGroupId;
	
	/** 仓群下定位生产的库房及SKU列表 HashMap<dc_storeId, Set<skuIds>>*/
	private HashMap<String, Set<Long>> wareGroupProductStoreMap;

	/**
     * 用当前订单暂停的配送中心和库房号字符串
     * dcId_storeId
     * @return String
     */
	@Deprecated
    public String fetchDeliveryCenterStoreStockCurrKey() {
        return this.deliveryId + "_" + this.storeId;
    }
    
    /**
     * 按照配送中心id，获取此配送中心下的库房列表
     * @param dcId
     * @return
     */
    public Set<Integer> getStoreIdSetByDcId(int dcId) {
    	Set<Integer> storeIdSet = null;
    	if (localDeliveryIdStoreIdsMap.containsKey(dcId)) {
    		storeIdSet = localDeliveryIdStoreIdsMap.get(dcId);
    	} else if (parallelDeliveryIdStoreIdsMap.containsKey(dcId)) {
    		storeIdSet = parallelDeliveryIdStoreIdsMap.get(dcId);
    	} else if (badDeliveryIdStoreIdsMap.containsKey(dcId)) {
    		storeIdSet = badDeliveryIdStoreIdsMap.get(dcId);
    	} else { // 理论不会进来！
    		storeIdSet = new HashSet<Integer>();
    	}
    	
    	return storeIdSet;
    }
    
    /**
     * 获取全部的可生产的配送中心列表
     * @param dcId
     * @return
     */
    public Map<Integer, Set<Integer>> getAllDeliveryIdMap() {
    	if (allDeliveryIdStoreIdsMap.isEmpty()) {
    		allDeliveryIdStoreIdsMap.putAll(localDeliveryIdStoreIdsMap);
    		allDeliveryIdStoreIdsMap.putAll(parallelDeliveryIdStoreIdsMap);
    		for (Map.Entry<Integer, Set<Integer>> entry : badDeliveryIdStoreIdsMap.entrySet()) {
    			if (!allDeliveryIdStoreIdsMap.containsKey(entry)) { // 不存在时才往里加入。存在就不在加了。
    				allDeliveryIdStoreIdsMap.put(entry.getKey(), entry.getValue());
    			}
    		}
    	}
    	
    	return allDeliveryIdStoreIdsMap;
    }
    
    /**
     * 取得订单中的SKU总数
     * 
     * @method: Order getSKUQuantity
     * @return int SKU总数
     * @create date： 2013-3-20
     * @2013, by aiwei.
     */
    public int fetchTotalSkuQuantity() {
        int TotalSkuQuantity = 0;
        if (orderDetails != null) {
            TotalSkuQuantity = orderDetails.size();
        }
        return TotalSkuQuantity;
    }
    
    public long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
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
    
    public Integer getStoreId() {
        return storeId;
    }
    
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    
    public Integer getStoreIdOld() {
		return storeIdOld;
	}

	public void setStoreIdOld(Integer storeIdOld) {
		this.storeIdOld = storeIdOld;
	}
    
    public int getSplitType() {
        return splitType;
    }
    
    public void setSplitType(int splitType) {
        this.splitType = splitType;
    }
    
    public Date getCodDate() {
        return codDate;
    }
    
    public void setCodDate(Date codDate) {
        this.codDate = codDate;
    }
    
    public int getIdProvince() {
        return idProvince;
    }
    
    public void setIdProvince(int idProvince) {
        this.idProvince = idProvince;
    }
    
    public int getIdCity() {
        return idCity;
    }
    
    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }
    
    public int getIdArea() {
        return idArea;
    }
    
    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
    
    public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTags() {
        return tags;
    }
    
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public Date getPaysureDate() {
        return paysureDate;
    }
    
    public void setPaysureDate(Date paysureDate) {
        this.paysureDate = paysureDate;
    }
    
    public Integer getState() {
        return state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
    
    public Integer getState2() {
        return state2;
    }
    
    public void setState2(Integer state2) {
        this.state2 = state2;
    }
    
    public Integer getPrintx() {
        return printx;
    }
    
    public void setPrintx(Integer printx) {
        this.printx = printx;
    }
    
    public Integer getZiti() {
        return ziti;
    }
    
    public void setZiti(Integer ziti) {
        this.ziti = ziti;
    }
    
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }
    
    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
   
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Date getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public String getOperator() {
        return operator;
    }
    
    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    public long getOrderVersion() {
        return orderVersion;
    }
    
    public void setOrderVersion(long orderVersion) {
        this.orderVersion = orderVersion;
    }
    
    public long getInnerVersion() {
        return innerVersion;
    }
    
    public void setInnerVersion(long innerVersion) {
        this.innerVersion = innerVersion;
    }

	public Map<Integer, Set<Integer>> getLocalDeliveryIdStoreIdsMap() {
		return localDeliveryIdStoreIdsMap;
	}

	public void setLocalDeliveryIdStoreIdsMap(Map<Integer, Set<Integer>> localDeliveryIdStoreIdsMap) {
		this.localDeliveryIdStoreIdsMap = localDeliveryIdStoreIdsMap;
	}

	public HashMap<Long, LinkedHashSet<Integer>> getSku2ReplaceDeliveryMap() {
		return sku2ReplaceDeliveryMap;
	}

	public void setSku2ReplaceDeliveryMap(HashMap<Long, LinkedHashSet<Integer>> skuMap) {
		this.sku2ReplaceDeliveryMap = skuMap;
	}

	/**
	 * 记录skuid：坏仓对应的替代的配送中心号的set
	 * @param skuId
	 * @param deliveryIds
	 */
	public void putSku2ReplaceDeliveryMap(Long skuId,LinkedHashSet<Integer> deliveryIds){
		this.sku2ReplaceDeliveryMap.put(skuId, deliveryIds);
	}
	
	public boolean isBadStore() {
		return isBadStore;
	}

	public void setBadStore(boolean isBadStore) {
		this.isBadStore = isBadStore;
	}

	public HashMap<Integer, Set<Long>> getReplaceDelivery2SkusMap() {
		return replaceDelivery2SkusMap;
	}

	public void setReplaceDelivery2SkusMap(HashMap<Integer, Set<Long>> deliveryMap) {
		this.replaceDelivery2SkusMap = deliveryMap;
	}

	/**
	 * 记录 坏仓对应的替代的配送中心号:skuid的串
	 * @param deliveryId
	 * @param skus
	 */
	public void putReplaceDelivery2SkusMap(Integer deliveryId, Set<Long> skus){
		this.replaceDelivery2SkusMap.put(deliveryId, skus);
	}

	public int getSkusDirectionInStock() {
		return skusDirectionInStock;
	}

	public void setSkusDirectionInStock(int skusDirectionInStock) {
		this.skusDirectionInStock = skusDirectionInStock;
	}
	
	public int getInStockSkuNum4J() {
		return inStockSkuNum4J;
	}

	public void setInStockSkuNum4J(int inStockSkuNum4J) {
		this.inStockSkuNum4J = inStockSkuNum4J;
	}
	
	public long getSortNum() {
        return sortNum;
    }

    public void setSortNum(long sortNum) {
        this.sortNum = sortNum;
    }
    
    public boolean isPreSaleForBookFlag() {
		return isPreSaleForBookFlag;
	}

	public void setPreSaleForBookFlag(boolean isPreSaleForBookFlag) {
		this.isPreSaleForBookFlag = isPreSaleForBookFlag;
	}
    
    public Date getLastGetTransferDate() {
		return lastGetTransferDate;
	}

	public void setLastGetTransferDate(Date lastGetTransferDate) {
		this.lastGetTransferDate = lastGetTransferDate;
	}
	
	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public int getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(int shipmentType) {
		this.shipmentType = shipmentType;
	}

	public Date getEndOrderDate() {
		return endOrderDate;
	}

	public void setEndOrderDate(Date endOrderDate) {
		this.endOrderDate = endOrderDate;
	}
	
	public Integer getPreOccupiedFlag() {
		return preOccupiedFlag;
	}

	public void setPreOccupiedFlag(Integer preOccupiedFlag) {
		this.preOccupiedFlag = preOccupiedFlag;
	}
	
	public Integer getOriginalBadStoreDcId() {
		return originalBadStoreDcId;
	}

	public void setOriginalBadStoreDcId(Integer originalBadStoreDcId) {
		this.originalBadStoreDcId = originalBadStoreDcId;
	}

	public Set<Long> getInStockSku4JSet() {
		return inStockSku4JSet;
	}

	public void setInStockSku4JSet(Set<Long> inStockSku4JSet) {
		this.inStockSku4JSet = inStockSku4JSet;
	}
	public Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public String getParallelWareStockFlag() {
		return parallelWareStockFlag;
	}

	public void setParallelWareStockFlag(String parallelWareStockFlag) {
		this.parallelWareStockFlag = parallelWareStockFlag;
	}
	
	public String getMesAntiKey() {
		return mesAntiKey;
	}

	public void setMesAntiKey(String mesAntiKey) {
		this.mesAntiKey = mesAntiKey;
	}

	public boolean isActiveStop() {
        return isActiveStop;
    }

    public void setActiveStop(boolean isActiveStop) {
        this.isActiveStop = isActiveStop;
    }
    
    public Date getFastestTransferDate() {
        return fastestTransferDate;
    }

    public void setFastestTransferDate(Date fastestTransferDate) {
        this.fastestTransferDate = fastestTransferDate;
    }

    public int getDeliveryDateType() {
        return deliveryDateType;
    }

    public void setDeliveryDateType(int deliveryDateType) {
        this.deliveryDateType = deliveryDateType;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	
	public Set<Integer> getEffectiveDcIds() {
		return effectiveDcIds;
	}
	
	public Map<Integer, Set<Integer>> getParallelDeliveryIdStoreIdsMap() {
		return parallelDeliveryIdStoreIdsMap;
	}

	public void setParallelDeliveryIdStoreIdsMap(Map<Integer, Set<Integer>> parallelDeliveryIdStoreIdsMap) {
		this.parallelDeliveryIdStoreIdsMap = parallelDeliveryIdStoreIdsMap;
	}
	
	public Map<Integer, Set<Integer>> getBadDeliveryIdStoreIdsMap() {
		return badDeliveryIdStoreIdsMap;
	}

	public void setBadDeliveryIdStoreIdsMap(Map<Integer, Set<Integer>> badDeliveryIdStoreIdsMap) {
		this.badDeliveryIdStoreIdsMap = badDeliveryIdStoreIdsMap;
	}
    
    public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
	public String getWareGroupId() {
		return wareGroupId;
	}

	public void setWareGroupId(String wareGroupId) {
		this.wareGroupId = wareGroupId;
	}

	public HashMap<String, Set<Long>> getWareGroupProductStoreMap() {
		return wareGroupProductStoreMap;
	}

	public void setWareGroupProductStoreMap(
			HashMap<String, Set<Long>> wareGroupProductStoreMap) {
		this.wareGroupProductStoreMap = wareGroupProductStoreMap;
	}

	public String getAddrLng() {
		return addrLng;
	}

	public void setAddrLng(String addrLng) {
		this.addrLng = addrLng;
	}

	public String getAddrLat() {
		return addrLat;
	}

	public void setAddrLat(String addrLat) {
		this.addrLat = addrLat;
	}
	
	public String getOnTimeDcStore() {
		return onTimeDcStore;
	}

	public void setOnTimeDcStore(String onTimeDcStore) {
		this.onTimeDcStore = onTimeDcStore;
	}
	
	
	public boolean isChangeDCByStarChange() {
		return isChangeDCByStarChange;
	}

	public void setChangeDCByStarChange(boolean isChangeDCByStarChange) {
		this.isChangeDCByStarChange = isChangeDCByStarChange;
	}
	
	public long getArriveStockTime() {
		return arriveStockTime;
	}
	
}
