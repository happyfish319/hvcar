package com.thinkgem.jeesite.modules.huvis.entity;


import java.net.URL;




import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class HvCarLocationDtl extends DataEntity<HvCarLocationDtl>{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -638367150994692751L;
	private String orderMstId;
	private String carNo;
	private String locationTime;
	private String x;
	private String y;
	private String realWarnYn;
	private String realWarnOption;
	private String realWarnOptionTime;
	
	private String location;
	
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getLocationTime() {
		return locationTime;
	}
	public void setLocationTime(String locationTime) {
		this.locationTime = locationTime;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getRealWarnYn() {
		return realWarnYn;
	}
	public void setRealWarnYn(String realWarnYn) {
		this.realWarnYn = realWarnYn;
	}
	public String getRealWarnOption() {
		return realWarnOption;
	}
	public void setRealWarnOption(String realWarnOption) {
		this.realWarnOption = realWarnOption;
	}
	public String getRealWarnOptionTime() {
		return realWarnOptionTime;
	}
	public void setRealWarnOptionTime(String realWarnOptionTime) {
		this.realWarnOptionTime = realWarnOptionTime;
	}
	public String getOrderMstId() {
		return orderMstId;
	}
	public void setOrderMstId(String orderMstId) {
		this.orderMstId = orderMstId;
	}
	public String getLocation() {
		String add = getAdd(this.x, this.y);  
        JSONObject jsonObject = new JSONObject(add);  
        JSONArray jsonArray = new JSONArray(jsonObject.getString("addrList"));  
        JSONObject j_2 = jsonArray.getJSONObject(0);  
        String allAdd = j_2.getString("admName");  
        String arr[] = allAdd.split(",");  
        
        String allAddName = j_2.getString("name");  
        location = arr[0]+arr[1]+arr[2]+allAddName;  
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
    protected String getAdd(String log, String lat ){  
        //lat 小  log  大  
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)  
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";  
        String res = "";     
        try {     
            URL url = new URL(urlString);    
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
            conn.setDoOutput(true);    
            conn.setRequestMethod("POST");    
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
            String line;    
           while ((line = in.readLine()) != null) {    
               res += line+"\n";    
         }    
            in.close();    
        } catch (Exception e) {    
            System.out.println("error in wapaction,and e is " + e.getMessage());    
        }   
        System.out.println(res);  
        return res;    
    }  
	
}
