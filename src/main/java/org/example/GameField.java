package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class GameField {
    //változók deklarálása
    int[][] board = new int[7][6];

    //tábla generálása
    public GameField() {
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                board[i][j] = 0;
            }
        }
        Visual();
    }

    //Tábla importálása
    public GameField(String nev){
        try {
            nev.concat(".json");
            ObjectMapper objectMapper = new ObjectMapper();
            GameField field = objectMapper.readValue(new File(nev), GameField.class);
            this.board = field.board;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Visual();
    }
    public void Save()
    {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("GameSave.json"), GameField.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Visual();
    }

    //Játékos lépései
    public int Step(int column)
    {
        int i = 0;
        while(i < 6 && board[column][i] != 0)
        {
            i++;
        }
        if(i == 6)
        {
            System.out.println("Az oszlop megtelt, kérem válasszon egy másikat!");
        } else{
            board[column][i] = 1;
            if(IsEnd(column, i, true) != 0)
            {
                return 0;
            }
            else{
                return StepAI();
            }
        }
        return 1;
    }

    //AI lépései
    private int StepAI()
    {
        int genNumber = (int)(Math.random() * 7) + 1;
        int i = 0;
        while(i < 6 && board[genNumber][i] != 0)
        {
            i++;
        }
        if(i == 6)
        {
            StepAI();
        } else{
            board[genNumber][i] = 2;
        }
        Visual();
        if(IsEnd(genNumber, i, false) != 0)
        {
            return 2;
        } else{
            return 0;
        }
    }

    //Vége-e a játéknak
    public int IsEnd(int column, int row, boolean isPlayer)
    {
        //változók létrehozása
        int preferedChacacter = 2;
        if(isPlayer)
        {
            preferedChacacter = 1;
        }
        // Vizszintes
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == preferedChacacter &&
                        board[i][j + 1] == preferedChacacter &&
                        board[i][j + 2] == preferedChacacter &&
                        board[i][j + 3] == preferedChacacter) {
                    return preferedChacacter;
                }
            }
        }

        // Fuggoleges
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == preferedChacacter &&
                        board[i + 1][j] == preferedChacacter &&
                        board[i + 2][j] == preferedChacacter &&
                        board[i + 3][j] == preferedChacacter) {
                    return preferedChacacter;
                }
            }
        }

        // Balrol jobbra atlosan
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == preferedChacacter &&
                        board[i + 1][j + 1] == preferedChacacter &&
                        board[i + 2][j + 2] == preferedChacacter &&
                        board[i + 3][j + 3] == preferedChacacter) {
                    return preferedChacacter;
                }
            }
        }

        // Jobbrol balra atlosan
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j < 6; j++) {
                if (board[i][j] == preferedChacacter &&
                        board[i + 1][j - 1] == preferedChacacter &&
                        board[i + 2][j - 2] == preferedChacacter &&
                        board[i + 3][j - 3] == preferedChacacter) {
                    return preferedChacacter;
                }
            }
        }

        return 0;
    }

    //Pálya állásának kiíratása
    private void Visual()
    {
        String abc = " ABCDEFG";
        for(int i = 5; i > 0; i--)
        {
            System.out.print(i + " ");
            for(int j = 0; j < 7; j++)
            {
                System.out.printf(board[j][i] + " ");
            }
            System.out.println();
        }
        for(int i = 0; i < abc.length(); i++)
        {
            System.out.print(abc.charAt(i) + " ");
        }
    }
}
