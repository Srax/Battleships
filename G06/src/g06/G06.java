/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g06;

import battleship.interfaces.BattleshipsPlayer;
import tournament.player.PlayerFactory;

/**
 *
 * @author thoma
 */
public class G06 implements PlayerFactory<BattleshipsPlayer>{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public BattleshipsPlayer getNewInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getID() {
        return "G06";
    }

    @Override
    public String getName() {
        return "G06 Super duper gg easy game fam AI";
    }

    @Override
    public String[] getAuthors() {
        String[] res = {"Jonas", "Thomas", "Andread"};
        return res;
    }
    
}
