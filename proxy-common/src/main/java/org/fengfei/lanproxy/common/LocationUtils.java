package org.fengfei.lanproxy.common;

import com.google.gson.reflect.TypeToken;

/**
 * 位置工具
 */
public class LocationUtils {

    private static final String REQ_IP_URL = "https://restapi.amap.com/v5/ip?type=4&key=349e59d49261a067c09d8a5042920b9d&ip=";

    public static Location getLocation(String ip) {
        String url = REQ_IP_URL + ip;
        String json = HttpClientUtils.doGet(url);
        Location location = new Location();

        try {
            LocationIP locationIP = JsonUtil.json2object(json, new TypeToken<LocationIP>(){});
            if (locationIP!=null && locationIP.status==1) {
                location.setCode(locationIP.infocode);
                String[] locations = locationIP.location.split(",");
                if (locations.length==2) {
                    location.setLongitude(locations[0]);
                    location.setLatitude(locations[1]);
                }
                String address = locationIP.province + " " + locationIP.city + " " + locationIP.district;
                location.setAddress(address);
                location.setFullAddress(address);
                location.setProvince(locationIP.province);
                location.setCity(locationIP.city);
                location.setDistrict(locationIP.district);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public static class LocationIP {
        public int status;
        public String info;
        public String infocode;
        public String country;
        public String province;
        public String city;
        public String district;
        public String isp;
        public String location;
        public String ip;
    }

}

