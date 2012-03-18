package server;

public class GameState
{
    protected int Rows;
    protected int Columns;

    public char CurrentPlayer;
    public char WhoWon;
    protected boolean connectionError;

    public char[][] Board;

    public GameState(char curPlayer, char WWon, boolean CErr, char[][] board)
    {
        CurrentPlayer = curPlayer;
        WhoWon = WWon;
        connectionError = CErr;
        Board = board;
    }

    public int getRows()
    {
        return Rows;
    }

    public void setRows(int rows)
    {
        Rows = rows;
    }

    public int getColumns()
    {
        return Columns;
    }

    public void setColumns(int columns)
    {
        Columns = columns;
    }

    public GameState(int row, int column)
    {
        Rows = row;
        Columns = column;

        Board = new char[Rows][Columns];
        StartInit();
    }

    public void StartInit()
    {
        WhoWon = ' ';
        for (int i = 0; i < Rows; i++)
            for (int j = 0; j < Columns; j++)
            {
                Board[i][j] = ' ';
            }
        connectionError = false;
    }

    public char[][] getBoard()
    {
        return Board;
    }

    public void setBoard(char[][] board)
    {
        Board = board;
    }

    public char getCurrentPlayer()
    {
        return CurrentPlayer;
    }

    public void setCurrentPlayer(char currentPlayer)
    {
        CurrentPlayer = currentPlayer;
    }

    public char getWhoWon()
    {
        return WhoWon;
    }

    public void setWhoWon(char whoWon)
    {
        WhoWon = whoWon;
    }

    public boolean isConnectionError()
    {
        return connectionError;
    }

    public void setConnectionError(boolean connectionError)
    {
        this.connectionError = connectionError;
    }
}
