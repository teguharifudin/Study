package com.example.study.repository;

import com.example.study.dto.StockDto;
import com.example.study.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query(value = "SELECT * FROM stock", nativeQuery = true)
    List<Stock> getStocks();

    @Query(value = "SELECT * FROM stock WHERE id = :id", nativeQuery = true)
    Optional<Stock> findById(Integer id);

    @Query(value = "INSERT INTO stock (nama, stok, nomor, gambar, attributes, usr, created, rev, updated) VALUES (nama, stok, nomor, file, attributes, usr, created, rev, updated)", nativeQuery = true)
    Stock save(StockDto stockDto);

    @Modifying
    @Query(value = "DELETE FROM stock WHERE id = :id", nativeQuery = true)
    void deleteById(Integer id);

}
