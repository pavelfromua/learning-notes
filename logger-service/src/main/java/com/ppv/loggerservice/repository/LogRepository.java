package com.ppv.loggerservice.repository;

import com.ppv.loggerservice.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
