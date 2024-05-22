package com.galdino.rgvpacientes.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.galdino.rgvpacientes.dto.patient.PatientFilter;
import com.galdino.rgvpacientes.enums.Status;
import com.galdino.rgvpacientes.model.Patient;

public class PatientRepositoryImpl implements PatientRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Patient> getAllWithPaginate(PatientFilter patientFilter, Pageable pageable) {
        Map<String, Object> parameters = new HashMap<>();
        StringBuilder sql = new StringBuilder("select ");
        sql.append(
                "p.cpf, p.name, p.phone, p.address_name, p.address_number, p.address_complement, p.address_district, p.status ");
        sql.append("from patients p where p.status = :status ");

        addFilters(patientFilter, parameters, sql);

        sql.append("order by p.name ");
        sql.append("limit :limit offset :offset ");

        Query createNativeQuery = entityManager.createNativeQuery(sql.toString(), Patient.class);

        createNativeQuery.setParameter("limit", pageable.getPageSize());
        createNativeQuery.setParameter("offset", pageable.getOffset());
        createNativeQuery.setParameter("status", Status.ACTIVE.getValue());

        parameters.keySet().forEach(key -> {
            if (parameters.containsKey(key)) {
                createNativeQuery.setParameter(key, parameters.get(key));
            }
        });

        @SuppressWarnings("unchecked")
        List<Patient> patientList = createNativeQuery.getResultList();
        return PageableExecutionUtils.getPage(patientList, pageable,
                () -> getCountForQuery(Patient.class));
    }

    private void addFilters(PatientFilter patientFilter, Map<String, Object> parameters, StringBuilder sql) {
        if (StringUtils.hasText(patientFilter.getCpf())) {
            sql.append("and p.cpf = :cpf ");
            parameters.put("cpf", patientFilter.getCpf());
        }
        if (StringUtils.hasText(patientFilter.getName())) {
            sql.append("and p.name like :name ");
            parameters.put("name", patientFilter.getName().concat("%"));
        }
    }

    private Long getCountForQuery(Class<?> t) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder
                .createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(
                countQuery.from(t)));
        return entityManager.createQuery(countQuery)
                .getSingleResult();
    }

}
