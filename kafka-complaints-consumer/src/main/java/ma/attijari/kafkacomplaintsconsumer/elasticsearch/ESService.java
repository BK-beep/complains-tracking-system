package ma.attijari.kafkacomplaintsconsumer.elasticsearch;

import lombok.RequiredArgsConstructor;
import ma.attijari.kafkacomplaintsconsumer.models.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        Page<Complaint> page = complaintRepository.findAll(Pageable.unpaged());
        return page.getContent();
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



}
