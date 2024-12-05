package com.example.controller;


import com.example.modelo.Producto;
import com.example.repository.ProductoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;





@RestController
public class ProductoController {


    @Autowired
    private   ProductoRepositorio repositorio;

    public ProductoController(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }


    @GetMapping("/producto")
    public List<Producto> obtenerTodos() {
        List<Producto> productos = repositorio.findAll();
        return productos;
    }


    @GetMapping("/producto/{id}")
    public Producto obtenerUno(@PathVariable Long id) {
        return repositorio.findById(id).orElse(null);
    }



    @PostMapping("/productos")
    public Producto crearProducto(@RequestBody Producto producto) {
        return repositorio.save(producto);
    }


    @PutMapping("/producto/{id}")
    public Producto actualizarProducto(@RequestBody Producto nuevoProducto,@PathVariable Long id) {
        if (repositorio.existsById(id)) {
                nuevoProducto.setId(id);
                nuevoProducto.se
            return repositorio.save(nuevoProducto);
        } else {
            return null;
        }
    }


    @DeleteMapping("/producto/{id}")
    public Producto eliminarProducto(@PathVariable Long id) {
        if (repositorio.existsById(id)) {
            Producto producto = repositorio.findById(id).get();
            assert producto != null;
            repositorio.delete(producto);
            return producto;

        } else {
            return null;
        }
    }





}
