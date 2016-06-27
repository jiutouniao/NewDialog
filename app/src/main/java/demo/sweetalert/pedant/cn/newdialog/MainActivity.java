package demo.sweetalert.pedant.cn.newdialog;

import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnTest = (Button)findViewById(R.id.btn_test);

        btnTest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AnswerDialog dialog=  new AnswerDialog(MainActivity.this, new AnswerDialog.OnShareDialogListener() {
                    @Override
                    public void onDialogMessage(int position) {

                    }
                });
                dialog.show();
            }
        });
    }



}
