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
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.youtube.YouTube;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class IntruderAlertApplication {

  private static YouTube youtube;

  public static void main(String[] args) throws IOException, GeneralSecurityException {
    Credential credential = OAuthHelper.builder()
        .withClientSecret("./src/main/resources/client_secret.json")
        .withApplicationName("Intruder Alerter")
        .withScope("https://www.googleapis.com/auth/youtube")
        .build();

    youtube = YoutubeService.builder()
        .applicationName("Intruder Alerter")
        .credentials(credential)
        .build();

    System.out.println(youtube);

  }

}
