package com.inventory.inventory_api.Service;

import com.inventory.inventory_api.Entity.Product;
import com.inventory.inventory_api.Entity.Category;
import com.inventory.inventory_api.Entity.Supplier;
import com.inventory.inventory_api.Exception.BusinessException;
import com.inventory.inventory_api.Exception.ResourceNotFoundException;
import com.inventory.inventory_api.Mapper.ProductMapper;
import com.inventory.inventory_api.Repository.CategoryRepository;
import com.inventory.inventory_api.Repository.ProductRepository;
import com.inventory.inventory_api.Repository.SupplierRepository;
import com.inventory.inventory_api.Dto.ProductCreateDTO;
import com.inventory.inventory_api.Dto.ProductFilterDTO;
import com.inventory.inventory_api.Dto.ProductResponseDTO;
import com.inventory.inventory_api.Dto.ProductUpdateDTO;
import com.inventory.inventory_api.Specification.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository,SupplierRepository supplierRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
        this.supplierRepository=supplierRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll(){
        List<Product> products = productRepository.findByStatusTrue();
        return ProductMapper.toDTOList(products);
    }

    @Transactional(readOnly = true)
    public Optional<ProductResponseDTO> findById(int id){
        Optional<Product> productOpt=productRepository.findByIdAndStatusTrue(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            ProductResponseDTO dto = ProductMapper.toDTO(product);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public ProductResponseDTO save(ProductCreateDTO productDto){

        if (productRepository.existsByName(productDto.getName())) {
            throw new BusinessException("There is already a product with that name");
        }

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con ID: " + productDto.getCategoryId()
                ));

        Supplier supplier = supplierRepository.findById(productDto.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proveedor no encontrado con ID: " + productDto.getSupplierId()
                ));

        Product product = ProductMapper.toEntity(productDto);
        product.setCategory(category);
        product.setSupplier(supplier);

        Product saved = productRepository.save(product);

        return ProductMapper.toDTO(saved);
    }


    @Transactional
    public void delete(int id){
        Product product = productRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with ID: " + id)
                );

        product.setStatus(false);
        productRepository.save(product);
    }

    @Transactional
    public Optional<ProductResponseDTO> update(int id, ProductUpdateDTO productDto){
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()){
            Product product=productOpt.get();

            if (productDto.getName() != null && !product.getName().equalsIgnoreCase(productDto.getName())) {
                if (categoryRepository.existsByName(productDto.getName())) {
                    throw new BusinessException("There is already a category with that name");
                }
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

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> search(ProductFilterDTO filter) {
        Specification<Product> spec = ProductSpecification.filterBy(
                filter.getName(),
                filter.getCategoryId(),
                filter.getCategoryName(),
                filter.getSupplierId(),
                filter.getSupplierName(),
                filter.getMinPrice(),
                filter.getMaxPrice()
        );

        List<Product> products = productRepository.findAll(spec);

        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
