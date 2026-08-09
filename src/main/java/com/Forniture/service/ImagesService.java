package com.Forniture.service;

import com.Forniture.domain.Producto;
import com.Forniture.domain.ProductoImagen;
import com.Forniture.repository.ImagesRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagesService {

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @Autowired
    private ImagesRepository imagesRepository;

    public ImagesService(FirebaseStorageService firebaseStorageService, ImagesRepository imagesRepository) {
        this.firebaseStorageService = firebaseStorageService;
        this.imagesRepository = imagesRepository;

    }

    public void save(MultipartFile[] imagenFile, Producto producto) {
        if (imagenFile == null || imagenFile.length == 0) {
            return;
        }

        System.out.println("Cantidad de archivos recibidos: " + imagenFile.length);

        boolean esPrimera = true;
        int index = 0;

        for (MultipartFile file : imagenFile) {
            if (!file.isEmpty()) {
                try {
                    String rutaImage = firebaseStorageService.uploadImage(
                            file, "products", producto.getProductoID(), index
                    );

                    ProductoImagen image = new ProductoImagen();
                    image.setUrlImagen(rutaImage);
                    image.setProducto(producto);
                    image.setPortada(esPrimera);
                    image.setNombreArchivo(producto.getProductoNombre());
                    image.setFechaCreacion(LocalDateTime.now());

                    imagesRepository.save(image);

                    esPrimera = false;
                    index++;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
