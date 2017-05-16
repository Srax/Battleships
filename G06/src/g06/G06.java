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
public class G06 implements PlayerFactory<BattleshipsPlayer> {

    public G06() {
    }

    @Override
    public BattleshipsPlayer getNewInstance() {
        return new MyShooter();
    }

    @Override
    public String getID() {
        return "G06";
    }

    @Override
    public String getName() {
        return"G06 Shooter";
    }

    @Override
    public String[] getAuthors() {
        String[] res = {"Jonas", "Thomas", "Andreas"};
        return res;
    }

}
