package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void ProfileGenerating()
    {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Profile test = new Profile("test", 0);
                });
    }

    @Test
    void winPushArray() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Profile test = new Profile("test", 0);
                    test.WinPushArray(test.getName());
                });
    }
}