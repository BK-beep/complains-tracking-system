package ma.attijari.kafkacomplaintsconsumer.elasticsearch;

import lombok.RequiredArgsConstructor;
import ma.attijari.kafkacomplaintsconsumer.models.Complaint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comp")
public class ESController {
    
    private final ESService service;

    @PostMapping
    public ResponseEntity<Complaint> createComplaint(@RequestBody Complaint complaint) {
        Complaint createdComplaint = service.createComplaint(complaint);
        return new ResponseEntity<>(createdComplaint, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        List<Complaint> complaints = service.getAllComplaints();
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable String id) {
        Complaint complaint = service.getComplaintById(id);
        return ResponseEntity.ok(complaint);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Complaint> updateComplaint(@PathVariable String id, @RequestBody Complaint complaint) {
        Complaint updatedComplaint = service.updateComplaint(id, complaint);
        return ResponseEntity.ok(updatedComplaint);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable String id) {
        boolean deleted = service.deleteComplaint(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

/*    @GetMapping("/search")
    public ResponseEntity<List<Complaint>> searchComplaints(@RequestParam String keyword) {
        List<Complaint> complaints = service.searchComplaints(keyword);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }
*/
}
