package com.mercado.circular.model;
import com.mercado.circular.security.entity.Usuario;
import javax.persistence.*;

@Entity
public class Peces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pecesId;

    private String nombre;
    @Lob
    private String descripcion;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_nombre_usuario", referencedColumnName = "nombreUsuario")
    private Usuario user;
}