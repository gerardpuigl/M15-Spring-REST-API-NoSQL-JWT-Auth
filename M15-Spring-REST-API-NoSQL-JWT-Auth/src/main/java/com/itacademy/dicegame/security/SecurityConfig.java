package com.itacademy.dicegame.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * The @EnableWebSecurity annotation tells Spring to apply the web security configuration declared by the class. 
 * The class extends WebSecurityConfigurerAdapter, which provides a convenient customization base.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * The @Value annotation on an instance variable is the Spring way of assigning
	 * a property value from the application.properties file.
	 */
	@Value("${auth0.audience}")
	private String audience;

	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	private String issuer;

	/**
	 * You override the configure method to ensure GET requests can be processed without authentication. 
	 * Other requests require a JWT, which will be verified using the issuer-uri from the application.properties file.
	 * 
	 * HttpSecurity is a builder class and provides numerous convenience methods that can be chained.
	 * Under the hood, each method adds a filter the HTTP request needs to pass through.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.cors()
			.configurationSource(corsConfigurationSource())
			.and()
			.oauth2ResourceServer()
			.jwt()
			.decoder(jwtDecoder())
//			.jwtAuthenticationConverter(jwtAuthenticationConverter())
			;
}

	/**
	 * Control de acceso HTTP (CORS) In this section, you'll set up CORS in your
	 * SecurityConfig class.
	 */
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedMethods(List.of(
				HttpMethod.GET.name(),
				HttpMethod.PUT.name(), 
				HttpMethod.POST.name(),
				HttpMethod.DELETE.name()));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
		return source;
	}

	/**
	 * In the jwtDecoder method, you ensure both the audience claim (aud) and the
	 * issuer claim (iss) are validated.
	 */
	JwtDecoder jwtDecoder() {
		OAuth2TokenValidator<Jwt> withAudience = new AudienceValidator(audience);
		OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
		OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withAudience, withIssuer);

		NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(issuer);
		jwtDecoder.setJwtValidator(validator);
		return jwtDecoder;
	}

	/**
	 * A JWT issued by an authorization server will typically have a scope
	 * attribute, listing the granted permissions. Spring calls them granted
	 * authorities. Instead, Auth0 uses a custom claim called permissions to specify
	 * them.
	 * 
	 * Spring provides a default instance of JwtAuthenticationConverter which
	 * expects granted authorities in a scope or scp claim. To use permissions
	 * instead.
	 */

	JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
		converter.setAuthoritiesClaimName("permissions");
		converter.setAuthorityPrefix("");

		JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
		jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
		return jwtConverter;
	}
	/**
	 * Finally, add a @PreAuthorize annotation to the relevant methods in the
	 * ItemController. The @PreAuthorize annotation holds a Spring Expression
	 * Language (SpEL) expression which must be satisfied before the method is
	 * executed.
	 * 
	 * hasAuthority will check if the permission/argument is in the list of granted
	 * authorities. Since you've ensured they will be read from the permissions
	 * claim
	 */
}