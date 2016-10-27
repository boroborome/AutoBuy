package com.happy3w.autobuy.util;

import org.apache.catalina.util.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by ysgao on 4/28/16.
 */
public class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {

    private final Logger logger = LoggerFactory.getLogger(HttpsClientRequestFactory.class);

    private final HostnameVerifier hostNameVerifier;
    private final ServerInfo serverInfo;

    public HttpsClientRequestFactory(final HostnameVerifier hostNameVerifier,
                                     final ServerInfo serverInfo) {
        this.hostNameVerifier = (hostNameVerifier == null ? new SslThumbprintVerifier() : hostNameVerifier);
        this.serverInfo = serverInfo;
    }

    @Override
    protected void prepareConnection(final HttpURLConnection connection, final String httpMethod)
            throws IOException {
        if (connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) connection).setHostnameVerifier(hostNameVerifier);
            ((HttpsURLConnection) connection).setSSLSocketFactory(initSSLContext()
                    .getSocketFactory());
        }
        super.prepareConnection(connection, httpMethod);
    }

    private SSLContext initSSLContext() {
        try {
            System.setProperty("https.protocols", "TLSv1");

            // Set ssl trust manager. VerifyStruct against our server thumbprint
            final SSLContext ctx = SSLContext.getInstance("TLSv1");
//            final SslThumbprintVerifier verifier = new SslThumbprintVerifier(serverInfo);
            final ThumbprintTrustManager thumbPrintTrustManager =
                    new ThumbprintTrustManager();//null, verifier);
            ctx.init(null, new TrustManager[] { thumbPrintTrustManager }, null);
            return ctx;
        } catch (final Exception ex) {
            logger.error(
                    "An exception was thrown while trying to initialize HTTP security manager.", ex);
            return null;
        }
    }

    private static class SslThumbprintVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    }

    private static class ThumbprintTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
