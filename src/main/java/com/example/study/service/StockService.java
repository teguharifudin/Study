package com.example.study.service;

import com.example.study.dto.StockDto;
import com.example.study.model.Stock;
import com.example.study.repository.StockRepository;

import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<StockDto> getStocks() {
        List<Stock> stock = stockRepository.getStocks();
        List<StockDto> entityToDto = modelMapper.map(stock, new TypeToken<List<StockDto>>(){}.getType());
        return entityToDto;
    }

    public Optional<StockDto> findById(Integer id) {
        Optional<Stock> stock = stockRepository.findById(id);
        Optional<StockDto> entityToDto = modelMapper.map(stock, new TypeToken<Optional<StockDto>>(){}.getType());
        return entityToDto;
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

    public void create(Stock stock) {
        stockRepository.save(stock);
    }

    public Stock update(Stock stock, Integer id) {
        return stockRepository.save(stock);
    }

    @Transactional
    public void deleteById(Integer id) {
        stockRepository.deleteById(id);
    }
}
