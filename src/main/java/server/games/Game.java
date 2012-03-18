package server.games;

import java.awt.Point;

import server.GameState;
import server.ServerThread;

public class Game
{
    protected final int WinCombination = 3;
    protected ServerThread firstPlayer;
    protected ServerThread secondPlayer;
    protected GameState State;
    protected int MovesCount;

    public GameState getState()
    {
        return State;
    }

    public void setState(GameState state)
    {
        State = state;
    }

    public Game(ServerThread firstPlayer, ServerThread secondPlayer)
    {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        start();
        SendStates();
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

    protected char reverseChar(char toRev)
    {
        if(toRev == 'x')
            return '0';
        if(toRev =='0')
            return 'x';

        return 0;
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
        while(tCord.getX() < State.Rows && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
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
        while(tCord.getY() < State.Columns && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
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

        while(tCord.getX() >=0 && tCord.getY() >=0 && tCord.getX() < State.Rows && tCord.getY() < State.Columns && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
        {
            count++;
            tCord.y--;
            tCord.x--;
        }

        tCord.y = moveCord.y + 1;
        tCord.x = moveCord.x + 1;
        while(tCord.getX() < State.Rows && tCord.getY() < State.Columns && tCord.getX() >=0 && tCord.getY() >=0 && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
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

        while(tCord.getX() >=0 && tCord.getY() >=0 && tCord.getX() < State.Rows && tCord.getY() < State.Columns && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
        {
            count++;
            tCord.y--;
            tCord.x++;
        }

        tCord.y = moveCord.y + 1;
        tCord.x = moveCord.x - 1;
        while(tCord.getX() < State.Rows && tCord.getY() < State.Columns && tCord.getX() >=0 && tCord.getY() >=0 && State.Board[tCord.x][tCord.y] == State.getCurrentPlayer())
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

        if(MovesCount == State.Rows*State.Columns)
        {
            State.WhoWon = 'd';
        }

    }

    protected void SendStates()
    {
        firstPlayer.sendGameState(State);
        secondPlayer.sendGameState(State);
    }

    protected void start()
    {
        State = new GameState();
        char cp;
        if(Math.random()*2 == 1)
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

    public void Surrender(ServerThread sender)
    {
        State.setWhoWon(reverseChar(sender.getMark()));
        SendStates();
    }
}