package edu.upc.eetac.dsa;

import java.util.*;

import edu.upc.eetac.dsa.classes.*;
import org.apache.log4j.Logger;

import javax.jws.soap.SOAPBinding;

public class GameManagerImpl implements GameManager {

    final static Logger log = Logger.getLogger(GameManagerImpl.class.getName());


    private static GameManager instance;

    private int numPuntos;
    private List<PuntoInteres> puntosInteres;
    private LinkedList<PuntoInteres> puntosPasados;
    private LinkedList<User> usuariosPunto;
    private LinkedList<User> users;

    private GameManagerImpl(){
        puntosInteres = new LinkedList<>();
        puntosPasados = new LinkedList<>();
        usuariosPunto = new LinkedList<>();
        users = new LinkedList<>();
    }
    /**Singleton**/
    public static GameManager getInstance(){
        if(instance==null){
            instance = new GameManagerImpl();
        }
        return instance;
    }

    public void clear(){
        instance = null;
        puntosInteres.clear();
        puntosPasados.clear();
        usuariosPunto.clear();
        users.clear();
    }

    public int numUsers(){
        log.info("Numero de ususarios: " +this.users.size());
        return this.users.size();
    }
    public int numPuntosInteres(){
        log.info("Numero de puntos: " +this.puntosInteres.size());
        return this.puntosInteres.size();
    }
    public void addPuntoInteres(String puntoId, String type){
        this.puntosInteres.add(new PuntoInteres(puntoId, type));
    }
    public void addUser(String userId, String name){
        this.users.add(new User(userId, name));
    }

    public LinkedList<User> getUsersSortedByName(){
        LinkedList<User> res = users;
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return res;
    }

    public User getUserInfo(String userId) throws UserNotFoundException {
        User theUser = null;
        for (int i = 0; i<users.size(); i++){
            if (userId.equals(users.get(i).getUserId())){
                theUser = users.get(i);
                log.info("Usuario: " +theUser);
            }
        }
        if (theUser != null){
            return theUser;
        }
        else{
            log.error("El usuario no existe");
            throw new UserNotFoundException();
        }
    }

    public void pasarPunto(String userId, String puntoId) throws UserNotFoundException, PuntoNotFoundException {
        PuntoInteres punto = null;
        for (int i = 0; i<puntosInteres.size(); i++){
            if (puntoId.equals(puntosInteres.get(i).getPuntoId())){
                punto = puntosInteres.get(i);
                log.info("Punto de interes: " +punto);
            }
        }
        User theUser = null;
        for (int i = 0; i<users.size(); i++){
            if (userId.equals(users.get(i).getUserId())){
                theUser = users.get(i);
                log.info("Usuario: " +theUser);
            }
        }
        if (punto != null){
            if(theUser != null){
                theUser.addPuntoPasado(punto);
                punto.addUser(theUser);
            }
            else{
                log.error("El usuario no existe");
                throw new UserNotFoundException();
            }
        }
        else{
            log.error("El punto no existe");
            throw new PuntoNotFoundException();
        }
    }

    public List<PuntoInteres> getPuntosInteresUser(String userId) throws UserNotFoundException {
        User theUser = null;
        LinkedList<PuntoInteres> res;
        for (int i = 0; i<users.size(); i++){
            if (userId.equals(users.get(i).getUserId())){
                theUser = users.get(i);
                log.info("Usuario: " +theUser);
            }
        }
        if (theUser != null) {
            res = theUser.getPuntosPasados();
        }
        else{
            log.error("El usuario no existe");
            throw new UserNotFoundException();
        }
        return res;
    }

    public List<User> getUsersPuntoInteres(String puntoId) throws PuntoNotFoundException{
        PuntoInteres punto = null;
        LinkedList<User> res;
        for (int i = 0; i<puntosInteres.size(); i++){
            if (puntoId.equals(puntosInteres.get(i).getPuntoId())){
                punto = puntosInteres.get(i);
                log.info("Punto de interes: " +punto);
            }
        }
        if (punto != null){
            res = punto.getUsuariosPunto();
        }
        else{
            log.info("El punto no existe");
            throw new PuntoNotFoundException();
        }
        return res;
    }

    public List<User> getUsersSortedByPuntos(){
        LinkedList<User> res = users;
        log.info("Lista antes de ordenar: "+res);
        Collections.sort(res, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getPuntosPasados().size()-o2.getPuntosPasados().size();
            }
        });
        log.info("Lista ordenada: "+res);
        return res;
    }
}
