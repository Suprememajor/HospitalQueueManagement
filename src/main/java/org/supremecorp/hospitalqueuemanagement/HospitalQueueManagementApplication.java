package org.supremecorp.hospitalqueuemanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.supremecorp.hospitalqueuemanagement.model.Hospital;
import org.supremecorp.hospitalqueuemanagement.services.base.HospitalService;

@SpringBootApplication
public class HospitalQueueManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalQueueManagementApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunner(HospitalService hospitalService) {
        return args -> {
            Hospital hospital1 = new Hospital(
                    "Buea Regional Hospital",
                    "Buea, Cameroon",
                    "674298331",
                    "www.buearegionalhospitalwebsite.com",
                    "buearegionalhospital@gmail"
            );
            Hospital hospital2 = new Hospital(
                    "Limbe Regional Hospital",
                    "Limbe, Cameroon",
                    "652237802",
                    "www.limberegionalhospitalwebsite.com",
                    "limberegionalhospital@gmail"
            );
            hospitalService.save(hospital1);
            hospitalService.save(hospital2);
        };
    }

}
