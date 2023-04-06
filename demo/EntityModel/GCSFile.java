package com.example.demo.EntityModel;

public class GCSFile {

	String fileName;
	String fileUrl;
	String fileStatus;
	
	public GCSFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GCSFile(String fileName, String fileUrl, String fileStatus) {
		super();
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		this.fileStatus = fileStatus;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}
	
	
}
