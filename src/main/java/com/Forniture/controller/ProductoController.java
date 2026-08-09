package com.Forniture.controller;

import com.Forniture.domain.ProductoImagen;
import org.springframework.ui.Model;
import com.Forniture.domain.Producto;
import com.Forniture.service.ImagesService;
import com.Forniture.service.ProductoService;
import jakarta.validation.Valid;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/managementProduct")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private ImagesService imagesService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/list")
    public String listProducts(Model model, @RequestParam(defaultValue = "0") int page) {

        Page<Producto> productos = productoService.getAll(page);

        model.addAttribute("Productos", productos.getContent());
        model.addAttribute("pageActual", page);
        model.addAttribute("totalPaginas", productos.getTotalPages());

        return "Product/list";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("producto", new Producto());

        //Product ProductoImagen
        return "Product/create";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("producto") Producto producto,
            RedirectAttributes redirectAttributes) {
        try {
            if (producto.getProductoID() == null) {
                productoService.save(producto);
                redirectAttributes.addFlashAttribute("success", "Producto registrado correctamente.");
            } else {
                productoService.update(producto);
                redirectAttributes.addFlashAttribute("success", "Producto actualizado correctamente.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace(); // <-- AGREGAR ESTA LÍNEA
            String msg = e.getMessage() != null ? e.getMessage() : "Error inesperado: " + e.getClass().getSimpleName();
            redirectAttributes.addFlashAttribute("error", msg); // <-- usar "msg" en vez de e.getMessage()
            if (producto.getProductoID() == null) {
                return "redirect:/managementProduct/add";
            }
            return "redirect:/managementProduct/edit/" + producto.getProductoID();
        }
        return "redirect:/managementProduct/list";
    }

    @GetMapping("/modify/{productoID}")
    public String editProduct(@PathVariable Integer productoID, Model model) {

        var producto = productoService.getProducto(productoID)
                .orElseThrow(() -> new IllegalArgumentException("No existe el producto " + productoID));
        model.addAttribute("producto", producto);
        return "Product/edit";
    }

    @PostMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String mensaje = "Dato inavilitado";

        try {
            productoService.disableProduct(id);

            redirectAttributes.addFlashAttribute("todoOk", true);
            redirectAttributes.addFlashAttribute("mensaje", "Producto desabilitado correctamente.");

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("mensaje", e.getMessage());

        } catch (IllegalStateException e) {

            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("mensaje", e.getMessage());

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("mensaje", e.getMessage());

        }
        redirectAttributes.addFlashAttribute(titulo,
                messageSource.getMessage(null, Locale.getDefault()));

        return "redirect:/Product/list";
    }
}
