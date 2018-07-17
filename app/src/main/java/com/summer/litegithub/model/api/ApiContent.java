package com.summer.litegithub.model.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.summer.litegithub.model.cookie.CookiesManager;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.model.api
 *  文件名:   ApiContent
 *  创建者:   Summers
 *  创建时间: 2018/7/1619:48
 *  描述：    TODO
 */
public class ApiContent {
    private static Retrofit mRetrofit;
    static {
        createProxy();
    }

    public static <T> T createApi(Class<T> serviceClass) {
        return mRetrofit.create(serviceClass);
    }

    private static void createProxy() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm.dd hh:mm:ss").create();
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().build();
                        return chain.proceed(request);
                    }
                })
                .cookieJar(new CookiesManager());
        SSLSocketFactory sslSocketFactory = getSSLSocketFactory(
                new Buffer().writeUtf8(AppConfig.SSL_KEY).inputStream(),
                new Buffer().writeUtf8(AppConfig.MIDDLE_KEY).inputStream());
        if (sslSocketFactory != null) {
            builder.sslSocketFactory(sslSocketFactory);
        }
        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
    }

    private static SSLSocketFactory getSSLSocketFactory(InputStream... certifacates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certifacates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias,certificateFactory.generateCertificate(certificate));
                if (certificate != null) {
                    certificate.close();
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null,trustManagerFactory.getTrustManagers(),new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
