package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {


    Button verifyButton = null;
    Button cancelButton = null;
    TextView seconTextView = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.verify:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);

        seconTextView = (TextView) findViewById(R.id.textViewSecondaryActivity);
        verifyButton = (Button) findViewById(R.id.verify);
        cancelButton = (Button) findViewById(R.id.cancel);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("textView")) {
            String numberOfClicks = intent.getStringExtra("textView");
            seconTextView.setText(String.valueOf(numberOfClicks));
        }

        verifyButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);

    }
}
