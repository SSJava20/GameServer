package server.games;

import server.ServerThread;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: stvad
 * Date: 18.03.12
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class BattleshipGame extends Game
{
    public BattleshipGame(ServerThread firstPlayer, ServerThread secondPlayer)
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
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void Move(ServerThread sender, Point coords)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
