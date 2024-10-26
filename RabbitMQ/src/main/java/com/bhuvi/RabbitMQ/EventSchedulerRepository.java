package com.bhuvi.RabbitMQ;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSchedulerRepository extends JpaRepository<EventScheduler, Integer> {

	@Query(value = "SELECT effective_date_utc FROM bhuvi.gprs_event_scheduler where  effective_date_utc > ?1 and published =false GROUP BY effective_date_utc ORDER BY effective_date_utc", nativeQuery = true)
	List<Object[]> findScheduledTimes(Timestamp dateTime);

	@Query("SELECT s from EventScheduler s  WHERE s.effectiveDateUtc = :scheduledTime and published =false and s.effectiveDateUtc > :currentDateTime")
	public List<EventScheduler> findByScheduledTime(Timestamp scheduledTime, Timestamp currentDateTime);
}
