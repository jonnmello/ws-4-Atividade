package com.devsuperior.movieflix.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	private Environment env;//apartir desse objeto consegue acessar variaveis(ambiente execução aplicação)

	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC = {"/oauth/token","/h2-console/**"}; //rota publica para logar
	
	
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore); //com isso aqui o Resource server consegue analisar o token e ver se esta batendo, se expirou ou nao se é valido etc
		
		
	}

	@Override
	public void configure(HttpSecurity http) throws Exception { //configurar as rotas quem pode acessar oq
		
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {//se eu to rodando o profile teste eu quero liberar o h2
			
			http.headers().frameOptions().disable(); //liberar o h2
		}
		
		http.authorizeRequests() 
		.antMatchers(PUBLIC).permitAll() //antMatchers define autorizações (quem tiver acessando a rota do vetor PUBLIC vai ta liberado nao pede loguin
		.anyRequest().authenticated();// qualquer outra rota só pode se for autenticado, tem que ta logado
		
	}
	
	

}
