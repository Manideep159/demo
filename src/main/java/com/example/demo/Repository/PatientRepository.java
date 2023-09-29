package com.example.demo.Repository;

import com.example.demo.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {


    Patient findByNameOfPatient(String nameOfPatient);

    Patient findByNatureOPatient(String nature);

    Patient findByTokenId(int tokenId);

    Patient deleteByTokenId(int tokenId);
}
