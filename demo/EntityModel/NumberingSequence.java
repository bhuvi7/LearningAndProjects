package com.example.demo.EntityModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="m_numbering_sequence")
public class NumberingSequence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer constructionWorkingSequence;
	private Integer bankReceiptingSequence;
	private Integer creditNoteSequence;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	public NumberingSequence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NumberingSequence(Integer id, Integer constructionWorkingSequence, Integer bankReceiptingSequence, Integer creditNoteSequence) {
		super();
		this.id = id;
		this.constructionWorkingSequence = constructionWorkingSequence;
		this.bankReceiptingSequence = bankReceiptingSequence;
		this.creditNoteSequence = creditNoteSequence;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConstructionWorkingSequence() {
		return constructionWorkingSequence;
	}

	public void setConstructionWorkingSequence(Integer constructionWorkingSequence) {
		this.constructionWorkingSequence = constructionWorkingSequence;
	}

	public Integer getBankReceiptingSequence() {
		return bankReceiptingSequence;
	}
	
	public Integer getCreditNoteSequence() {
		return creditNoteSequence;
	}

	public void setCreditNoteSequence(Integer creditNoteSequence) {
		this.creditNoteSequence = creditNoteSequence;
	}

	public void setBankReceiptingSequence(Integer bankReceiptingSequence) {
		this.bankReceiptingSequence = bankReceiptingSequence;
	}

	@Override
	public String toString() {
		return "NumberingSequence [id=" + id + ", constructionWorkingSequence=" + constructionWorkingSequence
				+ ", bankReceiptingSequence=" + bankReceiptingSequence + "]";
	}
	
	
}
