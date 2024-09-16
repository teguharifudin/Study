package com.example.study.service;

import com.example.study.dto.StockDto;
import com.example.study.model.Stock;
import com.example.study.repository.StockRepository;

import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public StockDto create(StockDto stockDto) {
        Stock stock = modelMapper.map(stockDto, Stock.class);
        Stock savedStock = stockRepository.save(stock);
        StockDto savedStockDto = modelMapper.map(savedStock, StockDto.class);
        return savedStockDto;
    }

    public Stock update(StockDto stockDto, Integer id) {
        Stock existingStock = stockRepository.findById(stockDto.getId()).get();
        existingStock.setNama(stockDto.getNama());
        existingStock.setStok(stockDto.getStok());
        existingStock.setNomor(stockDto.getNomor());
        existingStock.setGambar(stockDto.getGambar());
        existingStock.setAttributes(stockDto.getAttributes());
        existingStock.setRev(stockDto.getRev());
        existingStock.setUpdated(stockDto.getUpdated());
        Stock updatedStock = stockRepository.save(existingStock);
        return updatedStock;
    }

    @Transactional
    public void deleteById(Integer id) {
        stockRepository.deleteById(id);
    }
}
