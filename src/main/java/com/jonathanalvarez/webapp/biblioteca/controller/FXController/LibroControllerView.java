package com.jonathanalvarez.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import com.jonathanalvarez.webapp.biblioteca.model.Categoria;
import com.jonathanalvarez.webapp.biblioteca.model.Libro;
import com.jonathanalvarez.webapp.biblioteca.service.LibroService;
import com.jonathanalvarez.webapp.biblioteca.system.Main;
import com.jonathanalvarez.webapp.biblioteca.util.EstadoLibro;
import com.jonathanalvarez.webapp.biblioteca.util.MethodType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

public class LibroControllerView implements Initializable {

    @FXML
    TextField tfId, tfIsbn, tfNombre, tfAutor, tfEditorial, tfdisponibilidad, tfEstanteria, tfCluster;
    @FXML
    TextArea taSipnosis;
    @FXML
    ComboBox cmbCategorias;
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar, btnRegresar;
    @FXML
    TableView tblLibros;
    @FXML
    TableColumn colId, colIsbn, colNombre,colSinopsis, colAutor, colEditorial, colDisponibilidad, colEstanteria, colCluster, colCategoria;

    @Setter
    private Main stage;

    @Autowired
    LibroService libroService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                agregarLibro();
            }else{
                editarLibro();
            }
        }else if(event.getSource() == btnRegresar){
            stage.indexView();
        }
    }

    public void cargarDatos(){
        tblLibros.setItems(listarLibros());
        colId.setCellFactory(new PropertyValueFactory<Libro, Long>("id"));
        colIsbn.setCellFactory(new PropertyValueFactory<Libro,String>("isbn"));
        colNombre.setCellFactory(new PropertyValueFactory<Libro,String>("nombre"));
        colSinopsis.setCellFactory(new PropertyValueFactory<Libro,String>("sinopsis"));
        colAutor.setCellFactory(new PropertyValueFactory<Libro,String>("autor"));
        colEditorial.setCellFactory(new PropertyValueFactory<Libro,String>("editorial"));
        colDisponibilidad.setCellFactory(new PropertyValueFactory<Libro,String>("disponibilidad"));
        colEstanteria.setCellFactory(new PropertyValueFactory<Libro,String>("numeroEstanteria"));
        colCluster.setCellFactory(new PropertyValueFactory<Libro,String>("cluster"));
        colCategoria.setCellFactory(new PropertyValueFactory<Libro, Categoria>("categoria"));
    }

    public ObservableList<Libro> listarLibros(){
        return FXCollections.observableList(libroService.listarLibros());
    }

    public void agregarLibro(){
        Libro libro = null;
        libro.setIsbn(tfIsbn.getText());
        libro.setNombre(tfNombre.getText());
        libro.setSinopsis(taSipnosis.getText());
        libro.setAutor(tfAutor.getText());
        libro.setEditorial(tfEditorial.getText());
        libro.setNumeroEstanteria(tfEstanteria.getText());
        libro.setCluster(tfCluster.getText());
        libroService.guardarLibro(libro, MethodType.POST);
        cargarDatos();
    }

    public void editarLibro(){
        Libro libro = libroService.buscarLibroPorId(Long.parseLong(tfId.getText()));
        libro.setIsbn(tfIsbn.getText());
        libro.setNombre(tfNombre.getText());
        libro.setSinopsis(taSipnosis.getText());
        libro.setAutor(tfAutor.getText());
        libro.setEditorial(tfEditorial.getText());
        libro.setNumeroEstanteria(tfEstanteria.getText());
        libro.setCluster(tfCluster.getText());
        libroService.guardarLibro(libro, MethodType.PUT);
        cargarDatos();
    }

    public void eliminarLibro(){
        Libro libro = libroService.buscarLibroPorId(Long.parseLong(tfId.getText()));
        libroService.eliminarLibro(libro);
        cargarDatos();
    }

}
