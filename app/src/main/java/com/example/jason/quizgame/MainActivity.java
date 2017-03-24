package com.example.jason.quizgame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_SCORE = "score";
    private static final String KEY_CHEATERS = "cheat";
    private static final String KEY_ANSWERED = "answered";
    private static final int REQUEST_CHEAT_CODE = 0;
    private Button moptA;
    private Button moptB;
    private Button moptC;
    private Button moptD;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mHintButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private TextView mOptionA;
    private TextView mOptionB;
    private TextView mOptionC;
    private TextView mOptionD;
    private TextView hintToastBlank;
    private TextView mScoreTextView;
    private ArrayList<Question> mQuestionBank = new ArrayList<>();
    private ArrayList<Integer> mCheaters = new ArrayList<>();
    private ArrayList<Integer> mAnswered = new ArrayList<>();
    private int mCurrentIndex = 0;
    private Integer mScore = 0;

    private void updateQuestion(){
        Log.d(TAG, "Updating question text for question #" + mCurrentIndex, new Exception());
        Log.d(TAG, "Updating score " + mScore, new Exception());
        int question = mQuestionBank.get(mCurrentIndex).getTextResId();
        int optA = mQuestionBank.get(mCurrentIndex).getmOptA();
        int optB = mQuestionBank.get(mCurrentIndex).getmOptB();
        int optC = mQuestionBank.get(mCurrentIndex).getmOptC();
        int optD = mQuestionBank.get(mCurrentIndex).getmOptD();
        mQuestionTextView.setText(question);
        mOptionA.setText(optA);
        mOptionB.setText(optB);
        mOptionC.setText(optC);
        mOptionD.setText(optD);
        hintToastBlank.setText("");
        mScoreTextView.setText(String.valueOf("Score is: " + mScore));
    }
    private void checkAnswer(String userInput){
        String correctAnswer = mQuestionBank.get(mCurrentIndex).getAnswer();
        int messageResId = 0;
        if(userInput.equals(correctAnswer)){
            if(mCheaters.get(mCurrentIndex) != 1){
                mAnswered.set(mCurrentIndex,0);
                messageResId = R.string.judgement_toast;
                Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
                return;
            }
            else messageResId = R.string.correct_toast;
            if(mAnswered.get(mCurrentIndex) == 1) {
                mScore++;
                mScoreTextView.setText(String.valueOf("Score is: " + mScore));
                mAnswered.set(mCurrentIndex,0);
            }
        }
        else {
            messageResId = R.string.incorrect_toast;
            mScore--;
            if(mScore<0) mScore = 0;
            mScoreTextView.setText(String.valueOf("Score is: " + mScore));
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CHEAT_CODE){
            if(data == null) return;
            if(CheatActivity.wasAnswerShown(data)){
                mCheaters.set(mCurrentIndex,0);
            }
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }@Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }@Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }@Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putInt(KEY_SCORE, mScore);
        savedInstanceState.putIntegerArrayList(KEY_CHEATERS,mCheaters);
        savedInstanceState.putIntegerArrayList(KEY_ANSWERED,mAnswered);
    }
    //TODO static fucntion to populate Cheaters and Answered Arrays to 1
    public static void populate(Integer size, ArrayList<Integer> a, ArrayList<Integer> b){
        Log.d(TAG,"populate called");
        for(int i = 0; i < size; i ++){
            a.add(1);
            b.add(1);
    }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreat(Bunblde) called");
        setContentView(R.layout.activity_main);

        mQuestionBank.add(new Question(R.string.question_1,"D",R.string.Question1A,R.string.Question1B,R.string.Question1C,R.string.Question1D,R.string.Hint1_toast));
        mQuestionBank.add(new Question(R.string.question_2,"B",R.string.Question2A,R.string.Question2B,R.string.Question2C,R.string.Question2D,R.string.Hint2_toast));
        mQuestionBank.add(new Question(R.string.question_3,"A",R.string.Question3A,R.string.Question3B,R.string.Question3C,R.string.Question3D,R.string.Hint3_toast));
        mQuestionBank.add(new Question(R.string.question_4,"C",R.string.Question4A,R.string.Question4B,R.string.Question4C,R.string.Question4D,R.string.Hint4_toast));
        mQuestionBank.add(new Question(R.string.question_5,"A",R.string.Question5A,R.string.Question5B,R.string.Question5C,R.string.Question5D,R.string.Hint5_toast));
        Log.d(TAG,"bank set up");
        populate(mQuestionBank.size(),mCheaters,mAnswered);
        Log.d(TAG,"populate worked");
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mOptionA = (TextView) findViewById(R.id.option_A_view);
        mOptionB = (TextView) findViewById(R.id.option_B_view);
        mOptionC = (TextView) findViewById(R.id.option_C_view);
        mOptionD = (TextView) findViewById(R.id.option_D_view);
        hintToastBlank = (TextView) findViewById(R.id.hint_view);
        hintToastBlank.setText(" ");
        mScoreTextView = (TextView) findViewById(R.id.score_view);
        mScoreTextView.setText(String.valueOf("Score: " + mScore));
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String answerIs = mQuestionBank.get(mCurrentIndex).getAnswer();
                Intent godMode = CheatActivity.newIntent(MainActivity.this,answerIs);
                startActivityForResult(godMode,REQUEST_CHEAT_CODE);
            }
        });
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
                hintToastBlank.setText(mQuestionBank.get(mCurrentIndex).getHint());
            }
        });
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mScore = savedInstanceState.getInt(KEY_SCORE, 0);
            mCheaters = savedInstanceState.getIntegerArrayList(KEY_CHEATERS);
            mAnswered = savedInstanceState.getIntegerArrayList(KEY_ANSWERED);
        }
        updateQuestion();

    }
}
