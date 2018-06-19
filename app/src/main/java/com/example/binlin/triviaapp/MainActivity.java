package com.example.binlin.triviaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private QuestionCreatorFragment questionCreatorFragment;
    private TakeQuizFragment takeQuizFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_question_button)
    protected void addQuestionClicked(){
        questionCreatorFragment = questionCreatorFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, questionCreatorFragment).commit();
    }

    @OnClick(R.id.take_quiz_button)
    protected void takeQuiz(){

    }

    @OnClick(R.id.delete_quiz_button)
    protected void deleteQuiz(){

    }


}
