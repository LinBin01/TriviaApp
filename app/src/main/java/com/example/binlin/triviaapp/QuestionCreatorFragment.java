package com.example.binlin.triviaapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionCreatorFragment extends Fragment {

    @BindView(R.id.question_editText)
    protected EditText question;
    @BindView(R.id.correct_answer_editText)
    protected EditText correctAnswer;
    @BindView(R.id.first_wrong_answer_editText)
    protected EditText firstWrongAnswer;
    @BindView(R.id.second_wrong_answer_editText)
    protected EditText secondWrongAnswer;
    @BindView(R.id.third_wrong_answer_editText)
    protected EditText thirdWrongAnswer;

    private Callback callback;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_creator, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static QuestionCreatorFragment newInstance() {

        Bundle args = new Bundle();

        QuestionCreatorFragment fragment = new QuestionCreatorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.save_question_button)
    protected void saveQuestion() {

        if (TextUtils.isEmpty(question.getText()) || TextUtils.isEmpty(correctAnswer.getText()) || TextUtils.isEmpty(firstWrongAnswer.getText())
                || TextUtils.isEmpty(secondWrongAnswer.getText()) || TextUtils.isEmpty(thirdWrongAnswer.getText())) {
            Toast.makeText(getActivity(), "All fields are required!", Toast.LENGTH_SHORT).show();

        } else {
            String questionTitle = question.getText().toString();
            String correctAnswer = this.correctAnswer.getText().toString();
            String incorrectOne = firstWrongAnswer.getText().toString();
            String incorrectTwo = secondWrongAnswer.getText().toString();
            String incorrectThree = thirdWrongAnswer.getText().toString();

            // take variables created from user input and saves them in the Question object
            Question question = new Question(questionTitle, correctAnswer, incorrectOne, incorrectTwo, incorrectThree);
            callback.questionSaved(question);
        }
    }


    public void attachParent(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void questionSaved(Question question);

    }
}
