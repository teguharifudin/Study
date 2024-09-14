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
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private FileService fileService;

    @GetMapping
	public ResponseEntity<List<StockDto>> getStocks(){
		return ResponseEntity.status(HttpStatus.OK).body(stockService.getStocks());
	}

    @GetMapping(value = "/{id}")
	public ResponseEntity<Optional<StockDto>> findById(@PathVariable Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(stockService.findById(id));
	}

    @PostMapping(value = "/create")
    public ResponseEntity<String> createStock(@ModelAttribute Stock stock, @RequestParam("file") MultipartFile file) {
        try {
            if (isSupportedContentType(file.getContentType())) {
                fileService.save(file);
                stock.setGambar(file.getOriginalFilename());
            }else{
                return ResponseEntity.status(HttpStatus.OK).body("Failed to add stock. Only PNG or JPG images are allowed.");
            }
        } catch (Exception e) {
            // log.error("Error processing file: {}", file.getOriginalFilename());
        }
        stockService.create(stock);
        return ResponseEntity.status(HttpStatus.OK).body("Stock added successful.");
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateStock(@ModelAttribute Stock stock, @RequestParam("file") MultipartFile file) {
        try {
            if (isSupportedContentType(file.getContentType())) {
                fileService.save(file);
                stock.setGambar(file.getOriginalFilename());
            }else{
                return ResponseEntity.status(HttpStatus.OK).body("Failed to update stock. Only PNG or JPG images are allowed.");
            }
        } catch (Exception e) {
            // log.error("Error processing file: {}", file.getOriginalFilename());
        }
        stockService.update(stock, stock.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Stock updated successful.");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable("id") Integer id) {
        stockService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Stock has been removed.");
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/png");
    }

}
