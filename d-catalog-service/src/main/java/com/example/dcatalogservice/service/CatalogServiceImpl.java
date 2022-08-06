package com.example.dcatalogservice.service;

import com.example.dcatalogservice.repository.CatalogRepository;
import com.example.dcatalogservice.vo.CatalogDto;
import com.example.dcatalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService {


    private final CatalogRepository catalogRepository;

    @Override
    public List<ResponseCatalog> findAllCatalogs() {
        return catalogRepository.findAll().stream().map(ResponseCatalog::new).collect(Collectors.toList());
    }
}
