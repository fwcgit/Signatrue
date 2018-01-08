package com.yuhui.sign.http;

public class CallBackInfo {
	
	/***
	 * 签约编号
	 */
	public String applyNo;
	
	/***
	 * 证件类型
	 */
	public String identityType;
	
	/***
	 * 企业名称/个 人姓名
	 */
	public String fullName;
	
	/***
	 * 证件号码
	 */
	public String identityCard;
	
	/***
	 * 操作时间(毫 秒)
	 */
	public long   optTime;
	
	/***
	 * 签约状态 1 已签、2 拒签、3 保全
	 */
	public int    signStatus;
	
	/***
	 * 回传信息发送 时间(毫秒)
	 */
	public long	  timestamp;
	
	/***
	 * 签名加密串 (sha1 加密)
	 */
	public String sign;
	
}
