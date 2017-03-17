package com.utndds.personas;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;

import com.utndds.recetas.ComponenteReceta;
import com.utndds.recetas.Producto;
import com.utndds.recetas.Receta;

@Entity
public class GrupoDeUsuarios {

    @Id
    @GeneratedValue
    long grupo_id;

    private String nombre;
    @ManyToMany(cascade = {CascadeType.ALL})
    private Collection<Usuario> usuarios = new HashSet<Usuario>();
    @OneToMany(cascade = {CascadeType.ALL})
    private Collection<ComponenteReceta> recetasGrupales = new HashSet<ComponenteReceta>();
    @ElementCollection
    private Collection<Producto> preferencias = new HashSet<Producto>();
    @Transient
    private Collection<String> intereses = new HashSet<String>();

    public GrupoDeUsuarios() {
    }

    public GrupoDeUsuarios(String nombre, HashSet<Usuario> usuarios) {
        this.usuarios = usuarios;
        this.nombre = nombre;
    }

    public GrupoDeUsuarios(String nombre, HashSet<Usuario> usuarios,
                           Collection<ComponenteReceta> recetasGrupales, Collection<Producto> preferencias,
                           Collection<String> intereses) {
        this.usuarios = usuarios;
        this.nombre = nombre;
        this.recetasGrupales = recetasGrupales;
        this.preferencias = preferencias;
        this.intereses = intereses;
    }

    public String getNombre() {
        return nombre;
    }

    public Collection<String> getIntereses() {
        return intereses;
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean seLePuedeSugerir(Receta receta) {
        return usuarios.stream().allMatch(
                usuario -> usuario.cumpleCondiciones(receta))
                || preferencias.stream().anyMatch(
                preferencia -> receta.contienePreferencia(preferencia));
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuario.esUsuarioValido()) {
            usuarios.add(usuario);
        } else {
            throw new RuntimeException("El usuario no es valido");
        }
    }

    public void agregarPreferencia(Producto preferencia) {
        preferencias.add(preferencia);
    }

    public void quitarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public boolean contains(Usuario usuario) {
        return usuarios.contains(usuario);
    }

    public boolean contieneReceta(Receta receta) {
        return recetasGrupales.contains(receta);
    }

    public boolean cumpleCondiciones(Receta receta) {
        return usuarios.stream().allMatch(
                usuario -> usuario.cumpleCondiciones(receta));
    }

    public void agregar(ComponenteReceta receta) {
        recetasGrupales.add(receta);
    }

    public void quitar(Receta receta) {
        recetasGrupales.remove(receta);
    }

    public Collection<ComponenteReceta> getRecetas() {
        return recetasGrupales;

    }
}
