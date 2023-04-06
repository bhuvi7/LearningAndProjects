package com.example.demo.EntityModel;

public class InvoiceCounter {
	
	Integer currMonTotal;
	Integer currMonEquipCount;
	Integer currMonSd1NotCreated;
	Integer currMonSd1InProgress;
	Integer currMonSd1Approved;
	Integer currMonSd1NotCrEquipCount;
	Integer currMonSd1ProgEquipCount;
	Integer currMonSd1ApprEquipCount;
	Integer currMonSd2NotCreated;
	Integer currMonSd2InProgress;
	Integer currMonSd2Approved;
	Integer currMonSd2NotCrEquipCount;
	Integer currMonSd2ProgEquipCount;
	Integer currMonSd2ApprEquipCount;
	Integer currMonSd3NotCreated;
	Integer currMonSd3InProgress;
	Integer currMonSd3Approved;
	Integer currMonSd3NotCrEquipCount;
	Integer currMonSd3ProgEquipCount;
	Integer currMonSd3ApprEquipCount;
	Integer currMonSd4NotCreated;
	Integer currMonSd4InProgress;
	Integer currMonSd4Approved;
	Integer currMonSd4NotCrEquipCount;
	Integer currMonSd4ProgEquipCount;
	Integer currMonSd4ApprEquipCount;
	Integer currMonInvNotCreated;
	Integer currMonInvInProgress;
	Integer currMonInvApproved;
	Integer currMonInvNotCrEquipCount;
	Integer currMonInvProgEquipCount;
	Integer currMonInvApprEquipCount;
	
	Integer currMonTotal1;
	Integer currMonEquipCount1;
	Integer currMonTotal2;
	Integer currMonEquipCount2;
	
	Integer oldMonTotal1;
	Integer oldMonEquipCount1;
	Integer oldMonTotal2;
	Integer oldMonEquipCount2;
	
	Integer oldMonTotal;
	Integer oldMonEquipCount;
	Integer oldMonSd1NotCreated;
	Integer oldMonSd1InProgress;
	Integer oldMonSd1Approved;
	Integer oldMonSd2NotCreated;
	Integer oldMonSd2InProgress;
	Integer oldMonSd2Approved;
	Integer oldMonSd3NotCreated;
	Integer oldMonSd3InProgress;
	Integer oldMonSd3Approved;
	Integer oldMonSd4NotCreated;
	Integer oldMonSd4InProgress;
	Integer oldMonSd4Approved;
	Integer oldMonInvNotCreated;
	Integer oldMonInvInProgress;
	Integer oldMonInvApproved;
	
	Integer oldMonSd1NotCrEquipCount;
	Integer oldMonSd1ProgEquipCount;
	Integer oldMonSd1ApprEquipCount;

	Integer oldMonSd2NotCrEquipCount;
	Integer oldMonSd2ProgEquipCount;
	Integer oldMonSd2ApprEquipCount;

	Integer oldMonSd3NotCrEquipCount;
	Integer oldMonSd3ProgEquipCount;
	Integer oldMonSd3ApprEquipCount;

	Integer oldMonSd4NotCrEquipCount;
	Integer oldMonSd4ProgEquipCount;
	Integer oldMonSd4ApprEquipCount;

	Integer oldMonInvNotCrEquipCount;
	Integer oldMonInvProgEquipCount;
	Integer oldMonInvApprEquipCount;
	
	public InvoiceCounter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceCounter(Integer currMonTotal, Integer currMonEquipCount, Integer currMonSd1NotCreated,
			Integer currMonSd1InProgress, Integer currMonSd1Approved, Integer currMonSd1NotCrEquipCount,
			Integer currMonSd1ProgEquipCount, Integer currMonSd1ApprEquipCount, Integer currMonSd2NotCreated,
			Integer currMonSd2InProgress, Integer currMonSd2Approved, Integer currMonSd2NotCrEquipCount,
			Integer currMonSd2ProgEquipCount, Integer currMonSd2ApprEquipCount, Integer currMonSd3NotCreated,
			Integer currMonSd3InProgress, Integer currMonSd3Approved, Integer currMonSd3NotCrEquipCount,
			Integer currMonSd3ProgEquipCount, Integer currMonSd3ApprEquipCount, Integer currMonSd4NotCreated,
			Integer currMonSd4InProgress, Integer currMonSd4Approved, Integer currMonSd4NotCrEquipCount,
			Integer currMonSd4ProgEquipCount, Integer currMonSd4ApprEquipCount, Integer currMonInvNotCreated,
			Integer currMonInvInProgress, Integer currMonInvApproved, Integer currMonInvNotCrEquipCount,
			Integer currMonInvProgEquipCount, Integer currMonInvApprEquipCount, Integer currMonTotal1,
			Integer currMonEquipCount1, Integer currMonTotal2, Integer currMonEquipCount2, Integer oldMonTotal1,
			Integer oldMonEquipCount1, Integer oldMonTotal2, Integer oldMonEquipCount2, Integer oldMonTotal,
			Integer oldMonEquipCount, Integer oldMonSd1NotCreated, Integer oldMonSd1InProgress,
			Integer oldMonSd1Approved, Integer oldMonSd2NotCreated, Integer oldMonSd2InProgress,
			Integer oldMonSd2Approved, Integer oldMonSd3NotCreated, Integer oldMonSd3InProgress,
			Integer oldMonSd3Approved, Integer oldMonSd4NotCreated, Integer oldMonSd4InProgress,
			Integer oldMonSd4Approved, Integer oldMonInvNotCreated, Integer oldMonInvInProgress,
			Integer oldMonInvApproved, Integer oldMonSd1NotCrEquipCount, Integer oldMonSd1ProgEquipCount,
			Integer oldMonSd1ApprEquipCount, Integer oldMonSd2NotCrEquipCount, Integer oldMonSd2ProgEquipCount,
			Integer oldMonSd2ApprEquipCount, Integer oldMonSd3NotCrEquipCount, Integer oldMonSd3ProgEquipCount,
			Integer oldMonSd3ApprEquipCount, Integer oldMonSd4NotCrEquipCount, Integer oldMonSd4ProgEquipCount,
			Integer oldMonSd4ApprEquipCount, Integer oldMonInvNotCrEquipCount, Integer oldMonInvProgEquipCount,
			Integer oldMonInvApprEquipCount) {
		super();
		this.currMonTotal = currMonTotal;
		this.currMonEquipCount = currMonEquipCount;
		this.currMonSd1NotCreated = currMonSd1NotCreated;
		this.currMonSd1InProgress = currMonSd1InProgress;
		this.currMonSd1Approved = currMonSd1Approved;
		this.currMonSd1NotCrEquipCount = currMonSd1NotCrEquipCount;
		this.currMonSd1ProgEquipCount = currMonSd1ProgEquipCount;
		this.currMonSd1ApprEquipCount = currMonSd1ApprEquipCount;
		this.currMonSd2NotCreated = currMonSd2NotCreated;
		this.currMonSd2InProgress = currMonSd2InProgress;
		this.currMonSd2Approved = currMonSd2Approved;
		this.currMonSd2NotCrEquipCount = currMonSd2NotCrEquipCount;
		this.currMonSd2ProgEquipCount = currMonSd2ProgEquipCount;
		this.currMonSd2ApprEquipCount = currMonSd2ApprEquipCount;
		this.currMonSd3NotCreated = currMonSd3NotCreated;
		this.currMonSd3InProgress = currMonSd3InProgress;
		this.currMonSd3Approved = currMonSd3Approved;
		this.currMonSd3NotCrEquipCount = currMonSd3NotCrEquipCount;
		this.currMonSd3ProgEquipCount = currMonSd3ProgEquipCount;
		this.currMonSd3ApprEquipCount = currMonSd3ApprEquipCount;
		this.currMonSd4NotCreated = currMonSd4NotCreated;
		this.currMonSd4InProgress = currMonSd4InProgress;
		this.currMonSd4Approved = currMonSd4Approved;
		this.currMonSd4NotCrEquipCount = currMonSd4NotCrEquipCount;
		this.currMonSd4ProgEquipCount = currMonSd4ProgEquipCount;
		this.currMonSd4ApprEquipCount = currMonSd4ApprEquipCount;
		this.currMonInvNotCreated = currMonInvNotCreated;
		this.currMonInvInProgress = currMonInvInProgress;
		this.currMonInvApproved = currMonInvApproved;
		this.currMonInvNotCrEquipCount = currMonInvNotCrEquipCount;
		this.currMonInvProgEquipCount = currMonInvProgEquipCount;
		this.currMonInvApprEquipCount = currMonInvApprEquipCount;
		this.currMonTotal1 = currMonTotal1;
		this.currMonEquipCount1 = currMonEquipCount1;
		this.currMonTotal2 = currMonTotal2;
		this.currMonEquipCount2 = currMonEquipCount2;
		this.oldMonTotal1 = oldMonTotal1;
		this.oldMonEquipCount1 = oldMonEquipCount1;
		this.oldMonTotal2 = oldMonTotal2;
		this.oldMonEquipCount2 = oldMonEquipCount2;
		this.oldMonTotal = oldMonTotal;
		this.oldMonEquipCount = oldMonEquipCount;
		this.oldMonSd1NotCreated = oldMonSd1NotCreated;
		this.oldMonSd1InProgress = oldMonSd1InProgress;
		this.oldMonSd1Approved = oldMonSd1Approved;
		this.oldMonSd2NotCreated = oldMonSd2NotCreated;
		this.oldMonSd2InProgress = oldMonSd2InProgress;
		this.oldMonSd2Approved = oldMonSd2Approved;
		this.oldMonSd3NotCreated = oldMonSd3NotCreated;
		this.oldMonSd3InProgress = oldMonSd3InProgress;
		this.oldMonSd3Approved = oldMonSd3Approved;
		this.oldMonSd4NotCreated = oldMonSd4NotCreated;
		this.oldMonSd4InProgress = oldMonSd4InProgress;
		this.oldMonSd4Approved = oldMonSd4Approved;
		this.oldMonInvNotCreated = oldMonInvNotCreated;
		this.oldMonInvInProgress = oldMonInvInProgress;
		this.oldMonInvApproved = oldMonInvApproved;
		this.oldMonSd1NotCrEquipCount = oldMonSd1NotCrEquipCount;
		this.oldMonSd1ProgEquipCount = oldMonSd1ProgEquipCount;
		this.oldMonSd1ApprEquipCount = oldMonSd1ApprEquipCount;
		this.oldMonSd2NotCrEquipCount = oldMonSd2NotCrEquipCount;
		this.oldMonSd2ProgEquipCount = oldMonSd2ProgEquipCount;
		this.oldMonSd2ApprEquipCount = oldMonSd2ApprEquipCount;
		this.oldMonSd3NotCrEquipCount = oldMonSd3NotCrEquipCount;
		this.oldMonSd3ProgEquipCount = oldMonSd3ProgEquipCount;
		this.oldMonSd3ApprEquipCount = oldMonSd3ApprEquipCount;
		this.oldMonSd4NotCrEquipCount = oldMonSd4NotCrEquipCount;
		this.oldMonSd4ProgEquipCount = oldMonSd4ProgEquipCount;
		this.oldMonSd4ApprEquipCount = oldMonSd4ApprEquipCount;
		this.oldMonInvNotCrEquipCount = oldMonInvNotCrEquipCount;
		this.oldMonInvProgEquipCount = oldMonInvProgEquipCount;
		this.oldMonInvApprEquipCount = oldMonInvApprEquipCount;
	}

	public Integer getCurrMonTotal() {
		return currMonTotal;
	}

	public void setCurrMonTotal(Integer currMonTotal) {
		this.currMonTotal = currMonTotal;
	}

	public Integer getCurrMonEquipCount() {
		return currMonEquipCount;
	}

	public void setCurrMonEquipCount(Integer currMonEquipCount) {
		this.currMonEquipCount = currMonEquipCount;
	}

	public Integer getCurrMonSd1NotCreated() {
		return currMonSd1NotCreated;
	}

	public void setCurrMonSd1NotCreated(Integer currMonSd1NotCreated) {
		this.currMonSd1NotCreated = currMonSd1NotCreated;
	}

	public Integer getCurrMonSd1InProgress() {
		return currMonSd1InProgress;
	}

	public void setCurrMonSd1InProgress(Integer currMonSd1InProgress) {
		this.currMonSd1InProgress = currMonSd1InProgress;
	}

	public Integer getCurrMonSd1Approved() {
		return currMonSd1Approved;
	}

	public void setCurrMonSd1Approved(Integer currMonSd1Approved) {
		this.currMonSd1Approved = currMonSd1Approved;
	}

	public Integer getCurrMonSd1NotCrEquipCount() {
		return currMonSd1NotCrEquipCount;
	}

	public void setCurrMonSd1NotCrEquipCount(Integer currMonSd1NotCrEquipCount) {
		this.currMonSd1NotCrEquipCount = currMonSd1NotCrEquipCount;
	}

	public Integer getCurrMonSd1ProgEquipCount() {
		return currMonSd1ProgEquipCount;
	}

	public void setCurrMonSd1ProgEquipCount(Integer currMonSd1ProgEquipCount) {
		this.currMonSd1ProgEquipCount = currMonSd1ProgEquipCount;
	}

	public Integer getCurrMonSd1ApprEquipCount() {
		return currMonSd1ApprEquipCount;
	}

	public void setCurrMonSd1ApprEquipCount(Integer currMonSd1ApprEquipCount) {
		this.currMonSd1ApprEquipCount = currMonSd1ApprEquipCount;
	}

	public Integer getCurrMonSd2NotCreated() {
		return currMonSd2NotCreated;
	}

	public void setCurrMonSd2NotCreated(Integer currMonSd2NotCreated) {
		this.currMonSd2NotCreated = currMonSd2NotCreated;
	}

	public Integer getCurrMonSd2InProgress() {
		return currMonSd2InProgress;
	}

	public void setCurrMonSd2InProgress(Integer currMonSd2InProgress) {
		this.currMonSd2InProgress = currMonSd2InProgress;
	}

	public Integer getCurrMonSd2Approved() {
		return currMonSd2Approved;
	}

	public void setCurrMonSd2Approved(Integer currMonSd2Approved) {
		this.currMonSd2Approved = currMonSd2Approved;
	}

	public Integer getCurrMonSd2NotCrEquipCount() {
		return currMonSd2NotCrEquipCount;
	}

	public void setCurrMonSd2NotCrEquipCount(Integer currMonSd2NotCrEquipCount) {
		this.currMonSd2NotCrEquipCount = currMonSd2NotCrEquipCount;
	}

	public Integer getCurrMonSd2ProgEquipCount() {
		return currMonSd2ProgEquipCount;
	}

	public void setCurrMonSd2ProgEquipCount(Integer currMonSd2ProgEquipCount) {
		this.currMonSd2ProgEquipCount = currMonSd2ProgEquipCount;
	}

	public Integer getCurrMonSd2ApprEquipCount() {
		return currMonSd2ApprEquipCount;
	}

	public void setCurrMonSd2ApprEquipCount(Integer currMonSd2ApprEquipCount) {
		this.currMonSd2ApprEquipCount = currMonSd2ApprEquipCount;
	}

	public Integer getCurrMonSd3NotCreated() {
		return currMonSd3NotCreated;
	}

	public void setCurrMonSd3NotCreated(Integer currMonSd3NotCreated) {
		this.currMonSd3NotCreated = currMonSd3NotCreated;
	}

	public Integer getCurrMonSd3InProgress() {
		return currMonSd3InProgress;
	}

	public void setCurrMonSd3InProgress(Integer currMonSd3InProgress) {
		this.currMonSd3InProgress = currMonSd3InProgress;
	}

	public Integer getCurrMonSd3Approved() {
		return currMonSd3Approved;
	}

	public void setCurrMonSd3Approved(Integer currMonSd3Approved) {
		this.currMonSd3Approved = currMonSd3Approved;
	}

	public Integer getCurrMonSd3NotCrEquipCount() {
		return currMonSd3NotCrEquipCount;
	}

	public void setCurrMonSd3NotCrEquipCount(Integer currMonSd3NotCrEquipCount) {
		this.currMonSd3NotCrEquipCount = currMonSd3NotCrEquipCount;
	}

	public Integer getCurrMonSd3ProgEquipCount() {
		return currMonSd3ProgEquipCount;
	}

	public void setCurrMonSd3ProgEquipCount(Integer currMonSd3ProgEquipCount) {
		this.currMonSd3ProgEquipCount = currMonSd3ProgEquipCount;
	}

	public Integer getCurrMonSd3ApprEquipCount() {
		return currMonSd3ApprEquipCount;
	}

	public void setCurrMonSd3ApprEquipCount(Integer currMonSd3ApprEquipCount) {
		this.currMonSd3ApprEquipCount = currMonSd3ApprEquipCount;
	}

	public Integer getCurrMonSd4NotCreated() {
		return currMonSd4NotCreated;
	}

	public void setCurrMonSd4NotCreated(Integer currMonSd4NotCreated) {
		this.currMonSd4NotCreated = currMonSd4NotCreated;
	}

	public Integer getCurrMonSd4InProgress() {
		return currMonSd4InProgress;
	}

	public void setCurrMonSd4InProgress(Integer currMonSd4InProgress) {
		this.currMonSd4InProgress = currMonSd4InProgress;
	}

	public Integer getCurrMonSd4Approved() {
		return currMonSd4Approved;
	}

	public void setCurrMonSd4Approved(Integer currMonSd4Approved) {
		this.currMonSd4Approved = currMonSd4Approved;
	}

	public Integer getCurrMonSd4NotCrEquipCount() {
		return currMonSd4NotCrEquipCount;
	}

	public void setCurrMonSd4NotCrEquipCount(Integer currMonSd4NotCrEquipCount) {
		this.currMonSd4NotCrEquipCount = currMonSd4NotCrEquipCount;
	}

	public Integer getCurrMonSd4ProgEquipCount() {
		return currMonSd4ProgEquipCount;
	}

	public void setCurrMonSd4ProgEquipCount(Integer currMonSd4ProgEquipCount) {
		this.currMonSd4ProgEquipCount = currMonSd4ProgEquipCount;
	}

	public Integer getCurrMonSd4ApprEquipCount() {
		return currMonSd4ApprEquipCount;
	}

	public void setCurrMonSd4ApprEquipCount(Integer currMonSd4ApprEquipCount) {
		this.currMonSd4ApprEquipCount = currMonSd4ApprEquipCount;
	}

	public Integer getCurrMonInvNotCreated() {
		return currMonInvNotCreated;
	}

	public void setCurrMonInvNotCreated(Integer currMonInvNotCreated) {
		this.currMonInvNotCreated = currMonInvNotCreated;
	}

	public Integer getCurrMonInvInProgress() {
		return currMonInvInProgress;
	}

	public void setCurrMonInvInProgress(Integer currMonInvInProgress) {
		this.currMonInvInProgress = currMonInvInProgress;
	}

	public Integer getCurrMonInvApproved() {
		return currMonInvApproved;
	}

	public void setCurrMonInvApproved(Integer currMonInvApproved) {
		this.currMonInvApproved = currMonInvApproved;
	}

	public Integer getCurrMonInvNotCrEquipCount() {
		return currMonInvNotCrEquipCount;
	}

	public void setCurrMonInvNotCrEquipCount(Integer currMonInvNotCrEquipCount) {
		this.currMonInvNotCrEquipCount = currMonInvNotCrEquipCount;
	}

	public Integer getCurrMonInvProgEquipCount() {
		return currMonInvProgEquipCount;
	}

	public void setCurrMonInvProgEquipCount(Integer currMonInvProgEquipCount) {
		this.currMonInvProgEquipCount = currMonInvProgEquipCount;
	}

	public Integer getCurrMonInvApprEquipCount() {
		return currMonInvApprEquipCount;
	}

	public void setCurrMonInvApprEquipCount(Integer currMonInvApprEquipCount) {
		this.currMonInvApprEquipCount = currMonInvApprEquipCount;
	}

	public Integer getCurrMonTotal1() {
		return currMonTotal1;
	}

	public void setCurrMonTotal1(Integer currMonTotal1) {
		this.currMonTotal1 = currMonTotal1;
	}

	public Integer getCurrMonEquipCount1() {
		return currMonEquipCount1;
	}

	public void setCurrMonEquipCount1(Integer currMonEquipCount1) {
		this.currMonEquipCount1 = currMonEquipCount1;
	}

	public Integer getCurrMonTotal2() {
		return currMonTotal2;
	}

	public void setCurrMonTotal2(Integer currMonTotal2) {
		this.currMonTotal2 = currMonTotal2;
	}

	public Integer getCurrMonEquipCount2() {
		return currMonEquipCount2;
	}

	public void setCurrMonEquipCount2(Integer currMonEquipCount2) {
		this.currMonEquipCount2 = currMonEquipCount2;
	}

	public Integer getOldMonTotal1() {
		return oldMonTotal1;
	}

	public void setOldMonTotal1(Integer oldMonTotal1) {
		this.oldMonTotal1 = oldMonTotal1;
	}

	public Integer getOldMonEquipCount1() {
		return oldMonEquipCount1;
	}

	public void setOldMonEquipCount1(Integer oldMonEquipCount1) {
		this.oldMonEquipCount1 = oldMonEquipCount1;
	}

	public Integer getOldMonTotal2() {
		return oldMonTotal2;
	}

	public void setOldMonTotal2(Integer oldMonTotal2) {
		this.oldMonTotal2 = oldMonTotal2;
	}

	public Integer getOldMonEquipCount2() {
		return oldMonEquipCount2;
	}

	public void setOldMonEquipCount2(Integer oldMonEquipCount2) {
		this.oldMonEquipCount2 = oldMonEquipCount2;
	}

	public Integer getOldMonTotal() {
		return oldMonTotal;
	}

	public void setOldMonTotal(Integer oldMonTotal) {
		this.oldMonTotal = oldMonTotal;
	}

	public Integer getOldMonEquipCount() {
		return oldMonEquipCount;
	}

	public void setOldMonEquipCount(Integer oldMonEquipCount) {
		this.oldMonEquipCount = oldMonEquipCount;
	}

	public Integer getOldMonSd1NotCreated() {
		return oldMonSd1NotCreated;
	}

	public void setOldMonSd1NotCreated(Integer oldMonSd1NotCreated) {
		this.oldMonSd1NotCreated = oldMonSd1NotCreated;
	}

	public Integer getOldMonSd1InProgress() {
		return oldMonSd1InProgress;
	}

	public void setOldMonSd1InProgress(Integer oldMonSd1InProgress) {
		this.oldMonSd1InProgress = oldMonSd1InProgress;
	}

	public Integer getOldMonSd1Approved() {
		return oldMonSd1Approved;
	}

	public void setOldMonSd1Approved(Integer oldMonSd1Approved) {
		this.oldMonSd1Approved = oldMonSd1Approved;
	}

	public Integer getOldMonSd2NotCreated() {
		return oldMonSd2NotCreated;
	}

	public void setOldMonSd2NotCreated(Integer oldMonSd2NotCreated) {
		this.oldMonSd2NotCreated = oldMonSd2NotCreated;
	}

	public Integer getOldMonSd2InProgress() {
		return oldMonSd2InProgress;
	}

	public void setOldMonSd2InProgress(Integer oldMonSd2InProgress) {
		this.oldMonSd2InProgress = oldMonSd2InProgress;
	}

	public Integer getOldMonSd2Approved() {
		return oldMonSd2Approved;
	}

	public void setOldMonSd2Approved(Integer oldMonSd2Approved) {
		this.oldMonSd2Approved = oldMonSd2Approved;
	}

	public Integer getOldMonSd3NotCreated() {
		return oldMonSd3NotCreated;
	}

	public void setOldMonSd3NotCreated(Integer oldMonSd3NotCreated) {
		this.oldMonSd3NotCreated = oldMonSd3NotCreated;
	}

	public Integer getOldMonSd3InProgress() {
		return oldMonSd3InProgress;
	}

	public void setOldMonSd3InProgress(Integer oldMonSd3InProgress) {
		this.oldMonSd3InProgress = oldMonSd3InProgress;
	}

	public Integer getOldMonSd3Approved() {
		return oldMonSd3Approved;
	}

	public void setOldMonSd3Approved(Integer oldMonSd3Approved) {
		this.oldMonSd3Approved = oldMonSd3Approved;
	}

	public Integer getOldMonSd4NotCreated() {
		return oldMonSd4NotCreated;
	}

	public void setOldMonSd4NotCreated(Integer oldMonSd4NotCreated) {
		this.oldMonSd4NotCreated = oldMonSd4NotCreated;
	}

	public Integer getOldMonSd4InProgress() {
		return oldMonSd4InProgress;
	}

	public void setOldMonSd4InProgress(Integer oldMonSd4InProgress) {
		this.oldMonSd4InProgress = oldMonSd4InProgress;
	}

	public Integer getOldMonSd4Approved() {
		return oldMonSd4Approved;
	}

	public void setOldMonSd4Approved(Integer oldMonSd4Approved) {
		this.oldMonSd4Approved = oldMonSd4Approved;
	}

	public Integer getOldMonInvNotCreated() {
		return oldMonInvNotCreated;
	}

	public void setOldMonInvNotCreated(Integer oldMonInvNotCreated) {
		this.oldMonInvNotCreated = oldMonInvNotCreated;
	}

	public Integer getOldMonInvInProgress() {
		return oldMonInvInProgress;
	}

	public void setOldMonInvInProgress(Integer oldMonInvInProgress) {
		this.oldMonInvInProgress = oldMonInvInProgress;
	}

	public Integer getOldMonInvApproved() {
		return oldMonInvApproved;
	}

	public void setOldMonInvApproved(Integer oldMonInvApproved) {
		this.oldMonInvApproved = oldMonInvApproved;
	}

	public Integer getOldMonSd1NotCrEquipCount() {
		return oldMonSd1NotCrEquipCount;
	}

	public void setOldMonSd1NotCrEquipCount(Integer oldMonSd1NotCrEquipCount) {
		this.oldMonSd1NotCrEquipCount = oldMonSd1NotCrEquipCount;
	}

	public Integer getOldMonSd1ProgEquipCount() {
		return oldMonSd1ProgEquipCount;
	}

	public void setOldMonSd1ProgEquipCount(Integer oldMonSd1ProgEquipCount) {
		this.oldMonSd1ProgEquipCount = oldMonSd1ProgEquipCount;
	}

	public Integer getOldMonSd1ApprEquipCount() {
		return oldMonSd1ApprEquipCount;
	}

	public void setOldMonSd1ApprEquipCount(Integer oldMonSd1ApprEquipCount) {
		this.oldMonSd1ApprEquipCount = oldMonSd1ApprEquipCount;
	}

	public Integer getOldMonSd2NotCrEquipCount() {
		return oldMonSd2NotCrEquipCount;
	}

	public void setOldMonSd2NotCrEquipCount(Integer oldMonSd2NotCrEquipCount) {
		this.oldMonSd2NotCrEquipCount = oldMonSd2NotCrEquipCount;
	}

	public Integer getOldMonSd2ProgEquipCount() {
		return oldMonSd2ProgEquipCount;
	}

	public void setOldMonSd2ProgEquipCount(Integer oldMonSd2ProgEquipCount) {
		this.oldMonSd2ProgEquipCount = oldMonSd2ProgEquipCount;
	}

	public Integer getOldMonSd2ApprEquipCount() {
		return oldMonSd2ApprEquipCount;
	}

	public void setOldMonSd2ApprEquipCount(Integer oldMonSd2ApprEquipCount) {
		this.oldMonSd2ApprEquipCount = oldMonSd2ApprEquipCount;
	}

	public Integer getOldMonSd3NotCrEquipCount() {
		return oldMonSd3NotCrEquipCount;
	}

	public void setOldMonSd3NotCrEquipCount(Integer oldMonSd3NotCrEquipCount) {
		this.oldMonSd3NotCrEquipCount = oldMonSd3NotCrEquipCount;
	}

	public Integer getOldMonSd3ProgEquipCount() {
		return oldMonSd3ProgEquipCount;
	}

	public void setOldMonSd3ProgEquipCount(Integer oldMonSd3ProgEquipCount) {
		this.oldMonSd3ProgEquipCount = oldMonSd3ProgEquipCount;
	}

	public Integer getOldMonSd3ApprEquipCount() {
		return oldMonSd3ApprEquipCount;
	}

	public void setOldMonSd3ApprEquipCount(Integer oldMonSd3ApprEquipCount) {
		this.oldMonSd3ApprEquipCount = oldMonSd3ApprEquipCount;
	}

	public Integer getOldMonSd4NotCrEquipCount() {
		return oldMonSd4NotCrEquipCount;
	}

	public void setOldMonSd4NotCrEquipCount(Integer oldMonSd4NotCrEquipCount) {
		this.oldMonSd4NotCrEquipCount = oldMonSd4NotCrEquipCount;
	}

	public Integer getOldMonSd4ProgEquipCount() {
		return oldMonSd4ProgEquipCount;
	}

	public void setOldMonSd4ProgEquipCount(Integer oldMonSd4ProgEquipCount) {
		this.oldMonSd4ProgEquipCount = oldMonSd4ProgEquipCount;
	}

	public Integer getOldMonSd4ApprEquipCount() {
		return oldMonSd4ApprEquipCount;
	}

	public void setOldMonSd4ApprEquipCount(Integer oldMonSd4ApprEquipCount) {
		this.oldMonSd4ApprEquipCount = oldMonSd4ApprEquipCount;
	}

	public Integer getOldMonInvNotCrEquipCount() {
		return oldMonInvNotCrEquipCount;
	}

	public void setOldMonInvNotCrEquipCount(Integer oldMonInvNotCrEquipCount) {
		this.oldMonInvNotCrEquipCount = oldMonInvNotCrEquipCount;
	}

	public Integer getOldMonInvProgEquipCount() {
		return oldMonInvProgEquipCount;
	}

	public void setOldMonInvProgEquipCount(Integer oldMonInvProgEquipCount) {
		this.oldMonInvProgEquipCount = oldMonInvProgEquipCount;
	}

	public Integer getOldMonInvApprEquipCount() {
		return oldMonInvApprEquipCount;
	}

	public void setOldMonInvApprEquipCount(Integer oldMonInvApprEquipCount) {
		this.oldMonInvApprEquipCount = oldMonInvApprEquipCount;
	}

	@Override
	public String toString() {
		return "InvoiceCounter [currMonTotal=" + currMonTotal + ", currMonEquipCount=" + currMonEquipCount
				+ ", currMonSd1NotCreated=" + currMonSd1NotCreated + ", currMonSd1InProgress=" + currMonSd1InProgress
				+ ", currMonSd1Approved=" + currMonSd1Approved + ", currMonSd1NotCrEquipCount="
				+ currMonSd1NotCrEquipCount + ", currMonSd1ProgEquipCount=" + currMonSd1ProgEquipCount
				+ ", currMonSd1ApprEquipCount=" + currMonSd1ApprEquipCount + ", currMonSd2NotCreated="
				+ currMonSd2NotCreated + ", currMonSd2InProgress=" + currMonSd2InProgress + ", currMonSd2Approved="
				+ currMonSd2Approved + ", currMonSd2NotCrEquipCount=" + currMonSd2NotCrEquipCount
				+ ", currMonSd2ProgEquipCount=" + currMonSd2ProgEquipCount + ", currMonSd2ApprEquipCount="
				+ currMonSd2ApprEquipCount + ", currMonSd3NotCreated=" + currMonSd3NotCreated
				+ ", currMonSd3InProgress=" + currMonSd3InProgress + ", currMonSd3Approved=" + currMonSd3Approved
				+ ", currMonSd3NotCrEquipCount=" + currMonSd3NotCrEquipCount + ", currMonSd3ProgEquipCount="
				+ currMonSd3ProgEquipCount + ", currMonSd3ApprEquipCount=" + currMonSd3ApprEquipCount
				+ ", currMonSd4NotCreated=" + currMonSd4NotCreated + ", currMonSd4InProgress=" + currMonSd4InProgress
				+ ", currMonSd4Approved=" + currMonSd4Approved + ", currMonSd4NotCrEquipCount="
				+ currMonSd4NotCrEquipCount + ", currMonSd4ProgEquipCount=" + currMonSd4ProgEquipCount
				+ ", currMonSd4ApprEquipCount=" + currMonSd4ApprEquipCount + ", currMonInvNotCreated="
				+ currMonInvNotCreated + ", currMonInvInProgress=" + currMonInvInProgress + ", currMonInvApproved="
				+ currMonInvApproved + ", currMonInvNotCrEquipCount=" + currMonInvNotCrEquipCount
				+ ", currMonInvProgEquipCount=" + currMonInvProgEquipCount + ", currMonInvApprEquipCount="
				+ currMonInvApprEquipCount + ", currMonTotal1=" + currMonTotal1 + ", currMonEquipCount1="
				+ currMonEquipCount1 + ", currMonTotal2=" + currMonTotal2 + ", currMonEquipCount2=" + currMonEquipCount2
				+ ", oldMonTotal1=" + oldMonTotal1 + ", oldMonEquipCount1=" + oldMonEquipCount1 + ", oldMonTotal2="
				+ oldMonTotal2 + ", oldMonEquipCount2=" + oldMonEquipCount2 + ", oldMonTotal=" + oldMonTotal
				+ ", oldMonEquipCount=" + oldMonEquipCount + ", oldMonSd1NotCreated=" + oldMonSd1NotCreated
				+ ", oldMonSd1InProgress=" + oldMonSd1InProgress + ", oldMonSd1Approved=" + oldMonSd1Approved
				+ ", oldMonSd2NotCreated=" + oldMonSd2NotCreated + ", oldMonSd2InProgress=" + oldMonSd2InProgress
				+ ", oldMonSd2Approved=" + oldMonSd2Approved + ", oldMonSd3NotCreated=" + oldMonSd3NotCreated
				+ ", oldMonSd3InProgress=" + oldMonSd3InProgress + ", oldMonSd3Approved=" + oldMonSd3Approved
				+ ", oldMonSd4NotCreated=" + oldMonSd4NotCreated + ", oldMonSd4InProgress=" + oldMonSd4InProgress
				+ ", oldMonSd4Approved=" + oldMonSd4Approved + ", oldMonInvNotCreated=" + oldMonInvNotCreated
				+ ", oldMonInvInProgress=" + oldMonInvInProgress + ", oldMonInvApproved=" + oldMonInvApproved
				+ ", oldMonSd1NotCrEquipCount=" + oldMonSd1NotCrEquipCount + ", oldMonSd1ProgEquipCount="
				+ oldMonSd1ProgEquipCount + ", oldMonSd1ApprEquipCount=" + oldMonSd1ApprEquipCount
				+ ", oldMonSd2NotCrEquipCount=" + oldMonSd2NotCrEquipCount + ", oldMonSd2ProgEquipCount="
				+ oldMonSd2ProgEquipCount + ", oldMonSd2ApprEquipCount=" + oldMonSd2ApprEquipCount
				+ ", oldMonSd3NotCrEquipCount=" + oldMonSd3NotCrEquipCount + ", oldMonSd3ProgEquipCount="
				+ oldMonSd3ProgEquipCount + ", oldMonSd3ApprEquipCount=" + oldMonSd3ApprEquipCount
				+ ", oldMonSd4NotCrEquipCount=" + oldMonSd4NotCrEquipCount + ", oldMonSd4ProgEquipCount="
				+ oldMonSd4ProgEquipCount + ", oldMonSd4ApprEquipCount=" + oldMonSd4ApprEquipCount
				+ ", oldMonInvNotCrEquipCount=" + oldMonInvNotCrEquipCount + ", oldMonInvProgEquipCount="
				+ oldMonInvProgEquipCount + ", oldMonInvApprEquipCount=" + oldMonInvApprEquipCount + "]";
	}

		
}
