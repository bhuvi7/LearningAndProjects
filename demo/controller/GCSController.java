package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.file.FileSystem;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.example.demo.EntityModel.CammsBO;
import com.example.demo.EntityModel.GCSFile;
import com.example.demo.EntityModel.GCSFileResponse;
import com.example.demo.repository.CammsBORepository;

@RestController
@RequestMapping(path="/gcs")
@CrossOrigin(origins = "*")
public class GCSController {
	
	@Autowired
    ResourceLoader resourceLoader;
	
	@Autowired
	private CammsBORepository cammsRepository;

	@GetMapping(path = "/all")
	public List<GCSFile> getAllGCSFile() {
        
       // System.getProperty("user.dir");

        //String PATH_TO_JSON_KEY = "src/main/resource/cimsapi-7e0a88c7425a.json";

         String PATH_TO_JSON_KEY = "cimsapi-7e0a88c7425a.json";
        System.out.println(PATH_TO_JSON_KEY);
		String PROJECT_ID = "cimsapi";
		System.out.println("stage0");
		List<GCSFile> files = new ArrayList<GCSFile>();
		
	try {	
       System.out.println("stage0.1");
        Resource resource = resourceLoader.getResource("classpath:static/cimsapi-7e0a88c7425a.json");
        //File filetemp= new File(getClass().getResource(PATH_TO_JSON_KEY).getFile());
        //File filetemp = resource.getFile();

       System.out.println("stage0.2"); 
        // if(filetemp!=null)
        // {System.out.println(filetemp);
        // }
        //     StorageOptions options = StorageOptions.newBuilder()
	    //         .setProjectId(PROJECT_ID)
	    //         .setCredentials(GoogleCredentials.fromStream( new FileInputStream(filetemp))).build();
	    //   //             new FileInputStream(new File(Model.class.getClassLoader().getResourceAsStream(PATH_TO_JSON_KEY).toString())))).build();
        
        StorageOptions options = StorageOptions.newBuilder()
	            .setProjectId(PROJECT_ID)
	            .setCredentials(GoogleCredentials.fromStream( resource.getInputStream())).build();
       
                
		//Storage storage = StorageOptions.getDefaultInstance().getService();
        System.out.println("stage1");
		Storage storage = options.getService(); 
	    // Get the bucket
	    Bucket bucket = storage.get("cft-cimsapi-camsbucket");
	    System.out.println("stage2");
        Page<Blob> blobs = bucket.list();
         System.out.println("stage3");
	    for (Blob blob : blobs.iterateAll()) {
	       // Gets the path of the object
	       // String path = blob.getName();
	        System.out.println(blob.getSelfLink());
	       // System.out.println(path);
	        
	     // Define resource
	        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("cft-cimsapi-camsbucket", blob.getName())).build();

	        //URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
	        
	        // URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.signWith(ServiceAccountCredentials.fromStream(
            //         new FileInputStream(PATH_TO_JSON_KEY))));
	        
             URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.signWith(ServiceAccountCredentials.fromStream(
                    resource.getInputStream())));
	       // System.out.println(url.getRef());
	       // System.out.println(url.getPath());
	        System.out.println(url.toString() + "&response-content-disposition=attachment;");
	        
	        GCSFile gscFile = new GCSFile();
	        gscFile.setFileName(blob.getName());
	        gscFile.setFileUrl(url.toString() + "&response-content-disposition=attachment;");
	        files.add(gscFile);
//	        	blob.copyTo("camssuccessbucket");
//	        	blob.delete();
//	        
        }
        System.out.println("stage4");
	}
	catch(Exception e)
	{
        System.out.println(e);
    }
	
	return files;
	
	}
	
	@PostMapping("/approve/{fileName}")
	public GCSFileResponse approveFile(@PathVariable("fileName") String fileName) {
		
		boolean approveFile = false;
		String PATH_TO_JSON_KEY = "cimsapi-7e0a88c7425a.json";
		String PROJECT_ID = "cimsapi";
		
		try {
			Resource resource = resourceLoader.getResource("classpath:static/cimsapi-7e0a88c7425a.json");
        
        StorageOptions options = StorageOptions.newBuilder()
	            .setProjectId(PROJECT_ID)
	            .setCredentials(GoogleCredentials.fromStream( resource.getInputStream())).build();

			Storage storage = options.getService(); 
		    // Get the bucket
		   // Bucket bucket = storage.get("cft-cimsapi-camsbucket");
		    
		    Blob blob = storage.get("cft-cimsapi-camsbucket",fileName);
		    System.out.println(blob.getSelfLink());
		    ReadChannel readChannel = blob.reader();
		    BufferedReader br = new BufferedReader(Channels.newReader(readChannel, "UTF-8"));
		   // String fileContent = new String(blob.getContent());
		   // System.out.println(fileContent);
		    List<CammsBO> successCammsBO = new ArrayList<CammsBO>();
		    String strLine = "";
		    while ((strLine = br.readLine()) != null) {
		    //	System.out.println(strLine);
//		    	System.out.println("------");
		    	
		    	CammsBO cammsBO = new CammsBO();
 				
 				String[] fieldsArray = strLine.split("|", -1);
 				
 				cammsBO.setBENumber(fieldsArray[0]);
 				cammsBO.setBECategory(fieldsArray[1]);
 				cammsBO.setClinicCategory(fieldsArray[2]);
 				cammsBO.setZone(fieldsArray[3]);
 				cammsBO.setClinicCode(fieldsArray[4]);
 				cammsBO.setDistrict(fieldsArray[5]);
 				cammsBO.setCircle(fieldsArray[6]);
 				cammsBO.setInstallationDate(convertDate(fieldsArray[7]));
 				cammsBO.setTCDate(convertDate(fieldsArray[8]));
 				cammsBO.setAcceptanceDate(convertDate(fieldsArray[9]));
 				cammsBO.setRentalStartDate(convertDate(fieldsArray[10]));
 				cammsBO.setRentalEndDate(convertDate(fieldsArray[11]));
 				cammsBO.setWarrantyExpiryDate(convertDate(fieldsArray[12]));
 				cammsBO.setWarrantyStartDate(convertDate(fieldsArray[13]));
 				cammsBO.setPurchaseDate(convertDate(fieldsArray[14]));
 				cammsBO.setClinicType(fieldsArray[15]);
 				cammsBO.setState(fieldsArray[16]);
 				cammsBO.setMonthlyRev(Float.parseFloat(fieldsArray[17]));
 				if(!fieldsArray[18].equals(""))
 				{
 				cammsBO.setRentalPerMonth(Float.parseFloat(fieldsArray[18]));
 				}
 				else
 				{cammsBO.setRentalPerMonth(0);}	
 				cammsBO.setBatchNumber(fieldsArray[19]);
 				cammsBO.setOwnership(fieldsArray[20]);
 				cammsBO.setPurchaseCost(Float.parseFloat(fieldsArray[21]));
 				cammsBO.setModelNumber(fieldsArray[22]);
 				cammsBO.setSerialNumber(fieldsArray[23]);
 				cammsBO.setCurrentInstallmentNo(fieldsArray[24]);
 				cammsBO.setBEConditionalStatus(fieldsArray[25]);
 				cammsBO.setAuditStartDate(convertDate(fieldsArray[26]));
 				cammsBO.setFinCategory(fieldsArray[27]);
 				cammsBO.setActionType(fieldsArray[28]);
 				
 				successCammsBO.add(cammsBO);
		    }
		    
		    int persitence =0;
			
			if(successCammsBO.size()>0)
			{
				try {
				for(CammsBO camms1BO : successCammsBO)
				{
				//	System.out.println(camms1BO);
					cammsRepository.save(camms1BO);
				//	System.out.println("Persistence data:"+ persitence);
					persitence++;
				}
				approveFile = true;
				} catch(Exception e) {
					System.out.print(e);
				}
				//cammsRepository.saveAll(successCammsBO);
			}
		    
		    if(approveFile) {
		    	blob.copyTo("camssuccessbucket");
	        	blob.delete();
		    } else {
		    	blob.copyTo("camsfailurebucket");
	        	blob.delete();
		    }
		    
		} catch(Exception e) {
			System.out.print(e);
		}
		GCSFileResponse gcsFileResponse = new GCSFileResponse();
		gcsFileResponse.setFileStatus("Success");
		return gcsFileResponse;
	}
	
	@PostMapping("/reject/{fileName}")
	public GCSFileResponse rejectFile(@PathVariable("fileName") String fileName) {
		
		String PATH_TO_JSON_KEY = "cimsapi-7e0a88c7425a.json";
		String PROJECT_ID = "cimsapi";
		
		try {
			Resource resource = resourceLoader.getResource("classpath:static/cimsapi-7e0a88c7425a.json");
        
        StorageOptions options = StorageOptions.newBuilder()
	            .setProjectId(PROJECT_ID)
	            .setCredentials(GoogleCredentials.fromStream( resource.getInputStream())).build();

			Storage storage = options.getService();
		    // Get the bucket
		   // Bucket bucket = storage.get("cft-cimsapi-camsbucket");
		    
		    Blob blob = storage.get("cft-cimsapi-camsbucket",fileName);
		    	blob.copyTo("camsfailurebucket");
	        	blob.delete();
		    
		} catch(Exception e) {
			System.out.print(e);
		}
		GCSFileResponse gcsFileResponse = new GCSFileResponse();
		gcsFileResponse.setFileStatus("Failure");
		return gcsFileResponse;
	}
	
		@GetMapping(path = "/success/all")
	public List<GCSFile> getAllGCSSuccessFile() {
		
		String PATH_TO_JSON_KEY = "cimsapi-7e0a88c7425a.json";
		String PROJECT_ID = "cimsapi";
		
		List<GCSFile> files = new ArrayList<GCSFile>();
		
	try {	Resource resource = resourceLoader.getResource("classpath:static/cimsapi-7e0a88c7425a.json");
        //File filetemp= new File(getClass().getResource(PATH_TO_JSON_KEY).getFile());
        //File filetemp = resource.getFile();

       System.out.println("stage0.2"); 
        // if(filetemp!=null)
        // {System.out.println(filetemp);
        // }
        //     StorageOptions options = StorageOptions.newBuilder()
	    //         .setProjectId(PROJECT_ID)
	    //         .setCredentials(GoogleCredentials.fromStream( new FileInputStream(filetemp))).build();
	    //   //             new FileInputStream(new File(Model.class.getClassLoader().getResourceAsStream(PATH_TO_JSON_KEY).toString())))).build();
        
        StorageOptions options = StorageOptions.newBuilder()
	            .setProjectId(PROJECT_ID)
	            .setCredentials(GoogleCredentials.fromStream( resource.getInputStream())).build();
		
		//Storage storage = StorageOptions.getDefaultInstance().getService();

		Storage storage = options.getService(); 
	    // Get the bucket
	    Bucket bucket = storage.get("camssuccessbucket");
	    
	    Page<Blob> blobs = bucket.list();
	    for (Blob blob : blobs.iterateAll()) {
	       // Gets the path of the object
	       // String path = blob.getName();
	       // System.out.println(blob.getSelfLink());
	       // System.out.println(path);
	        
	     // Define resource
	        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("cft-cimsapi-camsbucket", blob.getName())).build();

	        //URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
	        
	        URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.signWith(ServiceAccountCredentials.fromStream(
                    resource.getInputStream())));
	        

	       // System.out.println(url.getRef());
	       // System.out.println(url.getPath());
	       // System.out.println(url.toString() + "&response-content-disposition=attachment;");
	        
	        GCSFile gcsFile = new GCSFile();
	        gcsFile.setFileName(blob.getName());
	        gcsFile.setFileUrl(url.toString() + "&response-content-disposition=attachment;");
	        gcsFile.setFileStatus("File Approved");
	        files.add(gcsFile);
//	        	blob.copyTo("camssuccessbucket");
//	        	blob.delete();
//	        
	    }
	}
	catch(Exception e)
	{System.out.println(e);
	e.printStackTrace();
	}
	
	return files;
	
	}
	
	@GetMapping(path = "/failure/all")
	public List<GCSFile> getAllGCSFailureFile() {
		
		String PATH_TO_JSON_KEY = "cimsapi-7e0a88c7425a.json";
		String PROJECT_ID = "cimsapi";
		
		List<GCSFile> files = new ArrayList<GCSFile>();
		
	try {	Resource resource = resourceLoader.getResource("classpath:static/cimsapi-7e0a88c7425a.json");
        //File filetemp= new File(getClass().getResource(PATH_TO_JSON_KEY).getFile());
        //File filetemp = resource.getFile();

       System.out.println("stage0.2"); 
        // if(filetemp!=null)
        // {System.out.println(filetemp);
        // }
        //     StorageOptions options = StorageOptions.newBuilder()
	    //         .setProjectId(PROJECT_ID)
	    //         .setCredentials(GoogleCredentials.fromStream( new FileInputStream(filetemp))).build();
	    //   //             new FileInputStream(new File(Model.class.getClassLoader().getResourceAsStream(PATH_TO_JSON_KEY).toString())))).build();
        
        StorageOptions options = StorageOptions.newBuilder()
	            .setProjectId(PROJECT_ID)
	            .setCredentials(GoogleCredentials.fromStream( resource.getInputStream())).build();
		
		//Storage storage = StorageOptions.getDefaultInstance().getService();

		Storage storage = options.getService(); 
	    // Get the bucket
	    Bucket bucket = storage.get("camsfailurebucket");
	    
	    Page<Blob> blobs = bucket.list();
	    for (Blob blob : blobs.iterateAll()) {
	       // Gets the path of the object
	       // String path = blob.getName();
	       // System.out.println(blob.getSelfLink());
	       // System.out.println(path);
	        
	     // Define resource
	        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("cft-cimsapi-camsbucket", blob.getName())).build();

	        //URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
	        
	        URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.signWith(ServiceAccountCredentials.fromStream(
                    resource.getInputStream())));
	        

	       // System.out.println(url.getRef());
	       // System.out.println(url.getPath());
	       // System.out.println(url.toString() + "&response-content-disposition=attachment;");
	        
	        GCSFile gcsFile = new GCSFile();
	        gcsFile.setFileName(blob.getName());
	        gcsFile.setFileUrl(url.toString() + "&response-content-disposition=attachment;");
	        gcsFile.setFileStatus("File Rejected");
	        files.add(gcsFile);
//	        	blob.copyTo("camssuccessbucket");
//	        	blob.delete();
//	        
	    }
	}
	catch(Exception e)
	{System.out.println(e);
	e.printStackTrace();
	}
	
	return files;
	
    }
	
	
	public java.sql.Date convertDate(String date)
	{
		java.sql.Date sql = null;
		try
		{
			if(!(date.equals(null)|| date.equals("")))
			
				{
			//	System.out.println(date);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			//format.format(date);
		//java.util.Date parsed = sdf.parse(date);
		
			//java.util.Date parsed =sdf2.parse(sdf2.format(sdf.parse(date)));
			java.util.Date parsed  =sdf2.parse(date);
		
		// System.out.println(parsed);
	     sql = new java.sql.Date(parsed.getTime());
	     
	    // System.out.println(sql);
				}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return sql;
	}
	
	
}
