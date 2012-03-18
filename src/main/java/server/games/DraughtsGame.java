package server.games;

import server.GameState;
import server.ServerThread;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: stvad
 * Date: 18.03.12
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class DraughtsGame extends Game
{
    public DraughtsGame(ServerThread firstPlayer, ServerThread secondPlayer)
    {
        super(firstPlayer, secondPlayer);
    }

    @Override
    protected void CheckForWin(Point moveCord)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void start()
    {
        State = new GameState(8,8);
        char cp;
        if(((int) Math.random()*2) == 1)
        {
            cp = 'x';
            firstPlayer.setMark(cp);
            secondPlayer.setMark('0');
        }
        else
        {
            cp = '0';
            firstPlayer.setMark(cp);
            secondPlayer.setMark('x');
        }

        State.setCurrentPlayer(cp);
    }

    @Override
    public void Move(ServerThread sender, Point from, Point to)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
