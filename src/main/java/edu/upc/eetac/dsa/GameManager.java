package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.classes.PuntoInteres;
import edu.upc.eetac.dsa.classes.PuntoNotFoundException;
import edu.upc.eetac.dsa.classes.User;
import edu.upc.eetac.dsa.classes.UserNotFoundException;

import java.util.List;

public interface GameManager {

    void addUser(String userId, String name);
    public int numPuntosInteres();
    public int numUsers();
    void clear();
    void addPuntoInteres(String puntoId, String type);
    void pasarPunto(String userId, String puntoId) throws PuntoNotFoundException, UserNotFoundException;
    List<User> getUsersSortedByName();
    User getUserInfo(String userId) throws UserNotFoundException;
    List<PuntoInteres> getPuntosInteresUser(String userId) throws UserNotFoundException;
    List<User> getUsersPuntoInteres(String puntoId) throws PuntoNotFoundException;
    List<User> getUsersSortedByPuntos();
}
