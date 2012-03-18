package server.games;

import server.GameState;
import server.ServerThread;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: stvad
 * Date: 18.03.12
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class TicTacGame extends Game
{
    protected final int WinCombination = 3;
    protected int MovesCount;


    public TicTacGame(ServerThread firstPlayer, ServerThread secondPlayer)
    {
        super(firstPlayer, secondPlayer);
    }

    protected void CheckForWin(Point moveCord)
    {
        int count = 0;
        Point tCord = new Point(moveCord);

        while(tCord.getX() >=0 && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
        {
            count++;
            tCord.x--;
        }

        tCord.x = moveCord.x + 1;
        while(tCord.getX() < State.getRows() && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
        {
            count++;
            tCord.x++;
        }

        if(count == WinCombination)
        {
            State.setWhoWon(State.getCurrentPlayer());
            return;
        }

        count = 0;
        tCord.y = moveCord.y;
        tCord.x = moveCord.x;
        while(tCord.getY() >=0 && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
        {
            count++;
            tCord.y--;
        }

        tCord.y = moveCord.y + 1;
        while(tCord.getY() < State.getColumns() && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
        {
            count++;
            tCord.y++;
        }

        if(count == WinCombination)
        {
            State.setWhoWon(State.getCurrentPlayer());
            return;
        }

        count = 0;
        tCord.y = moveCord.y;
        tCord.x = moveCord.x;

        while(tCord.getX() >=0 && tCord.getY() >=0 && tCord.getX() < State.getRows() && tCord.getY() < State.getColumns() && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
        {
            count++;
            tCord.y--;
            tCord.x--;
        }

        tCord.y = moveCord.y + 1;
        tCord.x = moveCord.x + 1;
        while(tCord.getX() < State.getRows() && tCord.getY() < State.getColumns() && tCord.getX() >=0 && tCord.getY() >=0 && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
        {
            count++;
            tCord.y++;
            tCord.x++;
        }

        if(count == WinCombination)
        {
            State.setWhoWon(State.getCurrentPlayer());
            return;
        }

        count = 0;
        tCord.y = moveCord.y;
        tCord.x = moveCord.x;

        while(tCord.getX() >=0 && tCord.getY() >=0 && tCord.getX() < State.getRows() && tCord.getY() < State.getColumns() && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
        {
            count++;
            tCord.y--;
            tCord.x++;
        }

        tCord.y = moveCord.y + 1;
        tCord.x = moveCord.x - 1;
        while(tCord.getX() < State.getRows() && tCord.getY() < State.getColumns() && tCord.getX() >=0 && tCord.getY() >=0 && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
        {
            count++;
            tCord.y++;
            tCord.x--;
        }

        if(count == WinCombination)
        {
            State.setWhoWon(State.getCurrentPlayer());
            return;
        }

        if(MovesCount == State.getRows()*State.getColumns())
        {
            State.WhoWon = 'd';
        }

    }

    protected void start()
    {
        State = new GameState(3,3);
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

        MovesCount = 0;

        State.setCurrentPlayer(cp);
    }

    public void Move(ServerThread sender, Point coords)
    {
        if(State.Board[coords.x][coords.y] == ' ' && State.getWhoWon() == ' ')
            if(sender.getMark() == State.getCurrentPlayer())
            {
                State.Board[coords.x][coords.y] = sender.getMark();
                MovesCount++;
                CheckForWin(coords);
                State.setCurrentPlayer(reverseChar(State.getCurrentPlayer()));
            }

        SendStates();
    }
}
