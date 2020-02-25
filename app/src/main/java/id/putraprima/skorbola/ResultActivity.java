package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView result, winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.result);
        winner = findViewById(R.id.winner);

        Bundle bundle = getIntent().getExtras();
        int homeResult = bundle.getInt("homeScore");
        int awayResult = bundle.getInt("awayScore");
        String homeName = bundle.getString("homeName");
        String awayName = bundle.getString("awayName");

        if (bundle != null){
            result.setText("Score: "+ String.valueOf(homeResult) + "-" + String.valueOf(awayResult));
            if (homeResult > awayResult){
                winner.setText("Team "+ homeName + " Memenangkan pertandingan");
            } else if (awayResult > homeResult){
                winner.setText("Team "+ awayName + " Memenangkan pertandingan");
            } else{
                winner.setText("Team imbang");
            }
        }
    }
}
