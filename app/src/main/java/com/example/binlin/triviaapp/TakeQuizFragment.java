package com.example.binlin.triviaapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.binlin.triviaapp.MainActivity.QUESTION_LIST;

public class TakeQuizFragment extends Fragment {

    @BindView(R.id.question_textView)
    protected TextView quizQuestion;
    @BindView(R.id.first_answer_button)
    protected Button firstAnswerButton;
    @BindView(R.id.second_answer_button)
    protected Button secondAnswerButton;
    @BindView(R.id.third_answer_button)
    protected Button thirdAnswerButton;
    @BindView(R.id.fourth_answer_button)
    protected Button fourthAnswerButton;

    private List<Question> questionsList;
    private Question question;
    private int questionsListPosition = 0;
    private int correctAnswers = 0;
    private QuizCallback quizCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_take_quiz, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static TakeQuizFragment newInstance() {

        Bundle args = new Bundle();

        TakeQuizFragment fragment = new TakeQuizFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        questionsList = getArguments().getParcelableArrayList(QUESTION_LIST);
        populateQuizContent();
    }

    private void populateQuizContent() {
        question = questionsList.get(questionsListPosition);
        quizQuestion.setText(question.getQuestionTitle());


        List<Button> buttonList = new ArrayList<>();
        buttonList.add(firstAnswerButton);
        buttonList.add(secondAnswerButton);
        buttonList.add(thirdAnswerButton);
        buttonList.add(fourthAnswerButton);

        // Just like with the button, this arrayList will take all of the possible answers and allow us to access them
        List<String> possibleAnswersList = new ArrayList<>();
        possibleAnswersList.add(question.getCorrectAnswer());
        possibleAnswersList.add(question.getWrongAnswerOne());
        possibleAnswersList.add(question.getWrongAnswerTwo());
        possibleAnswersList.add(question.getWrongAnswerThree());

        // This for each loop takes the arrayLists we made and actually allows us to randomize what answer goes on which button
        for (Button button : buttonList) {

            // Create a random number that will allow us to pick an answer from our arrayList
            int random = (int) Math.ceil(Math.random() * (possibleAnswersList.size() - 1));

            // Using the random number above we will set the text of the button by getting that item from the possible answers list.
            button.setText(possibleAnswersList.get(random));
            // To make sure we don't use the same answer twice we remove the possible answer from the list
            possibleAnswersList.remove(random);
        }
    }

    public void attachParent(QuizCallback callback) {
        quizCallback = callback;
    }

    public interface QuizCallback {
        void quizFinished(int correctAnswers);
    }

    private void checkAnswer(String answer) {
        questionsListPosition++;
        // if the input answer is equal to the correct answer, then the texView will tell user if they were correct and increment the correct answers
        if (question.getCorrectAnswer().equals(answer)) {
            quizQuestion.setText(R.string.correct_text);
            correctAnswers++;
        } else {
            quizQuestion.setText(getString(R.string.wrong_answer_text, question.getCorrectAnswer()));
        }
        disableAnswerButtons();
    }

    @OnClick(R.id.first_answer_button)
    protected void firstAnswerClicked() {
        checkAnswer(firstAnswerButton.getText().toString());
    }

    @OnClick(R.id.second_answer_button)
    protected void secondAnswerClicked() {
        checkAnswer(secondAnswerButton.getText().toString());
    }

    @OnClick(R.id.third_answer_button)
    protected void thirdAnswerClicked() {
        checkAnswer(thirdAnswerButton.getText().toString());
    }

    @OnClick(R.id.fourth_answer_button)
    protected void fourthAnswerClicked() {
        checkAnswer(fourthAnswerButton.getText().toString());
    }

    @OnClick(R.id.next_question_button)
    protected void nextButtonClicked() {
        enableAnswerButtons();
        if (questionsListPosition <= questionsList.size() - 1) {
            populateQuizContent();
        } else {
            // handling no more questions, taking users back to MainActivity
            quizCallback.quizFinished(correctAnswers);

        }
    }

    private void disableAnswerButtons() {
        firstAnswerButton.setEnabled(false);
        secondAnswerButton.setEnabled(false);
        thirdAnswerButton.setEnabled(false);
        fourthAnswerButton.setEnabled(false);

    }

    private void enableAnswerButtons() {
        firstAnswerButton.setEnabled(true);
        secondAnswerButton.setEnabled(true);
        thirdAnswerButton.setEnabled(true);
        fourthAnswerButton.setEnabled(true);
    }
}
