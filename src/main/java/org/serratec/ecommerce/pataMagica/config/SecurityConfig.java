package org.serratec.ecommerce.pataMagica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(authorize -> authorize
		.requestMatchers(HttpMethod.GET, "**").permitAll()
		//.requestMatchers(HttpMethod.PUT, "**").permitAll()
		//.requestMatchers(HttpMethod.POST, "**").permitAll()
		.requestMatchers(HttpMethod.POST, "**").hasRole("ADM")
		.requestMatchers(HttpMethod.DELETE, "**").hasRole("ADM")
		.requestMatchers(HttpMethod.PUT, "**").hasRole("ADM"))
		.csrf(csrf -> csrf.disable())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager usuariosDetalhes() {
		UserDetails usuario = User.builder()
				.username("admin")
				.password(encoder().encode("teste"))
				.roles("ADM").build();
		
		UserDetails usuario2 = User.builder()
				.username("rh")
				.password(encoder().encode("teste"))
				.roles("RH").build();
		
		return new InMemoryUserDetailsManager(usuario, usuario2);
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
