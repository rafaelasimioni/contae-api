package br.com.contae.config;

import br.com.contae.infrastructure.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService usuarioDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(usuarioDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Habilita o CORS configurado no CorsConfig
                .cors(Customizer.withDefaults())
                // Desativa CSRF pois APIs REST com JWT são stateless
                .csrf(csrf -> csrf.disable())
                // Garante que a aplicação não cria sessões HTTP (Stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Define o comportamento de erro para não autenticado (retorna 401 puro em vez de redirecionar para login)
                .exceptionHandling(e -> e.authenticationEntryPoint(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                // Regras de autorização de rotas
                .authorizeHttpRequests(auth -> auth
                        // Libera requisições de CORS preflight (OPTIONS)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Libera a raiz da API (para evitar o 401 ao abrir o link do Render no navegador)
                        .requestMatchers(HttpMethod.GET, "/").permitAll()

                        // Rotas públicas de autenticação e registo
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()

                        // Documentação da API (Swagger)
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()

                        // Qualquer outra rota obrigatoriamente exige autenticação (Token JWT)
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                // Adiciona o filtro JWT antes do filtro padrão de utilizador/senha
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}