package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01var04MainActivity extends AppCompatActivity {

    Button topLeft = null;
    Button topRight = null;
    Button center = null;
    Button bottomLeft = null;
    Button bottomRight = null;
    Button navigateToSecond = null;
    TextView textView  = null;
    static int numberOfClicks = 0;
    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    int serviceStatus = 0;
    private IntentFilter intentFilter = new IntentFilter();

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("text"));
        }
    }

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            numberOfClicks++;
            if (numberOfClicks > 3 && serviceStatus == 0) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
                intent.putExtra("text", textView.getText().toString());
                getApplicationContext().startService(intent);
                serviceStatus = 1;
            }

            switch(view.getId()) {
                case R.id.top_left:

//                    numberOfClicks++;
                    String currentString = textView.getText().toString();
                    currentString = currentString + ", " + topLeft.getText().toString();
                    textView.setText(currentString);

                    break;
                case R.id.top_right:
//                    numberOfClicks++;
                    currentString = textView.getText().toString();
                    currentString = currentString + ", " + topRight.getText().toString();
                    textView.setText(currentString);

                    break;

                case R.id.center:
//                    numberOfClicks++;
                    currentString = textView.getText().toString();
                    currentString = currentString + ", " + center.getText().toString();
                    textView.setText(currentString);

                    break;

                case R.id.bottom_left:
//                    numberOfClicks++;
                    currentString = textView.getText().toString();
                    currentString = currentString + ", " + bottomLeft.getText().toString();
                    textView.setText(currentString);

                    break;

                case R.id.bottom_right:
//                    numberOfClicks++;
                    currentString = textView.getText().toString();
                    currentString = currentString + ", " + bottomRight.getText().toString();
                    textView.setText(currentString);

                    break;

                case R.id.navigate_to_secondary:

                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);

                    intent.putExtra("textView", textView.getText().toString());
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;


            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01var04_main);

        topLeft = (Button) findViewById(R.id.top_left);
        topRight = (Button) findViewById(R.id.top_right);
        center = (Button) findViewById(R.id.center);
        bottomLeft = (Button) findViewById(R.id.bottom_left);
        bottomRight = (Button) findViewById(R.id.bottom_right);
        textView = (TextView) findViewById(R.id.text_view);
        textView.setText("");
        topLeft.setOnClickListener(buttonClickListener);
        topRight.setOnClickListener(buttonClickListener);
        center.setOnClickListener(buttonClickListener);
        bottomLeft.setOnClickListener(buttonClickListener);
        bottomRight.setOnClickListener(buttonClickListener);

        navigateToSecond = (Button) findViewById(R.id.navigate_to_secondary);
        navigateToSecond.setOnClickListener(buttonClickListener);




//        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction("action");
//        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("textView", textView.getText().toString());
        savedInstanceState.putString("count", Integer.toString(numberOfClicks));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState.containsKey("textView")) {
            textView.setText(savedInstanceState.getString("textView"));
        } else {
            textView.setText("");
        }
        if (savedInstanceState.containsKey("count")) {
            Toast.makeText(getApplicationContext(), "number of clicks" + numberOfClicks, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            textView.setText("");
            numberOfClicks = 0;
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var04Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
