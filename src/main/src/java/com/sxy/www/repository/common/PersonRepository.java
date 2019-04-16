package com.sxy.www.repository.common;

import com.sxy.www.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by xiangyusun on 2019/4/2.
 */
public interface PersonRepository extends CrudRepository<Person, String> {
}
