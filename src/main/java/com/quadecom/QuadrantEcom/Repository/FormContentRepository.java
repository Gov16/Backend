package com.quadecom.QuadrantEcom.Repository;

import com.quadecom.QuadrantEcom.Entities.FormContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FormContentRepository extends JpaRepository<FormContentEntity,String> {

}
