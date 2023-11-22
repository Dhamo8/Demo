package com.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entity.FileDetails;
import com.demo.entity.FileRecords;
import com.demo.repo.FileHandlingRepository;
import com.demo.repo.FileRecordsRepository;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

@Service
public class FileHandlingService {
	
	@Autowired
	private FileHandlingRepository fileHandlingRepository;
	
	@Autowired
	private FileRecordsRepository fileRecordsRepository;
	
	private final static String RESOURCE_PATH = "documents";

	public String createFile(FileDetails fileDetails) {
		fileDetails.setCreatedAt(new Date());
		fileDetails.setUpdatedAt(new Date());
		fileDetails.setFileName(fileDetails.getFile().getOriginalFilename());
		try {
			writeFile(fileDetails.getFile());
			fileDetails.setRecordList(addRecords(RESOURCE_PATH+"/"+fileDetails.getFile().getOriginalFilename()));
		} catch (IOException e) {
			return "File Creation Failed";
		/*} catch (Exception e) {
			return "File Creation Failed";*/
		}
		
		fileDetails.setFilePath(RESOURCE_PATH+"/"+fileDetails.getFile().getOriginalFilename());
		fileHandlingRepository.save(fileDetails);
		return "File Created Successfully";
	}
	
	private List<FileRecords> addRecords(String filePath) {
		PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
		        .addListDelimiter(";").build();
		
		List<FileRecords> records = Poiji.fromExcel(new File(filePath),
				FileRecords.class, options);
		return records;
	}
	
	private void writeFile(MultipartFile attachment) throws IOException{
	    File directory = new File(RESOURCE_PATH);
	    if (! directory.exists()){
	        directory.mkdir();
	    }
	    File file = new File(RESOURCE_PATH + "/" + attachment.getOriginalFilename());
    	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
    	bos.write(attachment.getBytes());
    	bos.flush();
    	bos.close();
	}

	public List<FileDetails> findAllFileDetails() {
		return fileHandlingRepository.findAll();
	}

	public FileDetails findFileDetailsById(Long fileDetailsId) {
		return fileHandlingRepository.findById(fileDetailsId).get();
	}
	
	/*private void deleteRecordsByDetailsId(Long fileDetailsId) {
		fileRecordsRepository.deleteRecordsByFileDetailsId(fileDetailsId);
	}*/

	public String modifyFile(FileDetails fileDetails, Long fileDetailsId) {
		FileDetails existedDetails = fileHandlingRepository.findById(fileDetailsId).get();
		fileDetails.setCreatedAt(existedDetails.getCreatedAt());
		fileDetails.setCreatedBy(existedDetails.getCreatedBy());
		fileDetails.setUpdatedAt(new Date());
		try {
			Files.deleteIfExists(new File(existedDetails.getFilePath()).toPath());
			writeFile(fileDetails.getFile());
		} catch (IOException e) {
			return "File Modification Failed";
		} catch (Exception e) {
			return "File Creation Failed";
		}
		fileDetails.setFilePath(RESOURCE_PATH+"/"+fileDetails.getFile().getOriginalFilename());
		fileHandlingRepository.save(fileDetails);
		return "File Created Successfully";
	}

	public String removeFileDetailsById(Long fileDetailsId) {
		FileDetails existedDetails = fileHandlingRepository.findById(fileDetailsId).get();
		try {
			Files.deleteIfExists(new File(existedDetails.getFilePath()).toPath());
		} catch (IOException e) {
			return "File Deletion Failed";
		} catch (Exception e) {
			return "File Creation Failed";
		}
		fileHandlingRepository.deleteById(fileDetailsId);
		return "File Deleted Successfully";
	}
}
