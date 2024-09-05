package ma.attijari.essearchapi;

import lombok.RequiredArgsConstructor;
import ma.attijari.essearchapi.entities.Complaint;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/complaints/_search")
public class API {

    private final SService service;

    @PostMapping
    public ResponseEntity<Complaint> editComplaint(@RequestBody Complaint complaint) {
        return ResponseEntity.ok(service.editComplaint(complaint));
    }

    @GetMapping("/keyword")
    public ResponseEntity<Map<String, Object>> searchAllFields(@RequestParam String searchTerm) {
        return ResponseEntity.ok(Map.of("complaints", service.searchAllFields(searchTerm)));
    }
    @GetMapping()
    public ResponseEntity<Map<String, Object>> searchAllFieldsPre(@RequestParam String searchTerm,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "5") int size) {
        SearchPage<Complaint> complaints = service.searchAllFieldsWithNgram(
                searchTerm,
                page,
                size
        );
        Map<String, Object> response = new HashMap<>();
        response.put("complaints", complaints);
        response.put("currentPage", complaints.getNumber());
        response.put("totalItems", complaints.getTotalElements());
        response.put("totalPages", complaints.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find")
    public ResponseEntity<Map<String,Object>> find(
            @RequestParam(required = false) String complaintId,
            @RequestParam(required = false) String fromName,
            @RequestParam(required = false) String fromEmail,
            @RequestParam(required = false) String fromPhone,
            @RequestParam(required = false) Date madeAt,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String source,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        SearchPage<Complaint> complaints = service.find(
                        complaintId,
                        fromName,
                        fromEmail,
                        fromPhone,
                        madeAt,
                        status,
                        source,
                        page,
                        size
                );
        Map<String, Object> response = new HashMap<>();
        response.put("complaints", complaints);
        response.put("currentPage", complaints.getNumber());
        response.put("totalItems", complaints.getTotalElements());
        response.put("totalPages", complaints.getTotalPages());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> find(
            @RequestParam(required = false) String complaintId,
            @RequestParam(required = false) String fromName,
            @RequestParam(required = false) String fromEmail,
            @RequestParam(required = false) String fromPhone,
            @RequestParam(required = false) Date madeAt,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String source
    ) {
        List<Complaint> complaints = service.all(
                complaintId,
                fromName,
                fromEmail,
                fromPhone,
                madeAt,
                status,
                source
        );
        Map<String, Object> response = new HashMap<>();
        response.put("complaints", complaints);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Complaint> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable String id) {
        Map<String,String> deleted=new HashMap<>();
        System.out.println("delete"+id);
        service.delete(id);
        deleted.put("deleted",id);
        return new ResponseEntity<>(deleted,HttpStatus.OK);
    }
}