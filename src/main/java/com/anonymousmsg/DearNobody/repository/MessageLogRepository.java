package com.anonymousmsg.DearNobody.repository;

import com.anonymousmsg.DearNobody.model.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageLogRepository extends JpaRepository<MessageLog, Long> {
}