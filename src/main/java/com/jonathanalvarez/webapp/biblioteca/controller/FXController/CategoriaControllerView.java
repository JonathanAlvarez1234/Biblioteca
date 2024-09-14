package com.jonathanalvarez.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jonathanalvarez.webapp.biblioteca.model.Categoria;
import com.jonathanalvarez.webapp.biblioteca.service.CategoriaService;
import com.jonathanalvarez.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class CategoriaControllerView implements Initializable {

    @FXML
    TextField tfId, tfNombre;
    @FXML
    Button btnGuardar, btnLimpiar, btnRegresar, btnEliminar;
    @FXML
    TableView tblCategorias;
    @FXML
    TableColumn colId, colNombre;

    @Setter
    private Main stage;

    @Autowired
    CategoriaService categoriaService;

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (tfId.getText().isBlank()) {
                agregarCategoria();
            } else {
                editarCategoria();
            }
        } else if (event.getSource() == btnLimpiar) {
            vaciarForm();
        } else if (event.getSource() == btnRegresar) {
            stage.indexView();
        } else if (event.getSource() == btnEliminar) {
            eliminarCategoria();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    
    public void cargarDatos() {
        tblCategorias.setItems(listaCategorias());
        colId.setCellValueFactory(new PropertyValueFactory<Categoria, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombreCategoria"));
    }

    public ObservableList<Categoria> listaCategorias() {
        return FXCollections.observableList(categoriaService.listarCategorias());
    }

    public void agregarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void cargarForm() {
        Categoria categoria = (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
        if (categoria != null) {
            tfId.setText(categoria.getId().toString());
            tfNombre.setText(categoria.getNombreCategoria());
        }
    }

    public void vaciarForm() {
        tfId.clear();
        tfNombre.clear();
    }

    public void editarCategoria() {
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void eliminarCategoria() {
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarDatos();
    }

}
    
