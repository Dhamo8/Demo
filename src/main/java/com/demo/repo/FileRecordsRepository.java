package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entity.FileRecords;

public interface FileRecordsRepository extends JpaRepository<FileRecords, Long>{

	//void deleteRecordsByFileDetailsId(Long fileDetailsId);
	

}
