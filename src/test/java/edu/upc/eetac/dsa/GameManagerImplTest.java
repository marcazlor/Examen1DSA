package edu.upc.eetac.dsa;

import junit.framework.TestCase;
import edu.upc.eetac.dsa.classes.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameManagerImplTest{

    final static Logger log = Logger.getLogger(GameManagerImpl.class.getName());

    private GameManager gameManager;

    @Before
    public void setUp(){
        this.gameManager = GameManagerImpl.getInstance();
        this.gameManager.addUser("user1", "Juan");


        this.gameManager.addPuntoInteres("punto2","puerta");
        this.gameManager.addPuntoInteres("punto3","casillaX");


        Assert.assertEquals(2, this.gameManager.numPuntosInteres());
    }
    @After
    public void tearDown(){this.gameManager.clear();}

    @Test
    public void testAddUser(){
        this.gameManager.addUser("user102", "Edi");
        Assert.assertEquals(2, this.gameManager.numUsers());
    }
    @Test
    public void testAddPuntoInteres(){
        this.gameManager.addPuntoInteres("punto1", "CasillaY");
        Assert.assertEquals(3, this.gameManager.numPuntosInteres());
    }
    @Test
    public void testpasarPunto(String userId, String puntoId) throws UserNotFoundException, PuntoNotFoundException {
        try{
            this.gameManager.pasarPunto("user102", "punto2");
            Assert.assertEquals("user102", this.gameManager.getUsersPuntoInteres("punto2"));
        }
        catch (UserNotFoundException | PuntoNotFoundException e){
            log.error("El punto o el usuario no existen: "+e.getMessage());
        }
    }
    @Test
    public void testgetUserInfo(String userId) throws UserNotFoundException {
        try{
            this.gameManager.getUserInfo("user102");
            Assert.assertEquals("Edi", this.gameManager.getUserInfo("user102"));
        }
        catch (UserNotFoundException e){
            log.error("El usuario no existe: "+e.getMessage());
        }
    }
    @Test
    public void testgetPuntosInteresUser(String userId) throws UserNotFoundException {
        try{
            this.gameManager.getPuntosInteresUser("user102");
            Assert.assertEquals("punto2", this.gameManager.getPuntosInteresUser("user102"));
        }
        catch (UserNotFoundException e){
            log.error("El usuario no existen: "+e.getMessage());
        }
    }

}