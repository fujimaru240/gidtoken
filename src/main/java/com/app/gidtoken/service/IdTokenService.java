package com.app.gidtoken.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.auth.oauth2.IdTokenProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;

import org.springframework.stereotype.Service;

@Service
public class IdTokenService {
  public com.google.auth.oauth2.IdToken getIdToken(String keyFile, String audience) {
    InputStream is;
    try {
      is = new FileInputStream(keyFile);
      ServiceAccountCredentials sourceCredentials = ServiceAccountCredentials.fromStream(is);
      return sourceCredentials.idTokenWithAudience(audience, new ArrayList<IdTokenProvider.Option>());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
