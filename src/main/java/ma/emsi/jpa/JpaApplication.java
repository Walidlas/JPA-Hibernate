package ma.emsi.jpa;

import ma.emsi.jpa.entities.*;
import ma.emsi.jpa.repositories.PatientRepository;
import ma.emsi.jpa.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class JpaApplication{
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IService service){
        return args -> {
            Stream.of("Hicham", "Latifa", "Yassine")
                    .forEach(nom -> {
                        Patient p = new Patient();
                        p.setNom(nom);
                        p.setDateNaissance(new Date());
                        p.setMalade(Math.random()>0.5?true:false);
                        p.setScore(600);
                        service.savePatient(p);
                                    }
                    );
            Stream.of("Houssam", "Othman", "Reda")
                    .forEach(nom -> {
                        Medecin m = new Medecin();
                        m.setNom(nom);
                        m.setEmail(nom+"@gmail.com");
                        m.setSpecialite(Math.random()>0.5 ? "Ophtalmologiste" : "Chirurgien");
                        service.saveMedecin(m);
                    });
            Stream.of("Tejda", "Sayar")
                    .forEach(userName -> {
                        User user = new User();
                        user.setUserName(userName);
                        user.setPassword("randomWords");
                        service.saveUser(user);
                    });
            Stream.of("Utilisateur", "Admin")
                    .forEach(roleName -> {
                        Role role = new Role();
                        role.setRoleName(roleName);
                        service.saveRole(role);
                    });

            Patient patient = service.ChercherPatientParNom("Latifa");
            Medecin medecin = service.ChercherMedecinParNom("Reda");
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatusRdv(Math.random()>0.5 ? StatusRdv.PENDING : StatusRdv.CANCELED);
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            service.saveRendezVous(rendezVous);
            RendezVous rendezVous1 = service.ChercherRendezVousParId(1L);
            Consultation consultation = new Consultation();
            consultation.setDate(new Date());
            consultation.setRapport("Rapport");
            consultation.setRendezVous(rendezVous1);
            service.saveConsultation(consultation);
            service.AjouterRoleToUser("Tejda", "Utilisateur");

        };
    }
}
