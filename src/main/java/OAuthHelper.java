/**
 * CONFIDENTIAL INFORMATION
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 *
 * Date: May 13, 2017
 * Copyright 2017 innoirvinge@gmail.com
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.oauth2.Oauth2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class OAuthHelper {

  public static OAuthBuilder builder() throws GeneralSecurityException, IOException {
    return new OAuthBuilder();
  }

  public static class OAuthBuilder {

    private String clientSecretJson;

    private String applicationName;

    private JsonFactory jsonFactory;

    List<String> scope;

    public OAuthBuilder() {
      this.jsonFactory = JacksonFactory.getDefaultInstance();
    }

    public OAuthBuilder withClientSecret(final String clientSecretJson) {
      this.clientSecretJson = clientSecretJson;
      return this;
    }

    public OAuthBuilder withApplicationName(final String name) {
      this.applicationName = name;
      return this;
    }

    public OAuthBuilder withScope(final String scope) {
      this.scope = Collections.singletonList(scope);
      return this;
    }

    public Oauth2 build() throws IOException, GeneralSecurityException {
      GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
          jsonFactory,
          new InputStreamReader(new FileInputStream(clientSecretJson))
      );

      NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

      FileDataStoreFactory dataStoreFactory = new FileDataStoreFactory(new File(
          System.getProperty("user.dir"),
          ".oauth-credentials"
      ));

      GoogleAuthorizationCodeFlow authorizationFlow = new GoogleAuthorizationCodeFlow.Builder(
          httpTransport,
          this.jsonFactory,
          clientSecrets,
          this.scope
      ).setDataStoreFactory(dataStoreFactory).build();

      LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8080).build();
      Credential credential = new AuthorizationCodeInstalledApp(authorizationFlow, localReceiver)
          .authorize("user");

      return new Oauth2.Builder(
          httpTransport,
          jsonFactory,
          credential
      ).setApplicationName(applicationName)
          .build();
    }

  }

}
