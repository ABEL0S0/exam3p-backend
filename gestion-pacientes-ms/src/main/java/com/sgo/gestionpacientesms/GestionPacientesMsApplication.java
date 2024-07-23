package com.sgo.gestionpacientesms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GestionPacientesMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionPacientesMsApplication.class, args);
    }

}
