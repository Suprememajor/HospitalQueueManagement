package org.supremecorp.hospitalqueuemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HospitalQueueManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalQueueManagementApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    CommandLineRunner commandLineRunner(HospitalService hospitalService,
                                        UnitService unitService) {
        return args -> {
            Hospital hospital1 = new Hospital(
                    "Buea Regional Hospital",
                    "Buea, Cameroon",
                    "674298331",
                    "www.buearegionalhospitalwebsite.com",
                    "buearegionalhospital@gmail"
            );
            hospital1.getTexts().add("First Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. Java is the #1 programming language and development platform.");
            hospital1.getTexts().add("Second Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. Java is the #1 programming language and development platform.");
            hospital1.getTexts().add("Third Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. Java is the #1 programming language and development platform.");
            hospital1.getTexts().add("Fourth Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. Java is the #1 programming language and development platform.");
            Hospital hospital2 = new Hospital(
                    "Limbe Regional Hospital",
                    "Limbe, Cameroon",
                    "652237802",
                    "www.limberegionalhospitalwebsite.com",
                    "limberegionalhospital@gmail"
            );
            hospital2.getTexts().add("First Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. Java is the #1 programming language and development platform.");
            hospital2.getTexts().add("Second Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. Java is the #1 programming language and development platform.");
            hospital2.getTexts().add("Third Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. Java is the #1 programming language and development platform.");
            hospital2.getTexts().add("Fourth Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. Java is the #1 programming language and development platform.");
            Hospital savedHos1 = hospitalService.save(hospital1);
            Hospital savedHos2 = hospitalService.save(hospital2);

            Unit unit1 = new Unit(
                    "Maternity",
                    "For prenatal and antenatal care",
                    savedHos1
            );
            Unit unit2 = new Unit(
                    "Pediatrics",
                    "For children",
                    savedHos1
            );
            Unit unit3 = new Unit(
                    "Chiropractor",
                    "For spine adjustments",
                    savedHos1
            );
            Unit unit4 = new Unit(
                    "Dentist",
                    "For healthier teeth",
                    savedHos1
            );
            Unit unit5 = new Unit(
                    "Dentist",
                    "For healthier teeth",
                    savedHos2
            );
            Unit unit6 = new Unit(
                    "Maternity",
                    "For prenatal and antenatal care",
                    savedHos2
            );
            unitService.save(unit1);
            unitService.save(unit2);
            unitService.save(unit3);
            unitService.save(unit4);
            unitService.save(unit5);
            unitService.save(unit6);
        };
    }*/
}
