package com.hifive.net;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * http请求链接池
 * Created by huangyong on 2017/5/16.
 */
public class HttpConnectionManager {

    private static PoolingHttpClientConnectionManager cm = null;

    private static CloseableHttpClient closeableHttpClient =null;

    static {
        LayeredConnectionSocketFactory sslsf = null;
        try {
//            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());//要进行主机验证
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                    return true;//不进行主机验证
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1.2"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(50);
        closeableHttpClient =  HttpClients.custom().setConnectionManager(cm).build();
    }

    public static CloseableHttpClient getHttpClient() {
        return closeableHttpClient;
    }
}