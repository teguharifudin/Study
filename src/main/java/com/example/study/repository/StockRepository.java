package com.example.study.repository;

import com.example.study.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Query(value = "SELECT e.id, e.nama, e.stok, e.nomor, e.gambar, e.usr, e.created, e.rev, e.updated FROM stock e", nativeQuery = true)
    List<Stock> getStocks();

    interface Stock {

        String getId();

        String getNama();

        String getStok();

        String getNomor();

        String getGambar();

        String getUsr();

        String getCreated();

        String getRev();

        String getUpdated();

    }

    Optional<com.example.study.model.Stock> findById(Long id);

}
