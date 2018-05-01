package com.gnstkd95.cafego;

import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpRequestManager {
    public static String CHARSET_UTF8 = "UTF-8";

    public static String METHOD_GET = "GET";
    public static String METHOD_POST = "POST";

    public static String PROTOCOL_HTTP = "http";
    public static String PROTOCOL_HTTPS = "https";

    public URLConnectionData setRequestDetails(String method, String protocol, String page) throws IOException {
        return this.setRequestDetails(method, protocol, page, CHARSET_UTF8, null);
    }

    public URLConnectionData setRequestDetails(String method, String protocol, String page, String charset) throws IOException {
        return this.setRequestDetails(method, protocol, page, charset, null);
    }

    public URLConnectionData setRequestDetails(String method, String protocol, String page, VariableList variables) throws IOException {
        return this.setRequestDetails(method, protocol, page, CHARSET_UTF8, variables);
    }

    public URLConnectionData setRequestDetails(String method, String protocol, String page, String charset, VariableList variables) throws IOException {
        StringBuilder queryStr = new StringBuilder();
        URL queryUrl = null;

        if (variables != null) {
            Set<Map.Entry<String, String>> entries = variables.varMap.entrySet();
            Iterator<Map.Entry<String, String>> itr = entries.iterator();
            Map.Entry<String, String> entry;

            while (itr.hasNext()) {
                entry = itr.next();
                queryStr.append(entry.getKey() + "=");
                queryStr.append(entry.getValue() + "&");
            }
            queryStr.deleteCharAt(queryStr.length() - 1);

            if (method.equals(METHOD_GET)) {
                queryUrl = new URL(protocol + "://" + page + "?" + queryStr.toString());
            }
            else if (method.equals(METHOD_POST)) {
                queryUrl = new URL(protocol + "://" + page);
            }
        }

        URLConnection conn = queryUrl.openConnection();

        ((HttpURLConnection)conn).setRequestMethod(method); // 굳이 http, https를 분리하지 않아도 됨
        conn.setRequestProperty("Content-Type", "text/html");
        conn.setRequestProperty("Charset", charset);

        if (method.equals(METHOD_POST) && variables != null) {
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(queryStr.toString().getBytes().length));
        }

        return new URLConnectionData(method, charset, conn, queryStr.toString());
    }

    public void setRequestHeader(URLConnectionData data, String propertyName, String value) {
        data.conn.setRequestProperty(propertyName, value);
    }

    public String request(@NonNull URLConnectionData data) throws IOException {
        StringBuilder result = new StringBuilder();

        if (data.method.equals(METHOD_POST)) {
            PrintWriter writer = new PrintWriter(data.conn.getOutputStream());
            writer.print(data.queryStr); // println 메소드를 사용할 경우 1바이트가 더 전달됨. print로 할 것.
            writer.flush();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(data.conn.getInputStream(), data.charset));
        String line;
        while ((line = reader.readLine()) != null) result.append(line);

        return result.toString();
    }

    public static class VariableList {
        private HashMap<String, String> varMap = new HashMap<>();
        private String charset;

        public static final String CHARSET_UTF8 = HttpRequestManager.CHARSET_UTF8;

        public VariableList(String charset) throws UnsupportedEncodingException {
            this.charset = charset;

            URLEncoder.encode("", charset); // charset의 무결성을 확인하기 위한 코드
        }

        public VariableList add(String name, String value) {
            try {varMap.put(name, URLEncoder.encode(value, charset));} catch (Exception e) {}
            //생성자에서 이미 확인하므로, 객체가 제대로 만들어졌다면 여기에서는 절대 예외가 발생하지 않음

            return this;
        }
    }

    public static class URLConnectionData {
        String method;
        String charset;
        URLConnection conn;
        String queryStr;

        public URLConnectionData(String method, String charset, URLConnection conn, String queryStr) {
            this.method = method;
            this.charset = charset;
            this.conn = conn;
            this.queryStr = queryStr;
        }
    }
}
