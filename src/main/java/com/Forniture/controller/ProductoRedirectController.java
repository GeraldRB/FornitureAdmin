package com.Forniture.controller;

import com.Forniture.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductoRedirectController {

    private ProductoService productoService;

    public ProductoRedirectController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/producto/{productoID}")
    public String redirectProduct(@PathVariable Integer productoID) {

        var producto = productoService.getProducto(productoID)
                .orElseThrow(()
                        -> new IllegalArgumentException(
                        "No existe el producto: " + productoID
                )
                );

        switch (producto.getCategoria()) {

            case 1:
                return "redirect:/sala/producto/" + productoID;

            case 2:
                return "redirect:/butacas/producto/" + productoID;

            case 3:
                return "redirect:/dormitorios/producto/" + productoID;

            case 4:
                return "redirect:/comedores/producto/" + productoID;

            case 5:
                return "redirect:/armarios/producto/" + productoID;

            default:
                throw new IllegalArgumentException("Categoría no válida");
        }
    }

}
