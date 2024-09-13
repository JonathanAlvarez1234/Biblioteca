package com.jonathanalvarez.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import com.jonathanalvarez.webapp.biblioteca.model.Cliente;
import com.jonathanalvarez.webapp.biblioteca.service.ClienteService;
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

public class ClienteControllerView implements Initializable {

    @FXML
    TextField tfId, tfNombre, tfApellido, tfTelefono;
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar, btnRegresar;
    @FXML
    TableView tblClientes;
    @FXML
    TableColumn colId, colNombre, colApellido, colTelefono;

    @Setter
    private Main stage;

    @Autowired
    ClienteService clienteService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                agregarCliente();
            }else{
                editarCliente();
            }
        }else if(event.getSource() == btnRegresar){
            stage.indexView();
        }
    }

    public void cargarDatos(){
        tblClientes.setItems(listarClientes());
        colId.setCellFactory(new PropertyValueFactory<Cliente,Long>("dpi"));
        colNombre.setCellFactory(new PropertyValueFactory<Cliente,String>("nombre"));
        colApellido.setCellFactory(new PropertyValueFactory<Cliente,String>("apellido"));
        colTelefono.setCellFactory(new PropertyValueFactory<Cliente,String>("telefono"));
    }

    public ObservableList<Cliente> listarClientes(){
        return FXCollections.observableList(clienteService.listarClientes());
    }

    public void agregarCliente(){
        Cliente cliente = null;
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void editarCliente(){
        Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(tfId.getText()));
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void eliminarCliente(){
        Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(tfId.getText()));
        clienteService.eliminarCliente(cliente);
        cargarDatos();
    }

}
