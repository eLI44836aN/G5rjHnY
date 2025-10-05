// 代码生成时间: 2025-10-06 02:05:24
// CertificateManagementService.java
package com.example.certificatemanagement;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Service class responsible for managing SSL/TLS certificates.
 */
@Service
public class CertificateManagementService {

    private final RestTemplate restTemplate;

    @Autowired
    public CertificateManagementService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Load certificate from a file and add it to the trust store.
     * @param certFilePath Path to the certificate file.
     * @throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException
     */
    public void loadCertificate(String certFilePath) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        // Initialize KeyStore
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(null, null); // Load an empty KeyStore

        // Initialize Certificate Factory
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

        // Read certificate from file
        try (InputStream certInputStream = new FileInputStream(certFilePath)) {
            X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(certInputStream);

            // Add certificate to trust store
            trustStore.setCertificateEntry("certificatealias", certificate);
        }

        // Initialize TrustManagerFactory and SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);
        sslContext.init(null, tmf.getTrustManagers(), null);

        // Update RestTemplate with new SSLContext
        restTemplate.getInterceptors().clear(); // Clear existing interceptors
        restTemplate.getRestTemplate().getHttpClient().getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        restTemplate.getRestTemplate().getHttpClient().getSSLSocketFactory().setHostnameVerifier((hostname, session) -> true);
    }

    /**
     * Retrieves the current SSL/TLS certificates from the trust store.
     * @return A Base64 encoded string of the certificates.
     * @throws IOException, CertificateException
     */
    public String getCertificates() throws IOException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try (InputStream inputStream = new FileInputStream("truststore.jks")) {
            keyStore.load(inputStream, null);
        }

        StringBuilder certificates = new StringBuilder();
        for (String alias : Collections.list(keyStore.aliases())) {
            X509Certificate certificate = (X509Certificate) keyStore.getCertificate(alias);
            if (certificate != null) {
                certificates.append(Base64.getEncoder().encodeToString(certificate.getEncoded()));
            }
        }

        return certificates.toString();
    }

    // Additional methods for certificate management can be added here
}
