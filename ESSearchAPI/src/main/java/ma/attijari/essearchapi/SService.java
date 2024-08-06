package ma.attijari.essearchapi;

import lombok.RequiredArgsConstructor;
import ma.attijari.essearchapi.entities.Complaint;
import ma.attijari.essearchapi.entities.ComplaintSource;
import ma.attijari.essearchapi.entities.ComplaintStatus;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class SService {
    //https://docs.spring.io/spring-data/elasticsearch/reference/elasticsearch/template.html#elasticsearch.operations.criteriaquery

    private final ElasticsearchTemplate elasticsearchTemplate;

    public List<Complaint> find(String complaintId, String fromName, String fromEmail, String fromPhone, Date madeAt, String status, String source) {
        Criteria criteria=new Criteria();
        if (complaintId!=null && !complaintId.isEmpty()){
            criteria.and(new Criteria("complaintId").is(complaintId));
        }
        if (fromName != null && !fromName.isEmpty()) {
            criteria.and(new Criteria("from.name").contains(fromName));
        }
        if (fromEmail != null && !fromEmail.isEmpty()) {
            criteria.and(new Criteria("from.email").contains(fromEmail));
        }
        if (fromPhone != null && !fromPhone.isEmpty()) {
            criteria.and(new Criteria("from.phone").contains(fromPhone));
        }
        if (fromPhone != null && !fromPhone.isEmpty()) {
            criteria.and(new Criteria("madeAt").is(madeAt));
        }
        if (status != null && !status.isEmpty()) {
            criteria.and(new Criteria("status").is(status));
        }
        if (source != null && !source.isEmpty()) {
            criteria.and(new Criteria("source").is(source));
        }

        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<Complaint> searchHits = elasticsearchTemplate.search(query, Complaint.class);

        return searchHits.stream().map(hit -> hit.getContent()).toList();
    }
}
