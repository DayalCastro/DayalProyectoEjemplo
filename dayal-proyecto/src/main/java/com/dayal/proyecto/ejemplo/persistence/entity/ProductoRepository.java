package com.dayal.proyecto.ejemplo.persistence.entity;

import com.dayal.proyecto.ejemplo.domain.Product;
import com.dayal.proyecto.ejemplo.domain.repository.ProductRepository;
import com.dayal.proyecto.ejemplo.persistence.crud.ProductoCrudRepository;
import com.dayal.proyecto.ejemplo.persistence.entity.Producto;
import com.dayal.proyecto.ejemplo.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);}

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanEstado(quantity,true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product)
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    public Producto save (Producto producto){
        return productoCrudRepository.save(producto);
    }
    @Override
    public void delete (int productId){
        productoCrudRepository.deleteById(productId);
    }
}
