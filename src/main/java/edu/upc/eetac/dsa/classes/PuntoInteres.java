package edu.upc.eetac.dsa.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;

public class PuntoInteres {
    //Attributes
    public String puntoId;
    public String type;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public LinkedList<User> usuariosPunto;

    public PuntoInteres(){

    }

    public PuntoInteres(String puntoID, String type) {
        this.puntoId = puntoID;
        this.type = type;
        this.usuariosPunto = new LinkedList<>();
    }

    public String getPuntoId() {
        return puntoId;
    }

    public void setPuntoId(String puntoId) {
        this.puntoId = puntoId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LinkedList<User> getUsuariosPunto() {
        return usuariosPunto;
    }

    public void setUsuariosPunto(LinkedList<User> usuariosPunto) {
        this.usuariosPunto = usuariosPunto;
    }

    public void addUser(User u){this.usuariosPunto.add(u);}
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public int getNumUsers(){return this.usuariosPunto.size();}
}
