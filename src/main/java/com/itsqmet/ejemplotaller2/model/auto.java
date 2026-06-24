package com.itsqmet.ejemplotaller2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import  com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "auto")
public class auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 50, message = "La marca no puede superar los 50 caracteres")
    @Column(nullable = false)
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 50, message = "El modelo no puede superar los 50 caracteres")
    @Column(nullable = false)
    private String modelo;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 1900, message = "El año debe ser válido")
    @Column(nullable = false)
    private Integer anio;

    @NotBlank(message = "El color es obligatorio")
    @Size(max = 30, message = "El color no puede superar los 30 caracteres")
    @Column(nullable = false)
    private String color;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(nullable = false)
    private Double precio;

    @NotBlank(message = "El número de chasis es obligatorio")
    @Size(max = 50, message = "El número de chasis no puede superar los 50 caracteres")
    @Column(nullable = false, unique = true)
    private String numeroChasis;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false)
    private String estado;

    //RELACION 1:1
    @OneToOne(mappedBy = "auto")
    @JsonManagedReference("auto-matricula")
    private matricula matricula;

    // RELACIÓN n:n
    @ManyToMany
    @JoinTable(
            name = "auto_accesorio",
            joinColumns = @JoinColumn(name = "auto_id"),
            inverseJoinColumns = @JoinColumn(name = "accesorio_id")
    )
    @JsonManagedReference("auto-accesorio")
    private List<accesorios> accesorios;


    //RELACION n:1
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference("cliente-auto")
    private cliente cliente;


    public auto() {
    }

    public auto(Long id, String marca, String modelo, Integer anio, String color, Double precio, String numeroChasis, String estado, matricula matricula, List<accesorios> accesorios, cliente cliente) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.precio = precio;
        this.numeroChasis = numeroChasis;
        this.estado = estado;
        this.matricula = matricula;
        this.accesorios = accesorios;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(matricula matricula) {
        this.matricula = matricula;
    }

    public List<accesorios> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(List<accesorios> accesorios) {
        this.accesorios = accesorios;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }
}
