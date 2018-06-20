package com.example.binlin.triviaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements QuestionCreatorFragment.Callback{

    private QuestionCreatorFragment questionCreatorFragment;
    private List<Question> questionList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        questionList = new ArrayList<>();
    }

    @OnClick(R.id.add_question_button)
    protected void addQuestionClicked(){
        questionCreatorFragment = questionCreatorFragment.newInstance();
        questionCreatorFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, questionCreatorFragment).commit();
    }

    @OnClick(R.id.take_quiz_button)
    protected void takeQuiz(){

    }

    @OnClick(R.id.delete_quiz_button)
    protected void deleteQuiz(){

    }


    @Override
    public void questionSaved(Question question) {
        // takes the question object that was passed in and saves it to the questions ArrayList
        questionList.add(question);

        // Shows a Toast to the user that lets them know the question was saved.
        Toast.makeText(this, "Question Saved", Toast.LENGTH_SHORT).show();

        // remove the fragment from the frameLayout
        getSupportFragmentManager().beginTransaction().remove(questionCreatorFragment).commit();

    }

}
