package edu.upc.eetac.dsa.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;

public class User {
   public String userId;
   String name;
   @JsonIgnore
   @ApiModelProperty(hidden = true)
   public LinkedList<PuntoInteres> puntosPasados;

   public User(){

   }

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.puntosPasados = new LinkedList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<PuntoInteres> getPuntosPasados() {
        return puntosPasados;
    }

    public void setPuntosPasados(LinkedList<PuntoInteres> puntosPasados) {
        this.puntosPasados = puntosPasados;
    }
    public void addPuntoPasado(PuntoInteres p){ this.puntosPasados.add(p);}
}
