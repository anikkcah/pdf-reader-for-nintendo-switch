package com.anikkumarb.pdfreader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anikkumarb.pdfreader.model.*;

public interface PdfRepository extends JpaRepository<PdfModel, Long>{

}
