package com.itsqmet.ejemplotaller2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "matriculas")
public class matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de matrícula es obligatorio")
    @Size(max = 20, message = "La matrícula no puede superar los 20 caracteres")
    @Column(nullable = false, unique = true)
    private String numeroMatricula;

    @NotNull(message = "La fecha de emisión es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaEmision;

    @NotNull(message = "La fecha de caducidad es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaCaducidad;

    @NotBlank(message = "La provincia es obligatoria")
    @Size(max = 50, message = "La provincia no puede superar los 50 caracteres")
    @Column(nullable = false)
    private String provincia;

    @NotBlank(message = "El estado de la matrícula es obligatorio")
    @Column(nullable = false)
    private String estado;


    // RELACION 1:1
    @OneToOne
    @JoinColumn(name = "auto_id", nullable = false, unique = true)
    @JsonBackReference("auto-matricula")
    private auto auto;


    public matricula() {
    }

    public matricula(Long id, String numeroMatricula, LocalDate fechaEmision, LocalDate fechaCaducidad, String provincia, String estado, auto auto) {
        this.id = id;
        this.numeroMatricula = numeroMatricula;
        this.fechaEmision = fechaEmision;
        this.fechaCaducidad = fechaCaducidad;
        this.provincia = provincia;
        this.estado = estado;
        this.auto = auto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public auto getAuto() {
        return auto;
    }

    public void setAuto(auto auto) {
        this.auto = auto;
    }
}
