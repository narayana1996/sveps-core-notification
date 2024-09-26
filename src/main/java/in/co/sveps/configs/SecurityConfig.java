package in.co.sveps.configs;

import javax.sql.DataSource;

import in.co.sveps.service.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                 .antMatchers("/css/**").permitAll()
                 .antMatchers("/js/**").permitAll()
                 //.antMatchers("/employee/new").hasAnyAuthority("ADMIN","MANAGER")
                 //.antMatchers("/employee/edit/**").hasAnyAuthority("ADMIN","MANAGER")
                 //.antMatchers(HttpMethod.POST,"/employee/").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers("/").hasAnyAuthority("dashboard")
                 .antMatchers("/project/new").hasAnyAuthority("ADMIN","MANAGER")
                 .antMatchers(HttpMethod.POST,"/project/").hasAnyAuthority("ADMIN","MANAGER")
                 .antMatchers("/leave/approveLeavesRequests").hasAnyAuthority("ADMIN","MANAGER","LEAD")
                 .antMatchers("/send-otp").permitAll()
                .antMatchers("/reset-password").permitAll()
                .antMatchers("/reset-password-conform").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/employee").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest()
                .authenticated()
                .and()
             .exceptionHandling()
                .accessDeniedPage("/error/403")
                .and()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .successForwardUrl("/")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
              
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(customUserDetailsService).passwordEncoder(customPasswordEncoder);

    }  


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

}