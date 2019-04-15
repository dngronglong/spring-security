package com.springsecurity.demo.utils;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import javax.net.ssl.*;
import java.io.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @program: spring-security
 * @description: 网络请求封装
 * @author: DrongRonglong
 * @create: 2019-03-22 13:21
 **/

public class SendHttp {
    /**
     * 请求超时时间
     */
    private static final int TIME_OUT = 120000;

    /**
     * Https请求
     */
    private static final String HTTPS = "https";

    /**
     * 发送Get请求
     *
     * @param url 请求URL
     * @return 服务器响应对象
     * @throws IOException
     */
    public static Response get(String url) throws IOException {
        return get(url, null);
    }

    /**
     * 发送Get请求
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @return 服务器响应对象
     * @throws IOException
     */
    public static Response get(String url, Map<String, String> headers) throws IOException {
        if (null == url || url.isEmpty()) {
            throw new RuntimeException("The request URL is blank.");
        }

        // 如果是Https请求
        if (url.startsWith(HTTPS)) {
            getTrust();
        }
        Connection connection = Jsoup.connect(url);
        connection.method(Connection.Method.GET);
        connection.timeout(TIME_OUT);
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);

        if (null != headers) {
            connection.headers(headers);
        }

        Response response = connection.execute();
        return response;
    }

    /**
     * 发送JSON格式参数POST请求
     *
     * @param url 请求路径
     * @param params JSON格式请求参数
     * @return 服务器响应对象
     * @throws IOException
     */
    public static Response post(String url, String params) throws IOException {
        return doPostRequest(url, null, null, null, params);
    }

    /**
     * 发送JSON格式参数POST请求
     *
     * @param url 请求路径
     * @param headers 请求头中设置的参数
     * @param params JSON格式请求参数
     * @return 服务器响应对象
     * @throws IOException
     */
    public static Response post(String url, Map<String, String> headers, String params) throws IOException {
        return doPostRequest(url, headers, null, null, params);
    }

    /**
     * 字符串参数post请求
     *
     * @param url 请求URL地址
     * @param paramMap 请求字符串参数集合
     * @return 服务器响应对象
     * @throws IOException
     */
    public static Response post(String url, Map<String, String> paramMap) throws IOException {
        return doPostRequest(url, null, paramMap, null, null);
    }

    /**
     * 带上传文件的post请求
     *
     * @param url 请求URL地址
     * @param paramMap 请求字符串参数集合
     * @param fileMap 请求文件参数集合
     * @return 服务器响应对象
     * @throws IOException
     */
    public static Response post(String url, Map<String, String> paramMap, Map<String, File> fileMap) throws IOException {
        return doPostRequest(url, null, paramMap, fileMap, null);
    }

    /**
     * 执行post请求
     *
     * @param url 请求URL地址
     * @param paramMap 请求字符串参数集合
     * @param fileMap 请求文件参数集合
     * @return 服务器响应对象
     * @throws IOException
     */
    private static Response doPostRequest(String url, Map<String, String> headers, Map<String, String> paramMap, Map<String, File> fileMap, String jsonParams) throws IOException {
        if (null == url || url.isEmpty()) {
            throw new RuntimeException("The request URL is blank.");
        }

        // 如果是Https请求
        if (url.startsWith(HTTPS)) {
            getTrust();
        }

        Connection connection = Jsoup.connect(url);
        connection.method(Connection.Method.POST);
        connection.timeout(TIME_OUT);
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);

        if (null != headers) {
            connection.headers(headers);
        }

        // 收集上传文件输入流，最终全部关闭
        List<InputStream> inputStreamList = null;
        try {

            // 添加文件参数
            if (null != fileMap && !fileMap.isEmpty()) {
                inputStreamList = new ArrayList<InputStream>();
                InputStream in = null;
                File file = null;
                Set<Entry<String, File>> set = fileMap.entrySet();
                for (Entry<String, File> e : set) {
                    file = e.getValue();
                    in = new FileInputStream(file);
                    inputStreamList.add(in);
                    connection.data(e.getKey(), file.getName(), in);
                }
            }

            // 设置请求体为JSON格式内容
            else if (null != jsonParams && !jsonParams.isEmpty()) {
                connection.header("Content-Type", "application/json;charset=UTF-8");
                connection.requestBody(jsonParams);
            }

            // 普通表单提交方式
            else {
                connection.header("Content-Type", "application/x-www-form-urlencoded");
            }

            // 添加字符串类参数
            if (null != paramMap && !paramMap.isEmpty()) {
                connection.data(paramMap);
            }

            Response response = connection.execute();
            return response;
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }

        // 关闭上传文件的输入流
        finally {
            if (null != inputStreamList) {
                for (InputStream in : inputStreamList) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 获取服务器信任
     */
    private static void getTrust() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[] { new X509TrustManager() {

                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            } }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
