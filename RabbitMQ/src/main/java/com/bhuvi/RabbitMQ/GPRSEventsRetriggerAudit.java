package com.bhuvi.RabbitMQ;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "GPRS_EVENT_RETRIGGER_AUDIT")
public class GPRSEventsRetriggerAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GUID", updatable = false, nullable = false)
	private Integer guid; //primary key
	
	@Column(name = "MAPPING_GUID")
	private String mappingGuid;
	
	@Column(name = "CATALOG_ID")
	private String catalogId; 
	
	@Column(name = "PRODUCT")
	private String product;
	
	@Column(name = "SERVICE")
	private String service;
	
	@Column(name = "MARKET")
	private String market;
	
	@Column(name = "CONSUMER_LIST")
	private String consumerList;
	
	@Column(name = "NOTIFICATION_TYPE")
	private String notificationType;
	
	@Column(name = "RETRIGGERED_BY")
	private String retriggeredBy;
	
	@Column(name = "RETRIGGERED_DATE")
	private String retriggeredDate;
	
	@Column(name = "STATUS")
	private String status;
	
	
	
}
