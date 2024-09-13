package com.example.study.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private int id;

    private String nama;

    private String stok;

    private String nomor;

    private String gambar;

    private String usr;

    private Date created;

    private String rev;

    private Date updated;

}