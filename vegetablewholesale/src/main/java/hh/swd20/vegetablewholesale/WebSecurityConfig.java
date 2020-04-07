package hh.swd20.vegetablewholesale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.swd20.vegetablewholesale.web.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests().antMatchers("/css/**").permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/", "/productlist").permitAll()//kuka tahansa käyttäjä voi nähdä productlist sivun
        .antMatchers("/delete/{productId}").hasRole("ADMIN")//ainoastaan admin-oikeudet omaava käyttäjä voi poistaa tuotteen
        //.antMatchers("/editproduct/{productId}").hasRole("ADMIN")//ainoastaan admin-oikeudet omaava käyttäjä voi poistaa tuotteen
          .anyRequest().authenticated()
          .and()
      .formLogin()
        //  .loginPage("/login")
          .defaultSuccessUrl("/productlist")
          .permitAll()
          .and()
      .logout()
          .permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
