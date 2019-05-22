package com.tayaa.quizme.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tayaa.quizme.R;
import com.tayaa.quizme.models.Question;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // TODO: Declare member variables here:
    private Button trueButton;
    private Button falseButton;
    private ProgressBar progressBar;
    private TextView scoreTextView;
    private TextView questionTextView;

    // TODO: Uncomment to create question bank
    private Question[] questions = new Question[] {
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, true),
            new Question(R.string.question_3, true),
            new Question(R.string.question_4, true),
            new Question(R.string.question_5, true),
            new Question(R.string.question_6, false),
            new Question(R.string.question_7, true),
            new Question(R.string.question_8, false),
            new Question(R.string.question_9, true),
            new Question(R.string.question_10, true),
            new Question(R.string.question_11, false),
            new Question(R.string.question_12, false),
            new Question(R.string.question_13,true)
    };
    private int indexQuestion=0;
    private int idQuestion;
    private int score;

    // TODO: Declare onstants here
    final int PROGRESS_BAR_INCREMENT=(int)Math.ceil(100.0/questions.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            score=savedInstanceState.getInt("scoreKey");
            indexQuestion=savedInstanceState.getInt("indexKey");
        }

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        progressBar=findViewById(R.id.progress_bar);
        scoreTextView=findViewById(R.id.score_text_view);
        questionTextView=findViewById(R.id.question_text_view);

        idQuestion=questions[indexQuestion].getId();

        questionTextView.setText(idQuestion);
        scoreTextView.setText("Score "+score+"/"+questions.length);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.true_button){
            checkAnswer(true);
            updateQuestion();
        }
        else if(v.getId()==R.id.false_button){
            checkAnswer(false);
            updateQuestion();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scoreKey", score);
        outState.putInt("indexKey", indexQuestion);
    }

    private void updateQuestion(){
        indexQuestion=indexQuestion+1;
        if(indexQuestion>=questions.length){
            indexQuestion=0;
        }
        if(indexQuestion==0){
            final AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Game over");
            alert.setCancelable(false);
            alert.setMessage("You scored "+score+" points!");
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();
            score=0;
        }

        idQuestion=questions[indexQuestion].getId();
        questionTextView.setText(idQuestion);
        progressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        scoreTextView.setText("Score "+score+"/"+questions.length);
    }

    private void checkAnswer(boolean userSelection){
        if(userSelection==questions[indexQuestion].getAnswer()){
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            score=score+1;
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
