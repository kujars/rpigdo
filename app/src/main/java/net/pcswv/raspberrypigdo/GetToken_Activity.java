package net.pcswv.raspberrypigdo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GetToken_Activity extends AppCompatActivity implements View.OnClickListener {

    //defining views
    private Button buttonDisplayToken;
    private EditText textViewToken;
    private Button buttonCopyToken;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_token);

        //getting views from xml
        textViewToken = (EditText) findViewById(R.id.textViewToken);
        buttonDisplayToken = (Button) findViewById(R.id.buttonDisplayToken);
        buttonCopyToken = (Button) findViewById(R.id.cptok);

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        //adding listener to view
        buttonDisplayToken.setOnClickListener(this);
        buttonCopyToken.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonDisplayToken) {
            //getting token from shared preferences
            String token = SharedPrefManager.getInstance(this).getDeviceToken();

            //if token is not null
            if (token != null) {
                //displaying the token
                textViewToken.setText(token);
            } else {
                //if token is null that means something wrong
                textViewToken.setText(R.string.tokerr);
            }
        }
        String check = "NS";
        if (view == buttonCopyToken) {
            String text = textViewToken.getText().toString();
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Token Copied To Clipboard!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }