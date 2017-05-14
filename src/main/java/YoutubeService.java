/**
 * CONFIDENTIAL INFORMATION
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 *
 * Date: May 14, 2017
 * Copyright 2017 innoirvinge@gmail.com
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class YoutubeService {

  public static YoutubeServiceBuilder builder() {
    return new YoutubeServiceBuilder();
  }

  public static class YoutubeServiceBuilder {

    private String applicationName;

    private Credential credentials;

    public YoutubeServiceBuilder applicationName(final String applicationName) {
      this.applicationName = applicationName;
      return this;
    }

    public YoutubeServiceBuilder credentials(final Credential credential) {
      this.credentials = credential;
      return this;
    }

    public YouTube build() throws GeneralSecurityException, IOException {
      NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
      JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

      return new YouTube.Builder(
          httpTransport,
          jsonFactory,
          credentials
      ).setApplicationName(applicationName).build();
    }
  }

}
