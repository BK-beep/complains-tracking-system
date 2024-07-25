package ma.attijari.kafkacomplainsproducer.controllers;

import lombok.RequiredArgsConstructor;
import ma.attijari.kafkacomplainsproducer.models.Complaint;
import ma.attijari.kafkacomplainsproducer.services.ComplainPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/complainproducer")
public class Complaints {
    private final ComplainPublisher service;
    @PostMapping
    public ResponseEntity<Void> sendComplain(
            @RequestBody Complaint complaint
    ){
        service.send(complaint);
        return ResponseEntity.accepted().build();
    }
}
