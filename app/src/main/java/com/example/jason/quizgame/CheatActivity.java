
package com.example.jason.quizgame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = ".com.jason.android.QuizGame.answer_is";
    private static final String EXTRA_ANSWER_SHOWN = ".com.jason.android.QuizGame.answer_shown";
    private static final String CHEAT_TAG = "CheatActivity";
    private static final String KEY_CHEATER = "cheated";
    private static final String KEY_ANSWERED = "answered status";
    private String mAnswer;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private boolean cheated = false;
    private boolean isAnswerShown = false;

    public void showAnswer(){
        mAnswerTextView.setText("Answer is => " + mAnswer);
    }
    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
    public static Intent newIntent(Context packageContext, String answerIs){
        Intent answerIntent = new Intent(packageContext, CheatActivity.class);
        answerIntent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIs);
        return answerIntent;
    }
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(CHEAT_TAG,"onStart() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(CHEAT_TAG,"onPause() called");
    }@Override
    public void onResume(){
        super.onResume();
        Log.d(CHEAT_TAG,"onResume() called");
    }@Override
    public void onStop(){
        super.onStop();
        Log.d(CHEAT_TAG,"onStop() called");
    }@Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(CHEAT_TAG,"onDestroy() called");
    }
    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(CHEAT_TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(KEY_CHEATER, cheated);
        savedInstanceState.putBoolean(KEY_ANSWERED, isAnswerShown);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        if(cheated) {
            showAnswer();
        }
        else mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showAnswer();
                cheated = true;
                isAnswerShown = true;
                setAnswerShownResult(isAnswerShown);
            }
        });
        mAnswer = getIntent().getStringExtra(EXTRA_ANSWER_IS_TRUE);
        if(savedInstanceState != null){
            cheated = savedInstanceState.getBoolean(KEY_CHEATER, false);
            isAnswerShown = savedInstanceState.getBoolean(KEY_ANSWERED, false);
            if(cheated) showAnswer();
            if(isAnswerShown) setAnswerShownResult(isAnswerShown);
        }
    }
}
