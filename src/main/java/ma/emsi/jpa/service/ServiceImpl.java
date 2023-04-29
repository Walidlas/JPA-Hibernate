package ma.emsi.jpa.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.jpa.entities.*;
import ma.emsi.jpa.repositories.*;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ServiceImpl implements IService{
    PatientRepository patientRepository;
    MedecinRepository medecinRepository;
    RendezVousRepository rendezVousRepository;
    ConsultationRepository consultationRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Patient ChercherPatientParNom(String nom) {
        return patientRepository.findByNom(nom);
    }

    @Override
    public Medecin ChercherMedecinParNom(String nom) {
        return medecinRepository.findByNom(nom);
    }

    @Override
    public RendezVous ChercherRendezVousParId(Long id) {
        return rendezVousRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User ChercherUserParUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role ChercherRoleParRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void AjouterRoleToUser(String userName, String roleName) {
        User user=userRepository.findByUserName(userName);
        Role role=roleRepository.findByRoleName(roleName);

        user.getRoles().add(role);
        role.getUsers().add(user);
    }
}