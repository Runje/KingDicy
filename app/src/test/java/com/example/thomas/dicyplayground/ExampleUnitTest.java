package com.example.thomas.dicyplayground;

import com.example.Model.Player;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest
{
    @Test
    public void addition_isCorrect() throws Exception
    {
        assertEquals(4, 2 + 2);
        Player pLayer = new Player("1", "Thomas", false);
        assertTrue(pLayer.equals(pLayer));
    }
}