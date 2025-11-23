package com.inventory.inventory_api.Service;

import com.inventory.inventory_api.Entity.Product;
import com.inventory.inventory_api.Mapper.ProductMapper;
import com.inventory.inventory_api.Repository.ProductRepository;
import com.inventory.inventory_api.dto.ProductRequestDTO;
import com.inventory.inventory_api.dto.ProductResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll(){
        List<Product> products=(List<Product>) productRepository.findAll();
        return ProductMapper.toDTOList(products);
    }

    @Transactional(readOnly = true)
    public Optional<ProductResponseDTO> findById(int id){
        Optional<Product> productOpt=productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            ProductResponseDTO dto = ProductMapper.toDTO(product);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public ProductResponseDTO save(ProductRequestDTO productDto){
        Product product=ProductMapper.toEntity(productDto);
        Product saved = productRepository.save(product);
        return ProductMapper.toDTO(saved);
    }

    @Transactional
    public Optional<ProductResponseDTO> delete(int id){
        Optional<Product> productOptional=productRepository.findById(id);
        if (productOptional.isPresent()){
            Product product=productOptional.get();
            ProductResponseDTO dto=ProductMapper.toDTO(product);
            productRepository.delete(product);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<ProductResponseDTO> update(int id,ProductRequestDTO productDto){
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()){
            Product product=productOpt.get();

            if (productDto.getName() != null) {
                product.setName(productDto.getName());
            }
            if (productDto.getDescription() != null) {
                product.setDescription(productDto.getDescription());
            }
            if (productDto.getPrice() != null) {
                product.setPrice(productDto.getPrice());
            }
            if (productDto.getStock() != null) {
                product.setStock(productDto.getStock());
            }

            Product updated=productRepository.save(product);
            return Optional.of(ProductMapper.toDTO(updated));
        }
        return Optional.empty();
    }
}
