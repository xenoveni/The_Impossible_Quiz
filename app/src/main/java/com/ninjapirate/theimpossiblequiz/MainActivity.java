package com.ninjapirate.theimpossiblequiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtVw_countLabel;
    private TextView txtVw_questionLabel;
    private Button btn_answer1;
    private Button btn_answer2;
    private Button btn_answer3;
    private Button btn_answer4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            // {"Question", "Right Answer", "Choice 1", "Choice 2", "Choice 3"}
            {"Colour", "Black", "Red", "Blue", "Green"},
            {"Are you enjoying this app?", "Best App Ever", "Yes", "Totally", "Total Joy"},
            {"I ran out of Questions", "OK!" , "OK", "OK?", "OK..."},
            {"Choose the first Answer", "First Answer", "Second Answer", "Third Answer", "Fourth Answer"},
            {"Click the button.", "Button.", "Button", "Button..", "Button?"}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVw_countLabel = (TextView)findViewById(R.id.txtVw_countLabel);
        txtVw_questionLabel = (TextView)findViewById(R.id.txtVw_questionLabel);
        btn_answer1 = (Button)findViewById(R.id.btn_answer1);
        btn_answer2 = (Button)findViewById(R.id.btn_answer2);
        btn_answer3 = (Button)findViewById(R.id.btn_answer3);
        btn_answer4 = (Button)findViewById(R.id.btn_answer4);

        // Receive quizCategory from StartActivity.
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);

        Log.v("CATEGORY_TAG", quizCategory + "");

        //Create quizArray from quizData.
        for (int i = 0; i < quizData.length; i++) {

            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);  //Question
            tmpArray.add(quizData[i][1]);  //Right Answer
            tmpArray.add(quizData[i][2]);  //Answer 1
            tmpArray.add(quizData[i][3]);  //Answer 2
            tmpArray.add(quizData[i][4]);  //Answer 3

            // Add tmpArray to quizArray.
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {

        // Update quizCountLabel.
        txtVw_countLabel.setText("Question " + quizCount);

        // Generate random number between 0 and 14 (quizArray's size -1).
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set question and right answer.
        // Array format: {"Question", "Right Answer", "Choice 1", "Choice 2", "Choice 3"}
        txtVw_questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        //Remove "Question Name" from quiz and Shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        // Set Choices.
        btn_answer1.setText(quiz.get(0));
        btn_answer2.setText(quiz.get(1));
        btn_answer3.setText(quiz.get(2));
        btn_answer4.setText(quiz.get(3));

        // Remove this quiz from quizArray.
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            // Correct!
            alertTitle = "Correct!";
            rightAnswerCount++;

        } else {
            // Wrong...
            alertTitle = "Wrong...";

        }

        // Create Dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    // Show Result.
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);
                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();

    }
}