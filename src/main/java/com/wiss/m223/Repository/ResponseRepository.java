package com.wiss.m223.Repository;

import com.wiss.m223.Model.Responses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Responses, Long> {

}
