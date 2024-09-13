package com.jonathanalvarez.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.jonathanalvarez.webapp.biblioteca.model.Categoria;
import com.jonathanalvarez.webapp.biblioteca.service.CategoriaService;
import com.jonathanalvarez.webapp.biblioteca.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;   
import lombok.Setter;

@Component
public class IndexController implements Initializable {

    @FXML
    MenuItem btnCategorias, btnClientes, btnEmpleados, btnLibros, btnPrestamos;

    @Setter
    private Main stage;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCategorias){
            stage.CategoriaView();
        }else if(event.getSource() == btnClientes){
            stage.ClienteView();
        }else if(event.getSource() == btnEmpleados){
            stage.EmpleadoView();
        }else if(event.getSource() == btnLibros){
            stage.LibroView();
        }else if(event.getSource() == btnPrestamos){
            
        }
    }

}
