package panchenko.ivan.canteen_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView totalCheckSummView;
    Button calculator1, calculator2,
            calculator3, calculator4,
            calculator5, calculator6,
            calculator7, calculator8,
            calculator9, calculator0,
        cancelCheckButton, acceptCheckButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalCheckSummView = (TextView) findViewById(R.id.total_summ_text_view);
        calculator0 = (Button) findViewById(R.id.calculator_number_0);
        calculator1 = (Button) findViewById(R.id.calculator_number_1);
        calculator2 = (Button) findViewById(R.id.calculator_number_2);
        calculator3 = (Button) findViewById(R.id.calculator_number_3);
        calculator4 = (Button) findViewById(R.id.calculator_number_4);
        calculator5 = (Button) findViewById(R.id.calculator_number_5);
        calculator6 = (Button) findViewById(R.id.calculator_number_6);
        calculator7 = (Button) findViewById(R.id.calculator_number_7);
        calculator8 = (Button) findViewById(R.id.calculator_number_8);
        calculator9 = (Button) findViewById(R.id.calculator_number_9);
        cancelCheckButton = (Button) findViewById(R.id.cancel_check_button);
        acceptCheckButton = (Button) findViewById(R.id.accept_check_button);

    }
}
