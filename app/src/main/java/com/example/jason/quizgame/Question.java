package com.example.jason.quizgame;
public class Question {
    private int mTextResId;
    private String mAnswer;
    private int mOptA;
    private int mOptB;
    private int mOptC;
    private int mOptD;
    private int hint;
    private boolean is_correct;

    public Question(int mTextResId, String mAnswer, int mOptA, int mOptB, int mOptC, int mOptD, int hint, boolean is_correct) {
        this.mTextResId = mTextResId;
        this.mAnswer = mAnswer;
        this.mOptA = mOptA;
        this.mOptB = mOptB;
        this.mOptC = mOptC;
        this.mOptD = mOptD;
        this.hint = hint;
        this.is_correct = is_correct;
    }

    public boolean is_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }

    public int getHint() {
        return hint;
    }

    public void setHint(int hint) {
        this.hint = hint;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public int getmOptA() {
        return mOptA;
    }

    public void setmOptA(int mOptA) {
        this.mOptA = mOptA;
    }

    public int getmOptB() {
        return mOptB;
    }

    public void setmOptB(int mOptB) {
        this.mOptB = mOptB;
    }

    public int getmOptC() {
        return mOptC;
    }

    public void setmOptC(int mOptC) {
        this.mOptC = mOptC;
    }

    public int getmOptD() {
        return mOptD;
    }

    public void setmOptD(int mOptD) {
        this.mOptD = mOptD;
    }
}
