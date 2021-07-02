package org.fengfei.lanproxy.common;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http请求工具类
 * @author ouyangjun
 */
public class HttpClientUtils {

    /**
     * http get请求, 不带参数
     * @param requestURL
     * @return
     */
    public static String doGet(String requestURL) {
        // 记录信息
        StringBuffer buffer = new StringBuffer();

        HttpURLConnection conn = null;
        try {
            URL url = new URL(requestURL);
            conn = (HttpURLConnection) url.openConnection();

            // 设置请求的属性
            conn.setDoOutput(true); // 是否可以输出
            conn.setRequestMethod("GET"); // 请求方式, 只包含"GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE", "TRACE"六种
            conn.setConnectTimeout(60000); // 最高超时时间
            conn.setReadTimeout(60000); // 最高读取时间
            conn.setConnectTimeout(60000); // 最高连接时间

            // 读取数据
            InputStream is = null;
            InputStreamReader inputReader = null;
            BufferedReader reader = null;
            try {
                is = conn.getInputStream();
                inputReader = new InputStreamReader(is, "UTF-8");
                reader = new BufferedReader(inputReader);

                String temp;
                while ((temp = reader.readLine()) != null) {
                    buffer.append(temp);
                }
            } catch (Exception e) {
                System.out.println("HttpGetUtils doGet error: " + e);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (inputReader != null) {
                        inputReader.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    System.out.println("HttpGetUtils doGet error: " + e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 当http连接空闲时, 释放资源
            if (conn != null) {
                conn.disconnect();
            }
        }
        // 返回信息
        return buffer.length()==0 ? "" : buffer.toString();
    }

    /**
     * http post请求, 带参数
     * @param requestURL
     * @param params
     * @return
     */
    public static String doPost(String requestURL, String params) {
        // 记录信息
        StringBuffer buffer = new StringBuffer();

        HttpURLConnection conn = null;
        try {
            URL url = new URL(requestURL);
            conn = (HttpURLConnection) url.openConnection();

            // 设置请求的属性
            conn.setDoOutput(true); // 是否可以输出
            conn.setRequestMethod("POST"); // 请求方式, 只包含"GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE", "TRACE"六种
            conn.setConnectTimeout(60000); // 最高超时时间
            conn.setReadTimeout(60000); // 最高读取时间
            conn.setConnectTimeout(60000); // 最高连接时间

            conn.setDoInput(true); // 是否可以输入
            if (params != null) {
                // 设置参数为json格式
                conn.setRequestProperty("Content-type", "application/json");

                // 写入参数信息
                OutputStream os = conn.getOutputStream();
                try {
                    os.write(params.getBytes("UTF-8"));
                } catch (Exception e) {
                    System.out.println("HttpPostUtils doPost error: " + e);
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                    } catch (IOException e) {
                        System.out.println("HttpPostUtils doPost error: " + e);
                    }
                }
            }

            // 读取数据
            InputStream is = null;
            InputStreamReader inputReader = null;
            BufferedReader reader = null;
            try {
                is = conn.getInputStream();
                inputReader = new InputStreamReader(is, "UTF-8");
                reader = new BufferedReader(inputReader);

                String temp;
                while ((temp = reader.readLine()) != null) {
                    buffer.append(temp);
                }
            } catch (Exception e) {
                System.out.println("HttpPostUtils doPost error: " + e);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (inputReader != null) {
                        inputReader.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    System.out.println("HttpPostUtils doPost error: " + e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 当http连接空闲时, 释放资源
            if (conn != null) {
                conn.disconnect();
            }
        }
        // 返回信息
        return buffer.length()==0 ? "" : buffer.toString();
    }
}