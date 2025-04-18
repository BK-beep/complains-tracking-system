package ma.attijari.kafkacomplaintsproducer.controllers;

import lombok.RequiredArgsConstructor;
import ma.attijari.kafkacomplaintsproducer.models.Complaint;
import ma.attijari.kafkacomplaintsproducer.services.ComplainPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/complaintproducer")
public class Complaints {
    private final ComplainPublisher service;
    @PostMapping
    public ResponseEntity<String> sendComplain(
            @RequestBody Complaint complaint
    ){
        service.send(complaint);
        return ResponseEntity.ok("published");
    }
}
