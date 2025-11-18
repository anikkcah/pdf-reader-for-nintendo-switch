package com.anikkumarb.pdfreader.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.anikkumarb.pdfreader.model.PdfModel;
import com.anikkumarb.pdfreader.service.PdfService;

@Controller
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pdfs", pdfService.getAllPdfs());
        return "index";
    }

    @PostMapping("/upload")
    public String uploadPdf(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "index";
        }

        String filename = file.getOriginalFilename();
        String uploadDir = "uploads/pdfs/";
        Path uploadPath = Paths.get(uploadDir, filename);

        try {
            Files.createDirectories(uploadPath.getParent());
            Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
           // Files.copy(file.getInputStream(), Paths.get("src/main/resources/static/pdfs/" + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            
            //Extract text
            String text;
            try (PDDocument document = PDDocument.load(uploadPath.toFile())) {
            	PDFTextStripper stripper = new PDFTextStripper();
            	text = stripper.getText(document);
            }
            PdfModel pdfModel = new PdfModel();
            pdfModel.setName(filename);
            pdfModel.setPath("/pdfs/" + filename);
           // pdfModel.setText(text);
            pdfService.savePdf(pdfModel);

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "File upload failed.");
            return "index";
        }

        return "redirect:/";
    }

    @GetMapping("/view/{id}")
    public String viewPdf(@PathVariable Long id, Model model) {
        PdfModel pdfModel = pdfService.getPdf(id);
        if (pdfModel == null) {
            model.addAttribute("message", "PDF not found.");
            return "index";
        }
        model.addAttribute("pdf", pdfModel);
        return "pdf-viewer";
    }
}