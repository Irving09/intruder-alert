/**
 * CONFIDENTIAL INFORMATION
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 *
 * Date: May 13, 2017
 * Copyright 2017 innoirvinge@gmail.com
 */

import com.google.api.services.youtube.YouTube;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class IntruderAlertApplication {

  private static YouTube youtube;

  public static void main(String[] args) throws IOException {
    String apiKey = Files.toString(new File("./src/main/resources/api-key.txt"), Charsets.UTF_8);
    System.out.println(apiKey);
  }

}
