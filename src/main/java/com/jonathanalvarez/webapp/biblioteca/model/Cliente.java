package com.jonathanalvarez.webapp.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "Clientes")
public class Cliente {
    @Id
    private long dpi;
    @NotNull(message = "El Nombre No Puede Ser Nulo")
    private String nombre;
    private String apellido;
    private String telefono;

}
