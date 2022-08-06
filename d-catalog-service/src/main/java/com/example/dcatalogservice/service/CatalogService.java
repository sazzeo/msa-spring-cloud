package com.example.dcatalogservice.service;

import com.example.dcatalogservice.vo.ResponseCatalog;

import java.util.List;

public interface CatalogService {

    List<ResponseCatalog> findAllCatalogs();

}
