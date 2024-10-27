package bios.obligatorio.envios.obligatorio_envios.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/","/static/**").permitAll()
        .requestMatchers("/sucursales","/sucursales/**").hasAuthority("Empleado")
        .requestMatchers("/empleados","/empleados/**").hasAuthority("Empleado")
        .requestMatchers("/categorias","/categorias/agregar","/categorias/modificar","/categorias/eliminar").hasAuthority("Empleado")
        .requestMatchers("/registrarcliente").anonymous()
        .requestMatchers("/micuenta/editar","/micuenta/eliminar","/micuenta").hasAuthority("Cliente")
        .requestMatchers("/estadosrastreo","estadosrastreo/**").hasAuthority("Empleado")
        .requestMatchers("/paquetes/registrar","/paquetes/listar").hasAuthority("Cliente")
        .requestMatchers("/paquetes","/paquetes/**").hasAuthority("Empleado")        
        .anyRequest().authenticated()
        )
        .formLogin(form -> form
        .loginPage("/ingresar").permitAll()
        //.defaultSuccessUrl("/",true)
        ).logout(logout ->  logout
        .logoutUrl("/salir")
        .logoutSuccessUrl("/").permitAll()
        );       

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/css/**");
    }

    
}
