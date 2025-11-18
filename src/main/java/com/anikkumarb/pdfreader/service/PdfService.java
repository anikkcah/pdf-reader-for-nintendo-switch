package com.anikkumarb.pdfreader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anikkumarb.pdfreader.model.PdfModel;
import com.anikkumarb.pdfreader.repository.PdfRepository;

@Service
public class PdfService {

	@Autowired
	private PdfRepository pdfRepository;
	
	public List<PdfModel> getAllPdfs() {
		return pdfRepository.findAll();
	}
	
	public PdfModel savePdf(PdfModel pdfModel) {
		return pdfRepository.save(pdfModel);
	}
	
	public PdfModel getPdf(long id) {
		return pdfRepository.findById(id).orElse(null);
	}
}
