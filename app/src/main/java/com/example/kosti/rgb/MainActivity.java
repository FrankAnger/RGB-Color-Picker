package com.example.kosti.rgb;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private SeekBar seek1;
    private SeekBar seek2;
    private SeekBar seek3;

    private TextView text;

    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        seek1 = findViewById(R.id.seek1);
        seek2 = findViewById(R.id.seek2);
        seek3 = findViewById(R.id.seek3);

        text = findViewById(R.id.text);

        seek1.setOnSeekBarChangeListener(new displayRGB());
        seek2.setOnSeekBarChangeListener(new displayRGB());
        seek3.setOnSeekBarChangeListener(new displayRGB());

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", text.getText());
                clipboard.setPrimaryClip(clip);

                Context context = getApplicationContext();
                CharSequence text = "Saved to Clipboard";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }

    private String addZero(String str) {
        if (str.length() == 1) {
            return "0" + str;
        }
        return str;
    }

    public class displayRGB implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            text.setBackgroundColor(Color.rgb(seek1.getProgress(), seek2.getProgress(), seek3.getProgress()));

            String red = Integer.toHexString(seek1.getProgress());
            red = addZero(red);

            String green = Integer.toHexString(seek2.getProgress());
            green = addZero(green);

            String blue = Integer.toHexString(seek3.getProgress());
            blue = addZero(blue);

            String color = "#" + red + green + blue;

            text.setTextColor(Color.rgb(
                    seek1.getMax() - seek1.getProgress(),
                    seek2.getMax() - seek2.getProgress(),
                    seek3.getMax() - seek3.getProgress()));
            text.setText(color);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
