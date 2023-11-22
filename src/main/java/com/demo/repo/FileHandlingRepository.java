package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.FileDetails;

public interface FileHandlingRepository extends JpaRepository<FileDetails, Long>{

}
