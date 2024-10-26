package com.bhuvi.RabbitMQ;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
@Setter
@Table(name = "GPRS_EVENT_SCHEDULER")
public class EventScheduler {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Integer id; //primary key
	
	private String catalogId; //primary key

	@Column(name = "MAPPING_GUID")
	private String guid;
	
	@Column(name = "GPRICE_ZONED_EFFECTIVE_DATE")
	private Timestamp GpriceZonedfromDate;
	
	@Column(name = "EFFECTIVE_DATE_UTC")
	private Timestamp effectiveDateUtc; //scheduledTime 
	
	@Column(name = "PRICE_LEVEL")
	private String priceLevel;

	@CreationTimestamp
	@Column(name = "EVENT_RECEIVED_DATE")
	private Timestamp eventReceivedDate;
	
	@Column(name = "EVENT_UPDATED_DATE")
	private Timestamp eventUpdatedDate;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name="SCHEDULED")
	private Boolean scheduled;
	
	@Column(name="PUBLISHED")
	private Boolean published;
	
	@Column(name = "USER")
	private String user;
	
}
