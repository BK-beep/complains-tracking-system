package ma.attijari.essearchapi;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ma.attijari.essearchapi.entities.Complaint;
import ma.attijari.essearchapi.entities.ComplaintSource;
import ma.attijari.essearchapi.entities.ComplaintStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/complaints/_search")
public class API {
    
    private final SService service;
    @GetMapping
    public ResponseEntity<List<Complaint>> find(
            @RequestParam(required = false) String complaintId,
            @RequestParam(required = false) String fromName,
            @RequestParam(required = false) String fromEmail,
            @RequestParam(required = false) String fromPhone,
            @RequestParam(required = false) Date madeAt,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String source
    ){
        return ResponseEntity.ok(service.find(
                complaintId,
                fromName,
                fromEmail,
                fromPhone,
                madeAt,
                status,
                source));
    }
}
