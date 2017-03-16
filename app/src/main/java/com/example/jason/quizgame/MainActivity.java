package com.example.jason.quizgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button moptA;
    private Button moptB;
    private Button moptC;
    private Button moptD;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mHintButton;
    private TextView mQuestionTextView;
    private TextView mOptionA;
    private TextView mOptionB;
    private TextView mOptionC;
    private TextView mOptionD;
    private TextView hintToast;
    private TextView hintToastBlank;

    private ArrayList<Question> mQuestionBank = new ArrayList<>();

    private int mCurrentIndex = 0;
    private void updateQuestion(){
        int question = mQuestionBank.get(mCurrentIndex).getTextResId();
        int optA = mQuestionBank.get(mCurrentIndex).getmOptA();
        int optB = mQuestionBank.get(mCurrentIndex).getmOptB();
        int optC = mQuestionBank.get(mCurrentIndex).getmOptC();
        int optD = mQuestionBank.get(mCurrentIndex).getmOptD();
        int hint = mQuestionBank.get(mCurrentIndex).getHint();
        mQuestionTextView.setText(question);
        mOptionA.setText(optA);
        mOptionB.setText(optB);
        mOptionC.setText(optC);
        mOptionD.setText(optD);
        hintToast.setText(hint);

    }
    private void checkAnswer(String userInput){
        String correctAnswer = mQuestionBank.get(mCurrentIndex).getAnswer();
        int messageResId = 0;
        if(userInput.equals(correctAnswer)) messageResId =R.string.correct_toast;
        else messageResId = R.string.incorrect_toast;
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionBank.add(new Question(R.string.question_1,"D",R.string.Question1A,R.string.Question1B,R.string.Question1C,R.string.Question1D,R.string.Hint1_toast));
        mQuestionBank.add(new Question(R.string.question_2,"B",R.string.Question2A,R.string.Question2B,R.string.Question2C,R.string.Question2D,R.string.Hint2_toast));
        mQuestionBank.add(new Question(R.string.question_3,"A",R.string.Question3A,R.string.Question3B,R.string.Question3C,R.string.Question3D,R.string.Hint3_toast));
        mQuestionBank.add(new Question(R.string.question_4,"C",R.string.Question4A,R.string.Question4B,R.string.Question4C,R.string.Question4D,R.string.Hint4_toast));
        mQuestionBank.add(new Question(R.string.question_5,"A",R.string.Question5A,R.string.Question5B,R.string.Question5C,R.string.Question5D,R.string.Hint5_toast));

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mOptionA = (TextView) findViewById(R.id.option_A_view);
        mOptionB = (TextView) findViewById(R.id.option_B_view);
        mOptionC = (TextView) findViewById(R.id.option_C_view);
        mOptionD = (TextView) findViewById(R.id.option_D_view);
        hintToast = (TextView) findViewById(R.id.hint_view);

        moptA = (Button) findViewById(R.id.optA);
        moptA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer("A");
            }
        });
        moptB = (Button) findViewById(R.id.optB);
        moptB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer("B");
            }
        });
        moptC = (Button) findViewById(R.id.optC);
        moptC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer("C");
            }
        });
        moptD = (Button) findViewById(R.id.optD);
        moptD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer("D");
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int new_index = mCurrentIndex+1;
                if(mCurrentIndex==mQuestionBank.size()-1) new_index = mQuestionBank.size()-1;
                mCurrentIndex = (new_index)%mQuestionBank.size();
                updateQuestion();
            }
        });
        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int new_index = 0;
                if(mCurrentIndex!=0) new_index = mCurrentIndex-1;
                mCurrentIndex = (new_index)%mQuestionBank.size();
                updateQuestion();
            }
        });
        mHintButton = (Button) findViewById(R.id.hint_button);
        mHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast hint = new Toast(getApplicationContext());
                //hint.setView(hintToast);
                //hint.setDuration(Toast.LENGTH_LONG);
                //hint.show();


            }
        });
        updateQuestion();

    }
}
