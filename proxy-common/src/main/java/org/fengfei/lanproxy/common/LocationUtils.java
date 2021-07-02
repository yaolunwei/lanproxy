package org.fengfei.lanproxy.common;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * 位置工具
 */
public class LocationUtils {
    private static final String REQ_IP_URL = "https://api.map.baidu.com/location/ip?ak=vqgX1TNGqwHzOypM394BKRuZAkCRuT3Q&coor=bd09ll";
    private static final String REQ_LL_URL = "https://api.map.baidu.com/reverse_geocoding/v3/?ak=vqgX1TNGqwHzOypM394BKRuZAkCRuT3Q&output=json&coordtype=wgs84ll&location=";

    public static Location getLocationAuto(String ip) {
        Location l1 = getLocation(ip);
        return getLocation(l1.getLongitude(), l1.getLatitude());
    }

    public static Location getLocation(String ip) {
        String url = REQ_IP_URL + "&ip="+ip;
        String json = HttpClientUtils.doGet(url);

        Location location = new Location();

        try {
            LocationIP locationIP = JsonUtil.json2object(json, new TypeToken<LocationIP>(){});
            if (locationIP.status==0) {
                location.setCode(locationIP.content.address_detail.city_code+"");
                location.setLongitude(locationIP.content.point.x);
                location.setLatitude(locationIP.content.point.y);
                location.setAddress(locationIP.content.address);
                location.setFullAddress(locationIP.content.address);
                location.setProvince(locationIP.content.address_detail.province);
                location.setCity(locationIP.content.address_detail.city);
                location.setDistrict(locationIP.content.address_detail.district);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public static Location getLocation(String longitude, String latitude) {
        String url = REQ_LL_URL + "&location="+latitude + "," + longitude;
        String json = HttpClientUtils.doGet(url);

        Location location = new Location();

        try {
            LocationLatitudeAndLongitude latitudeAndLongitude = JsonUtil.json2object(json, new TypeToken<LocationLatitudeAndLongitude>(){});
            if (latitudeAndLongitude.status==0) {
                location.setCode(latitudeAndLongitude.result.addressComponent.adcode);
                location.setLongitude(latitudeAndLongitude.result.location.lng+"");
                location.setLatitude(latitudeAndLongitude.result.location.lat+"");
                location.setAddress(latitudeAndLongitude.result.formatted_address);
                location.setFullAddress(latitudeAndLongitude.result.formatted_address + " " + latitudeAndLongitude.result.business);
                location.setProvince(latitudeAndLongitude.result.addressComponent.province);
                location.setCity(latitudeAndLongitude.result.addressComponent.city);
                location.setDistrict(latitudeAndLongitude.result.addressComponent.district);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public static class LocationIP {
        public String address;
        public ContentDTO content;
        public Integer status;

        public static class ContentDTO {
            public String address;
            public AddressDetailDTO address_detail;
            public PointDTO point;

            public static class AddressDetailDTO {
                public String city;
                public Integer city_code;
                public String district;
                public String province;
                public String street;
                public String street_number;

            }

            public static class PointDTO {
                public String x;
                public String y;
            }
        }
    }

    public static class LocationLatitudeAndLongitude {
        public Integer status;
        public ResultDTO result;

        public static class ResultDTO {
            public LocationDTO location;
            public String formatted_address;
            public String business;
            public AddressComponentDTO addressComponent;
            public String sematic_description;
            public Integer cityCode;

            public static class LocationDTO {
                public Double lng;
                public Double lat;
            }

            public static class AddressComponentDTO {
                public String country;
                public Integer country_code;
                public String country_code_iso;
                public String country_code_iso2;
                public String province;
                private String city;
                public Integer city_level;
                public String district;
                public String town;
                public String town_code;
                public String adcode;
                public String street;
                public String street_number;
                public String direction;
                public String distance;
            }
        }
    }

}

/**
 * {
 *   "address": "CN|贵州|贵阳|None|CMNET|0|0",
 *   "content": {
 *     "address": "贵州省贵阳市",
 *     "address_detail": {
 *       "city": "贵阳市",
 *       "city_code": 146,
 *       "district": "",
 *       "province": "贵州省",
 *       "street": "",
 *       "street_number": ""
 *     },
 *     "point": {
 *       "x": "106.70917710",
 *       "y": "26.62990674"
 *     }
 *   },
 *   "status": 0
 * }
 */

