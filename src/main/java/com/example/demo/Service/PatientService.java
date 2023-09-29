package com.example.demo.Service;

import com.example.demo.Entity.Patient;
import com.example.demo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private  final EntityManager entityManager;
    @Autowired
    PatientRepository repository;

    public PatientService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Patient save(Patient patient){
      return  repository.save(patient);

    }
    public List<Patient> saveAll(List<Patient> patients){
        return repository.saveAll(patients);
    }
    public List<Patient> getAll(){
        return repository.findAll();
    }

   public Patient getByTokenId(int tokenId){
       Optional<Patient> optionalPatient = Optional.ofNullable(repository.findByTokenId(tokenId));
//       if (optionalPatient.isPresent()) {
//           // Return the product if found
//           return ResponseEntity.ok(optionalPatient.get());
//       } else {
//           // Return 404 Not Found if the product is not found
//           return ResponseEntity.notFound().build();
//       }

//       Optional<Patient> patientOptional = Optional.ofNullable(repository.findByTokenId(tokenId));
       return optionalPatient.orElseThrow(() -> new ResourceNotFoundException("patient not found with id:" + tokenId));

//       return repository.findByTokenId(tokenId);
   }

   public ResponseEntity<Patient> getByNameOfPatient(String nameOfPatient){
       Optional<Patient> optionalPatient = Optional.ofNullable(repository.findByNameOfPatient(nameOfPatient));
       if (optionalPatient.isPresent()) {
           // Return the product if found
           return ResponseEntity.ok(optionalPatient.get());
       } else {
           // Return 404 Not Found if the product is not found
           return ResponseEntity.notFound().build();
       }
//        return repository.findByNameOfPatient(nameOfPatient);
   }

   public Patient getByNatureOfPatient(String nature){
       return repository.findByNatureOPatient(nature);
   }

   public Patient updatePatient(int tokenId, Patient updatePatient){
        Patient patientToUpdate = getByTokenId(tokenId);

        patientToUpdate.setNameOfPatient(updatePatient.getNameOfPatient());
        patientToUpdate.setAgeOfPatient(updatePatient.getAgeOfPatient());
        patientToUpdate.setPlaceOfPatient(updatePatient.getPlaceOfPatient());
        patientToUpdate.setNatureOPatient(updatePatient.getNatureOPatient());
        patientToUpdate.setWeightOfPatient(updatePatient.getWeightOfPatient());
        patientToUpdate.setPhoneNumber(updatePatient.getPhoneNumber());
        patientToUpdate.setBloodPressure(updatePatient.getBloodPressure());
        return repository.save(patientToUpdate);
   }
    @Transactional
    public ResponseEntity<String> deletePatient(@PathVariable int  tokenId) {
        if (repository.existsById(tokenId)) {
            repository.deleteById(tokenId);
            return ResponseEntity.ok("Product with ID " + tokenId + " has been deleted.");
        } else {
            return ResponseEntity.badRequest().body("Product with ID " + tokenId + " was not found in the database.");
        }
    }
}
