package com.inventory.inventory_api.Service;

import com.inventory.inventory_api.Entity.Supplier;
import com.inventory.inventory_api.Exception.BusinessException;
import com.inventory.inventory_api.Exception.ResourceNotFoundException;
import com.inventory.inventory_api.Mapper.SupplierMapper;
import com.inventory.inventory_api.Repository.ProductRepository;
import com.inventory.inventory_api.Repository.SupplierRepository;
import com.inventory.inventory_api.dto.SupplierRequestDTO;
import com.inventory.inventory_api.dto.SupplierResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public SupplierService(SupplierRepository supplierRepository,ProductRepository productRepository){
        this.supplierRepository=supplierRepository;
        this.productRepository=productRepository;
    }

    @Transactional(readOnly = true)
    public List<SupplierResponseDTO> findAll(){
        List<Supplier> suppliers=(List<Supplier>) supplierRepository.findAll();
        return SupplierMapper.toDTOList(suppliers);
    }

    @Transactional(readOnly = true)
    public Optional<SupplierResponseDTO> findById(int id){
        Optional<Supplier> supplierOpt=supplierRepository.findById(id);
        if (supplierOpt.isPresent()) {
            Supplier supplier = supplierOpt.get();
            SupplierResponseDTO dto = SupplierMapper.toDTO(supplier);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public SupplierResponseDTO save(SupplierRequestDTO supplierDto){
        Supplier supplier=SupplierMapper.toEntity(supplierDto);
        Supplier saved = supplierRepository.save(supplier);
        return SupplierMapper.toDTO(saved);
    }

    @Transactional
    public void delete(int id) {

        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Proveedor no encontrado con ID: " + id);
        }

        long productCount = productRepository.countBySupplierId(id);

        if (productCount > 0) {
            throw new BusinessException(
                    "No se puede eliminar el proveedor porque tiene " + productCount +
                            " producto(s) asociado(s). Elimine o reasigne los productos primero."
            );
        }

        // 3. Si no tiene productos, eliminar
        supplierRepository.deleteById(id);
    }


    @Transactional
    public Optional<SupplierResponseDTO> update(int id,SupplierRequestDTO supplierDto){
        Optional<Supplier> SupplierOpt = supplierRepository.findById(id);
        if (SupplierOpt.isPresent()){
            Supplier supplier=SupplierOpt.get();

            if (supplierDto.getName() != null) {
                supplier.setName(supplierDto.getName());
            }
            if (supplierDto.getEmail() != null) {
                supplier.setEmail(supplierDto.getEmail());
            }
            if (supplierDto.getPhone() != null) {
                supplier.setPhone(supplierDto.getPhone());
            }

            Supplier updated=supplierRepository.save(supplier);
            return Optional.of(SupplierMapper.toDTO(updated));
        }
        return Optional.empty();
    }
}
