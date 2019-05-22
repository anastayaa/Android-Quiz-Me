package com.tayaa.quizme.models;

/**
 * Created by java on 22/05/2019.
 */

public class Question {

    private int id;
    private boolean answer;

    public Question(int id, boolean answer){
        this.id=id;
        this.answer=answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
