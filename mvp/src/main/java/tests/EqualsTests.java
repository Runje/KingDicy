package tests;

import com.example.Model.Player;
import com.example.OldModel.Board;
import com.example.OldModel.BoardElement;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Thomas on 26.05.2016.
 */
public class EqualsTests
{
    @Test
    public void playerEquals()
    {
        Player thomas = new Player("1", "Thomas", true);
        Player milena = new Player("2", "Milena", false);
        Assert.assertTrue(thomas.equals(thomas));
        Assert.assertFalse(thomas.equals(milena));
    }

    @Test
    public void boardEquals()
    {
        Board board1 = Board.createElementsBoard(new int[] {1,1,1,2,2,2,3,3,3});
        Board board2 = Board.createElementsBoard(new int[] {1,1,1,2,2,2,3,3,3});
        Board board3 = Board.createElementsBoard(new int[] {1,1,1,2,2,2,3,3,4});
        Assert.assertTrue(board1.equals(board2));
        Assert.assertFalse(board1.equals(board3));
    }
}
