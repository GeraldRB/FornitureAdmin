package com.Forniture.controller;

import com.Forniture.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/butacas")
public class ButacasController {

    @Autowired
    private ProductoService productoService;

    @GetMapping({"/"})
    public String indexLivingRoom(Model model) {

        var productos = productoService.getProductosActivosPorCategoria(2);

        model.addAttribute("Productos", productos);

        return "Butacas/index";
    }

    @GetMapping("/producto/{productoID}")
    public String detailsLivingRoom(@PathVariable Integer productoID, Model model) {

        var producto = productoService.getProducto(productoID)
                .orElseThrow(() -> new IllegalArgumentException("No existe el producto. " + productoID));

        model.addAttribute("producto", producto);

        return "Butacas/Details";
    }
}
