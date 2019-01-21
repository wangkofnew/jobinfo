package com.wjh.job.web.db.mapper;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  jobinfo
 * @author wjh 2019-01-19
 */
@Repository
public interface JobinfoRepository extends JpaRepository<JobinfoDO,Integer> {


    @Query(nativeQuery = true, value = "SELECT * FROM jobinfo WHERE company_id = :company_id")
    List<JobinfoDO> findByCompanyId(@Param("company_id") String company_id);
}

