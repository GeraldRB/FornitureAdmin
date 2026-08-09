package com.Forniture;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class StorageConfig {

    @Value("${firebase.json.path}")
    private String jsonPath;

    @Value("${firebase.json.file}")
    private String jsonFile;

    @Value("${FIREBASE_CREDENTIALS:}")
    private String firebaseCredentials;

    @Bean
    public Storage storage() throws IOException {

        InputStream inputStream;

        if (firebaseCredentials != null && !firebaseCredentials.isBlank()) {

            inputStream = new ByteArrayInputStream(
                    firebaseCredentials.getBytes(StandardCharsets.UTF_8)
            );

        } else {

            ClassPathResource resource =
                    new ClassPathResource(jsonPath + File.separator + jsonFile);

            inputStream = resource.getInputStream();
        }

        try (inputStream) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);

            return StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();
        }
    }
}