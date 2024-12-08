package org.example;

import net.sf.saxon.s9api.streams.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class GameFieldTest {

    @BeforeEach
    void setUp() {
        GameField gameField = new GameField();
    }

    @Test
    void step() {
        GameField tg = new GameField();
        assertEquals(0, tg.Step(1));
    }
}