package ma.attijari.kafkacomplaintsconsumer.elasticsearch;

import lombok.RequiredArgsConstructor;
import ma.attijari.kafkacomplaintsconsumer.models.Complaint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ESService {
    private final ESRepo complaintRepository;

    public Complaint createComplaint(Complaint complaint) {
        if (complaint.getComplaintId() == null) {
            String id=UUID.randomUUID().toString();
            complaint.setComplaintId(id);
            System.out.println(id);

        }
        return complaintRepository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        complaintRepository.findAll();
        return (List<Complaint>) complaintRepository.findAll();
    }

    public Complaint getComplaintById(String id) {
        return complaintRepository.findById(id).orElse(new Complaint());
    }

    public Complaint updateComplaint(String id, Complaint complaint) {
        return complaintRepository.findById(id)
                .map(existingComplaint -> {
                    // Update the fields you want to be mutable
                    existingComplaint.setDescription(complaint.getDescription());
                    existingComplaint.setStatus(complaint.getStatus());
                    // Add more fields as necessary
                    return complaintRepository.save(existingComplaint);
                }).orElse(null);
    }

    public boolean deleteComplaint(String id) {
        return complaintRepository.findById(id)
                .map(complaint -> {
                    complaintRepository.delete(complaint);
                    return true;
                })
                .orElse(false);
    }

    public List<Complaint> searchComplaints(String keyword) {
        // This is a simple implementation. You might want to use more sophisticated
        // search capabilities depending on your requirements and database
        return (List<Complaint>) complaintRepository.findAll();
    }

}
