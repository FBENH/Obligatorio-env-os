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
        .requestMatchers("/sucursales","/sucursales/agregar","/sucursales/modificar","/sucursales/eliminar").hasAuthority("Empleado")
        .requestMatchers("/empleados","/empleados/agregar","/empleados/modificar","/empleados/eliminar").hasAuthority("Empleado")
        .requestMatchers("/categorias","/categorias/agregar","/categorias/modificar","/categorias/eliminar").hasAuthority("Empleado")
        .requestMatchers("/registrarcliente").anonymous()
        .requestMatchers("/micuenta/editar","/micuenta/eliminar","/micuenta").hasAuthority("Cliente")
        .requestMatchers("/estadosrastreo","estadosrastreo/agregar","/estadosrastreo/modificar","/estadosrastreo/eliminar").hasAuthority("Empleado")
        .requestMatchers("/paquetes","/paquetes/agregar","/paquetes/modificar","/paquetes/eliminar").hasAuthority("Empleado")
        .requestMatchers("/paquetes/registrar","/paquetes/listar").hasAuthority("Cliente")
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
