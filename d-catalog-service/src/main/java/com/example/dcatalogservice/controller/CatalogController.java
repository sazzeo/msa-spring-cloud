package com.example.dcatalogservice.controller;


import com.example.dcatalogservice.service.CatalogService;
import com.example.dcatalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog-service")
public class CatalogController {

    private final Environment env;
    private final CatalogService catalogService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("Catalog Service :: port = %s" , env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> findCatalogs() {
        List<ResponseCatalog> catalogs= catalogService.findAllCatalogs();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(catalogs);
    }

}
