package pl.mateusz.Registration.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.mateusz.Registration.appuser.AppUserService;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig  {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**")
                .anonymous().antMatchers("/", "/registration", "/user/add")
                .permitAll()
                .antMatchers("/about").hasAnyAuthority("ADMIN")
                .antMatchers("/continent").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/test").hasAnyAuthority("USER")
                .anyRequest().hasAnyAuthority("ADMIN");
        http
                .formLogin().permitAll()
                .loginPage("/login").and()
                .exceptionHandling().accessDeniedPage("/error");
        http
                .logout()
                .permitAll();

        return http.build();
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/v*/registration/**")
//                .permitAll()
//                .antMatchers("/", "/user/add")
//                .permitAll()
//                .antMatchers("/login", "/registration", "/error")
//                .permitAll()
//                .antMatchers("/about").hasAnyAuthority("ADMIN")
//                .antMatchers("/continent").hasAnyAuthority("USER", "ADMIN")
//                .anyRequest()
//                .authenticated().and()
//                .formLogin().loginPage("/login").and()
//                .exceptionHandling().accessDeniedPage("/error").and()
//                .logout();
//    }


//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
        return (web) -> web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
