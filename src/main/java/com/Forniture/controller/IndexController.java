package com.Forniture.controller;

import com.Forniture.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private final SectionService sectionService;

    public IndexController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping({"/", "/Home", "/Home/"})
    public String indexInicio(Model model) {

        model.addAttribute(
                "InicioDecoracion",
                sectionService.getBySection((byte) 1)
        );

        model.addAttribute(
                "ProductosMasVendidos",
                sectionService.getBySection((byte) 2)
        );

        model.addAttribute(
                "DescubreNuestrosProductos",
                sectionService.getBySection((byte) 3)
        );

        return "index";
    }
}