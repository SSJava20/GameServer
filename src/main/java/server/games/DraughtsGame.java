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
    protected char reverseChar(char toRev)
    {
        if(toRev == 'b')
            return 'w';
        if(toRev =='w')
            return 'b';

        return 0;
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

        if(canMove(sender, from, to))
        {
            State.Board[from.x][from.y] = ' ';
            State.Board[to.x][to.y] = sender.getMark();
            if(Math.abs(to.x - from.x) == 2)
            {
                if(State.Board[(from.x + to.x)/2][(from.y + to.y)/2] == 'w')
                    whiteKilled++;
                else
                    blackKilled++;

                State.Board[(from.x + to.x)/2][(from.y + to.y)/2] = ' ';
            }

            State.setCurrentPlayer(reverseChar(State.getCurrentPlayer()));
        }

        SendStates();
    }

    protected boolean canMove(ServerThread sender, Point from, Point to)
    {
        boolean result = false;

        if(State.Board[to.x][to.y] == ' ' && State.getWhoWon() == ' '
        && !(from.x < 0 || from.x > State.getRows() || to.x < 0 || to.x > State.getRows()) &&
        sender.getMark() == State.getCurrentPlayer() && sender.getMark() ==  State.Board[from.x][from.y] &&
        ((Math.abs(to.x - from.x) == 1 && Math.abs(to.y - from.y) == 1
        || (Math.abs(to.x - from.x) == 2 && Math.abs(to.y - from.y) == 2 && State.Board[(from.x + to.x)/2][(from.y + to.y)/2] == reverseChar(sender.getMark())) )))
        {
            // добавить условие на обязательный бой (facepalm)
            result = true;
        }

        return result;
    }
}
