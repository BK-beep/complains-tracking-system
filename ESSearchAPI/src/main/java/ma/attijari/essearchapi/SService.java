package ma.attijari.essearchapi;

import lombok.RequiredArgsConstructor;
import ma.attijari.essearchapi.entities.Complaint;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SService {

    private final ElasticsearchTemplate elasticsearchTemplate;

    public Complaint editComplaint(Complaint complaint) {
        return elasticsearchTemplate.save(complaint);
    }
    public List<Complaint> searchAllFields(String searchTerm) {
        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(q -> q
                        .multiMatch(m -> m
                                .query(searchTerm)
                                .fields("complaintId", "from.name", "from.email", "from.phone", "status", "source")
                        )
                )
                .build();

        SearchHits<Complaint> searchHits = elasticsearchTemplate.search(searchQuery, Complaint.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public SearchPage<Complaint> find(String complaintId, String fromName, String fromEmail, String fromPhone,
                                      Date madeAt, String status, String source, int page, int size) {
        Criteria criteria = new Criteria();
        if (complaintId != null && !complaintId.isEmpty()) {
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
        if (madeAt != null) {
            criteria.and(new Criteria("madeAt").is(madeAt));
        }
        if (status != null && !status.isEmpty()) {
            criteria.and(new Criteria("status").is(status));
        }
        if (source != null && !source.isEmpty()) {
            criteria.and(new Criteria("source").is(source));
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        CriteriaQuery query = new CriteriaQuery(criteria).setPageable(pageRequest);
        SearchHits<Complaint> searchHits = elasticsearchTemplate.search(query, Complaint.class);
        return SearchHitSupport.searchPageFor(searchHits, pageRequest);
    }

    public SearchPage<Complaint> searchAllFieldsWithNgram(String searchTerm,int page, int size) {
        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(q -> q
                        .multiMatch(m -> m
                                .query(searchTerm)
                                .fields("complaintId", "from.name", "from.email", "from.phone", "status", "source")
                                .analyzer("ngram_analyzer") // Use the n-gram analyzer
                        )
                )
                .withPageable(PageRequest.of(page, size))  // Add pagination here
                .build();

        SearchHits<Complaint> searchHits = elasticsearchTemplate.search(searchQuery, Complaint.class);
        return SearchHitSupport.searchPageFor(searchHits, PageRequest.of(page, size));

    }
}
