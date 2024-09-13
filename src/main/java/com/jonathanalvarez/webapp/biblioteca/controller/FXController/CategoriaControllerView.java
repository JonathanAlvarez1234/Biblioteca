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
    Button btnGuardar,btnLimpiar,btnEliminar, btnRegresar;
    @FXML
    TableView tblCategoria;
    @FXML
    TableColumn colId,colNombre;

    @Setter
    private Main stage;

    @Autowired
    CategoriaService categoriaService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       cargarDatos();
       
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                agregarCategoria();
            }else{
                editarCategoria();
            }
        }else if(event.getSource() == btnRegresar){
            stage.indexView();
        }
    }

    public ObservableList<Categoria> listarCategorias(){
        return FXCollections.observableList(categoriaService.listarCategorias());
    }

    public void cargarDatos(){
        tblCategoria.setItems(listarCategorias());
        colId.setCellValueFactory(new PropertyValueFactory<Categoria,Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombreCategoria"));
    }

    public void cargarFormEditar(){
        Categoria categoria = (Categoria)tblCategoria.getSelectionModel().getSelectedItem();
        tfId.setText(Long.toString(categoria.getId()));
        tfNombre.setText(categoria.getNombreCategoria());
    }


    public void agregarCategoria(){
        Categoria categoria = null;
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void editarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void eliminarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarDatos();
    }


}
    
