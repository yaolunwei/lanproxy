package org.fengfei.lanproxy.protocol;

/**
 * 认证交换扩展信息
 */
public class AuthData {

    /** 名称 **/
    private String name;

    /** 默认代理 **/
    private String proxyLan;

    // 经度
    private double longitude;

    // 纬度
    private double latitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProxyLan() {
        return proxyLan;
    }

    public void setProxyLan(String proxyLan) {
        this.proxyLan = proxyLan;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
