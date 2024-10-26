package com.bhuvi.RabbitMQ;

import java.sql.Timestamp;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "GPRS_EVENT_PARENT")
public class ParentScheduler {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GUID", updatable = false, nullable = false)
	private String guid;

	@Column(name = "CATALOG_ID")
    private String catalogId;

	@Column(name = "PRICE_LEVEL")
	private String priceLevel;

	@Column(name = "PRICE_SET_ID")
	private String priceSetId;

	@Column(name = "EFFECTIVE_FROM_DATE_UTC")
    private Timestamp effectiveFromDateUtc;

	@Column(name = "EFFECTIVE_TO_DATE_UTC")
	private Timestamp effectiveToDateUtc;

	@Column(name = "EVENT_RECEIVED_DATE")
	private Timestamp eventReceivedDate;

	@Column(name = "EVENT_UPDATED_DATE")
	private Timestamp eventUpdatedDate;

	@Column(name = "MAPPING_GUID")
    private String mappingGuid;

	@Column(name = "PUBLISHED")
	private boolean published;

	@Column(name = "SCHEDULED")
	private boolean scheduled;

	@Column(name = "status")
	private String status;

	@Column(name = "CREATED_BY")
	private String createdBy;
}
