package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

public class Profile {
    String name;
    int highscore;

    public Profile(String name, int i) {
        this.name = name;
        this.highscore = 0;
    }

    //beolvassa a Saved fileból az értékeket
    private ArrayList<Profile> GetArray() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Profile> accountList = accountList = new ArrayList<>();;
        try {
            Profile list[] = objectMapper.readValue("Saved.json", Profile[].class);
            for (int i = 0; i < list.length; i++) {
                accountList.add(list[i]);
            }
        } catch (Exception e) {

        }
        return accountList;
    }

    //beleírja a fileba az értéket ha nyer
    public void WinPushArray(String name)
    {
        ArrayList<Profile> accountList = GetArray();
        int counter = 0;
        while (counter < accountList.size() && name != accountList.get(counter).getName()) {
            counter++;
        }
        if(counter == accountList.size())
        {
            accountList.add(new Profile(this.name = name, this.highscore = 1));
        } else {
            this.name = accountList.get(counter).getName();
            this.highscore = accountList.get(counter).getHighscore() + 1;
        }
        //fájlba írás
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            for(int i = 0; i < accountList.size(); i++)
            {
             objectMapper.writeValueAsString(accountList.get(i));
            }
        } catch (Exception e) {

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
