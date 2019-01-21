package com.wjh.job.web.db.mapper;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 *  jobinfo
 * @author wjh 2019-01-19
 */
@Entity
@Data
@Table(name="jobinfo")
public class JobinfoDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    /**
     * id
     */
    private Long id;

    /**
     * company_id
     */
    private String companyId;

    /**
     * company_name
     */
    private String companyName;

    /**
     * job_title
     */
    private String jobTitle;

    /**
     * job_url
     */
    private String jobUrl;

    /**
     * create_date
     */
    private String createDate;

    /**
     * create_time
     */
    private String createTime;


}
