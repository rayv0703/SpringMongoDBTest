package com.broada.one.data.repo;

import com.broada.one.data.domain.EmpInf;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpRepository extends MongoRepository<EmpInf,Long> {

}
