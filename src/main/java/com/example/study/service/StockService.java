package com.example.study.service;

import com.example.study.model.Stock;
import com.example.study.dto.StockDto;
import com.example.study.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<StockDto> getStockList() {
        List<StockDto> stockList = stockRepository.findAll().stream().map(e -> {
            StockDto dto = new StockDto();
            dto.setId(e.getId());
            dto.setNama(e.getNama());
            dto.setStok(e.getStok());
            dto.setNomor(e.getNomor());
            dto.setGambar(e.getGambar());
            dto.setUsr(e.getUsr());
            dto.setCreated(e.getCreated());
            dto.setRev(e.getRev());
            dto.setUpdated(e.getUpdated());
            return dto;
        }).collect(Collectors.toList());
        return stockList;
    }

    public Stock findById(final Long id) {
        return stockRepository.findById(id).get();
    }

    public String saveImageToStorage(String uploadDirectory, MultipartFile imageFile) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

        Path uploadPath = Path.of(uploadDirectory);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }

    public List<StockRepository.Stock> getStocks() {
        return stockRepository.getStocks();
    }

    public void create(Stock stock) {
        stockRepository.save(stock);
    }

    public Stock update(Stock stock, Integer id) {
        return stockRepository.save(stock);
    }

    public void deleteById(Integer id) {
        stockRepository.deleteById(id);
    }
}