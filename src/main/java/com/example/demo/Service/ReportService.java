package com.example.demo.Service;

import com.example.demo.Entity.Patient;
import com.example.demo.Repository.PatientRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private PatientRepository repository;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path="C:\\Users\\Ramesh Kumar\\Desktop\\jasper";
        List<Patient> patients = repository.findAll();
        File file = ResourceUtils.getFile("classpath:HospitalStructure.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(patients);
        Map<String,Object> parameters= new HashMap<>();
        parameters.put("created by","ARKA TS");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\patients.html");
        }

        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\patients.pdf");
        }
        return "report generated in path:"+path;
    }
}


