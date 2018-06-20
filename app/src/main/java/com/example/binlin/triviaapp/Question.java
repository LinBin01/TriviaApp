package com.example.binlin.triviaapp;

public class Question {
    private String questionTitle;
    private String correctAnswer;
    private String wrongAnswerOne;
    private String wrongAnswerTwo;
    private String wrongAnswerThree;

    public Question(String questionTitle, String correctAnswer,String wrongAnswerOne, String wrongAnswerTwo,String wrongAnswerThree){
        this.questionTitle = questionTitle;
        this.correctAnswer = correctAnswer;
        this.wrongAnswerOne = wrongAnswerOne;
        this.wrongAnswerTwo = wrongAnswerTwo;
        this.wrongAnswerThree = wrongAnswerThree;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWrongAnswerOne() {
        return wrongAnswerOne;
    }

    public String getWrongAnswerTwo() {
        return wrongAnswerTwo;
    }

    public String getWrongAnswerThree() {
        return wrongAnswerThree;
    }
}
