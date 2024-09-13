package com.example.study.controller;

import com.example.study.model.Stock;
import com.example.study.dto.StockDto;
import com.example.study.service.StockService;
import com.example.study.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private FileService fileService;

    @GetMapping
    public List<StockDto> getStockList() {
        return stockService.getStockList();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable("id") final Long id) {
        final Stock fetchedStock = stockService.findById(id);
        if (fetchedStock == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fetchedStock, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public void createStock(@ModelAttribute Stock stock, @RequestParam("file") MultipartFile file) {
        try {
            if (isSupportedContentType(file.getContentType())) {
                fileService.save(file);
                stock.setGambar(file.getOriginalFilename());
            }
        } catch (Exception e) {
            // log.error("Error processing file: {}", file.getOriginalFilename());
        }
        stockService.create(stock);
    }

    @PutMapping(value = "/update")
    public void updateStock(@ModelAttribute Stock stock, @RequestParam("file") MultipartFile file) {
        try {
            if (isSupportedContentType(file.getContentType())) {
                fileService.save(file);
                stock.setGambar(file.getOriginalFilename());
            }
        } catch (Exception e) {
            // log.error("Error processing file: {}", file.getOriginalFilename());
        }
        stockService.update(stock, stock.getId());
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStock(@PathVariable("id") Integer id) {
        stockService.deleteById(id);
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/png");
    }

}