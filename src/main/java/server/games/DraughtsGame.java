package server.games;

import server.GameState;
import server.ServerThread;

import javax.management.BadAttributeValueExpException;
import java.awt.*;
import java.util.zip.DataFormatException;

/**
 * Created by IntelliJ IDEA.
 * User: stvad
 * Date: 18.03.12
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class DraughtsGame extends Game
{
    
    int blackKilled;
    int whiteKilled;
    
    public DraughtsGame(ServerThread firstPlayer, ServerThread secondPlayer)
    {
        super(firstPlayer, secondPlayer);
        blackKilled = 0;
        whiteKilled = 0;
    }

    @Override
    protected void CheckForWin(Point moveCord)
    {
        if(blackKilled == 12)
            State.setWhoWon('b');
        else if(whiteKilled == 12)
            State.setWhoWon('w');
    }

    @Override
    protected void start()
    {
        State = new GameState(8,8);
        char cp;
        if(((int) Math.random()*2) == 1)
        {
            cp = 'b';
            firstPlayer.setMark(cp);
            secondPlayer.setMark('w');
        }
        else
        {
            cp = 'w';
            firstPlayer.setMark(cp);
            secondPlayer.setMark('b');
        }

        for(int i = 0; i < State.getColumns(); i++)
            for(int j = 0; j < State.getRows()/2 - 1; j++)
            {
                if((j%2 == 0 && i%2 == 0) || (j%2 == 1 && i%2 == 1))
                    State.getBoard()[i][j] = firstPlayer.getMark();
            }

        for(int i = 0; i < State.getColumns(); i++)
            for(int j = State.getRows()/2 + 2; j < State.getRows(); j++)
            {
                if((j%2 == 0 && i%2 == 0) || (j%2 == 1 && i%2 == 1))
                    State.getBoard()[i][j] = firstPlayer.getMark();
            }

        State.setCurrentPlayer(cp);
    }

    @Override
    public void Move(ServerThread sender, Point from, Point to)
    {
        try
        {
            if(from.x < 0 || from.x > State.getRows() || to.x < 0 || to.x > State.getRows()) 
                throw new BadAttributeValueExpException("Pechalka;(");
            
            
            
        } catch (BadAttributeValueExpException e)
        {
            SendStates();
        }

        if(State.Board[to.x][to.y] == ' ' && State.getWhoWon() == ' ' && !(from.x < 0 || from.x > State.getRows() || to.x < 0 || to.x > State.getRows()))
            if(sender.getMark() == State.getCurrentPlayer())
            {
                State.Board[to.x][to.y] = sender.getMark();
                CheckForWin(to);
                State.setCurrentPlayer((State.getCurrentPlayer() == 'b')? 'w':'b');
            }

        SendStates();
    }
}
