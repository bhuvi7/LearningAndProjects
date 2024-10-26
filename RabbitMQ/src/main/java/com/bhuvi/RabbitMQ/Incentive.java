package com.bhuvi.RabbitMQ;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "GPRS_EVENT_INCENTIVE")
public class Incentive {
		@Id
	    @Column(name = "CATALOG_ID")
	    private String catalogId;

	    @Column(name = "COUNTRY_CODE")
	    private String countryCode;

	    @Column(name = "MODEL_YEAR")
	    private Integer modelYear;

	    @Column(name = "MID_MODEL_YEAR")
	    private String midModelYear;

	    @Column(name = "VEHICLE_LINE_CODE")
	    private String vehicleLineCode;

	    @Column(name = "VEHICLE_GROUP")
	    private String vehicleGroup;

	    @Column(name = "TARGET_TYPE")
	    private String targetType;

	    @Column(name = "TARGET_MARKET")
	    private String targetMarket;

	    @Column(name = "TIME_STAMP")
	    private Timestamp timeStamp;

	    @Column(name = "INCENTIVE_TYPE")
	    private String incentiveType;

	    @Column(name = "PRODUCT_NAME")
	    private String productName;

	    @Column(name = "NAMEPLATE_ID")
	    private String nameplateId;

	    @Column(name = "CHANGED_TIME_STAMP")
	    private Timestamp changedTimeStamp;
}
