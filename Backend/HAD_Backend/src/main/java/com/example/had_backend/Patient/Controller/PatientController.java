package com.example.had_backend.Patient.Controller;

import com.example.had_backend.Email.EmailService;
import com.example.had_backend.Model.LoginDTO;
import com.example.had_backend.Model.LoginMessage;
import com.example.had_backend.Patient.Entity.Patient;
import com.example.had_backend.Patient.Entity.PatientL;
import com.example.had_backend.Patient.Model.PatientChangePasswordDTO;
import com.example.had_backend.Patient.Model.RegisterDTO;
import com.example.had_backend.Patient.Service.PatientService;
import com.example.had_backend.WebSecConfig.UserAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserAuthProvider userAuthProvider;

    @Autowired
    private EmailService emailService;

    @CrossOrigin
    @PostMapping("/patient/login")
    public ResponseEntity<LoginMessage> login(@RequestBody @Validated LoginDTO login) {
        PatientL patientL = patientService.authenticate(login);
        LoginMessage message = new LoginMessage();
        if(patientL.getPatientId() != null){
            message.setMessage("Login Successful");
            message.setToken(userAuthProvider.createToken(patientL.getUserName()));
        }else{
            message.setMessage("Login failed, Check username/password");
        }
        return ResponseEntity.ok(message);
    }

    @CrossOrigin
    @PostMapping("/patient/register")
    public ResponseEntity<LoginMessage> register(@RequestBody @Validated RegisterDTO register) {
        LoginMessage loginMessage = patientService.registerPatient(register);
        if(!loginMessage.getMessage().equals("User is already registered")){
            emailService.sendSimpleMessage(
                    register.getEmail(),
                    "Registration in Kavach portal was successful",
                    "Username: "+register.getUserName()+ "\n"+"Password: "+register.getPassword());
        }
        return ResponseEntity.ok(loginMessage);

    }

    @CrossOrigin
    @PostMapping("/patient/getProfileDetails")
    public ResponseEntity<Patient> getProfileDetails(@RequestBody @Validated Patient patient3) {
        Patient patient4 = patientService.getProfile(patient3);
        return ResponseEntity.ok(patient4);
    }

    @CrossOrigin
    @PostMapping("/patient/changePassword")
    public ResponseEntity<LoginMessage> changePassword(@RequestBody @Validated PatientChangePasswordDTO patientChangePasswordDTO ) {
        LoginMessage loginMessage1 = patientService.changePassword(patientChangePasswordDTO);
        if(loginMessage1.getMessage().equals("Password updated successfully")){
            emailService.sendSimpleMessage(
                    patientChangePasswordDTO.getEmail(),
                    "Password has been changed successfully",
                    "Username: "+patientChangePasswordDTO.getUserName()+ "\n"+"Password: "+patientChangePasswordDTO.getNewPassword());
        }
        return ResponseEntity.ok(loginMessage1);
    }

//    @CrossOrigin
//    @GetMapping ("/patient/getListOfCases")
//
//    @CrossOrigin
//    @PostMapping("/patient/getSearchResult")
//
//    @CrossOrigin
//    @PostMapping("/patientReports/AssignNewDoctor")
//
//    @CrossOrigin
//    @PostMapping("/patientReports/AssignNewLab")



}
