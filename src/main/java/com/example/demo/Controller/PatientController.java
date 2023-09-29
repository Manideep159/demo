package com.example.demo.Controller;

import com.example.demo.Entity.Patient;
import com.example.demo.Service.PatientService;
import com.example.demo.Service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/Patient")
public class PatientController {
    @Autowired
    PatientService service;

    @Autowired
    ReportService reportService;

    @PostMapping("/register")
    public Patient save(@RequestBody Patient patient){
        return service.save(patient);
    }
    @PostMapping("/registerAll")
    public List<Patient> saveAll(@RequestBody List<Patient> patients) {
        return service.saveAll(patients);
    }

    @GetMapping("/PatientId/{tokenId}")
    public Patient findByTokenId(@PathVariable int tokenId){
        return service.getByTokenId(tokenId);
    }

    @GetMapping("/report/{format}")
    public String getReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }

    @GetMapping("/Patients")
    public List<Patient> findAll(){
        return service.getAll();
    }
    @GetMapping("/PatientName/{nameOfPatient}")
    public ResponseEntity<Patient> findByNameOfPatient(@PathVariable String nameOfPatient){
        return service.getByNameOfPatient(nameOfPatient);
    }
    @GetMapping("/PatientNature/{nature}")
    public Patient findByNatureOPatient(@PathVariable String nature){
        return service.getByNatureOfPatient(nature);
    }
    @PutMapping("/{tokenId}")
    public Patient updatePatient(@PathVariable int tokenId, @RequestBody Patient updatedPatient ){
        return service.updatePatient(tokenId, updatedPatient);
    }
    @DeleteMapping("/{tokenId}")
    public ResponseEntity<String> deletePatient(@PathVariable int tokenId){
        return   service.deletePatient(tokenId);

    }

}
