package com.itacademy.dicegame.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;

import java.util.List;

/**
 * OAuth2TokenValidator interface and its method validate provide means to verify custom OAuth 2.0 Token attributes. 
 * With the class above, you ensure only tokens containing the specified audience, or aud claim to be exact, are valid.
 */
class AudienceValidator implements OAuth2TokenValidator<Jwt> {
  private final String audience;

  AudienceValidator(String audience) {
    Assert.hasText(audience, "audience is null or empty");
    this.audience = audience;
  }

  public OAuth2TokenValidatorResult validate(Jwt jwt) {
    List<String> audiences = jwt.getAudience();
    if (audiences.contains(this.audience)) {
      return OAuth2TokenValidatorResult.success();
    }
    OAuth2Error err = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN);
    return OAuth2TokenValidatorResult.failure(err);
  }
}