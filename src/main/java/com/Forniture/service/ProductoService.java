package com.Forniture.service;

import com.Forniture.domain.Producto;
import com.Forniture.repository.ProductoRepository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    public Page<Producto> getAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return productoRepository.findAll(pageable);

    }

    @Transactional(readOnly = true)
    public List<Producto> getProductosActivosPorCategoria(Integer categoria) {
        return productoRepository.findByCategoriaAndEstado(categoria, (byte) 1);
    }

    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        return activos
                ? productoRepository.findByEstadoTrue()
                : productoRepository.findAll();
    }

    //Obtain Product
    @Transactional(readOnly = true)
    public Optional<Producto> getProducto(Integer productoID) {
        return productoRepository.findById(productoID);
    }

    @Transactional(readOnly = true)
    public List<Producto> getCategoria(Integer categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    @Transactional
    public Producto disableProduct(Integer id) {
        Producto producto = productoRepository.findById(id).orElseThrow();

        producto.setEstado((byte) 0);

        return productoRepository.save(producto);

    }

    //Method Save Product
    public Producto save(Producto producto) {

        if (producto.getProductoID() == null) {

            if (productoRepository
                    .findBySkuReferencia(producto.getSkuReferencia())
                    .isPresent()) {
                throw new RuntimeException("El SKU ya existe.");
            }

            Producto newProducto = new Producto();

            newProducto.setCategoria(producto.getCategoria());
            newProducto.setProductoNombre(producto.getProductoNombre());
            newProducto.setPrecioVenta(producto.getPrecioVenta());
            newProducto.setSkuReferencia(producto.getSkuReferencia());
            newProducto.setDescripcion(producto.getDescripcion());
            newProducto.setAncho(producto.getAncho());
            newProducto.setAlto(producto.getAlto());
            newProducto.setFondo(producto.getFondo());
            newProducto.setLargo(producto.getLargo());
            newProducto.setCapacidad(producto.getCapacidad());
            newProducto.setTipoCama(producto.getTipoCama());
            newProducto.setMaterialPrincipal(producto.getMaterialPrincipal());
            newProducto.setDetallesAcabado(producto.getDetallesAcabado());
            newProducto.setFechaCreacion(LocalDateTime.now());
            newProducto.setEstado(producto.getEstado());

            //Aqui me devuelve el ID del producto
            return productoRepository.save(newProducto);

        }
        return productoRepository.save(producto);
    }

    public Producto update(Producto producto) {

        Producto productoDB = productoRepository.findById(producto.getProductoID())
                .orElseThrow(() -> new RuntimeException("El producto no existe."));

        // Validar que el SKU no pertenezca a otro producto
        productoRepository.findBySkuReferencia(producto.getSkuReferencia())
                .ifPresent(p -> {
                    if (!p.getProductoID().equals(producto.getProductoID())) {
                        throw new RuntimeException("El SKU ya existe.");
                    }
                });

        productoDB.setCategoria(producto.getCategoria());
        productoDB.setProductoNombre(producto.getProductoNombre());
        productoDB.setPrecioVenta(producto.getPrecioVenta());
        productoDB.setSkuReferencia(producto.getSkuReferencia());
        productoDB.setDescripcion(producto.getDescripcion());
        productoDB.setAncho(producto.getAncho());
        productoDB.setAlto(producto.getAlto());
        productoDB.setFondo(producto.getFondo());
        productoDB.setLargo(producto.getLargo());
        productoDB.setCapacidad(producto.getCapacidad());
        productoDB.setTipoCama(producto.getTipoCama());
        productoDB.setMaterialPrincipal(producto.getMaterialPrincipal());
        productoDB.setDetallesAcabado(producto.getDetallesAcabado());
        productoDB.setEstado(producto.getEstado());

        return productoRepository.save(productoDB);
    }
}
