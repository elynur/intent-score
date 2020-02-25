package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {
    private TextView homeName, awayName, homeScore, awayScore;
    private ImageView homeLogo, awayLogo;
    Uri uri1, uri2;
    Bitmap bitmap1, bitmap2;
    String homeTeam, awayTeam;
    int scoreHome = 0;
    int scoreAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeName = findViewById(R.id.txt_home);
        awayName = findViewById(R.id.txt_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        awayScore = findViewById(R.id.score_away);
        homeScore = findViewById(R.id.score_home);

        Bundle bundle = getIntent().getExtras();

        homeTeam = bundle.getString("inputHome");
        awayTeam = bundle.getString("inputAway");

        if (bundle != null){
            uri1 = Uri.parse(bundle.getString("logoHome"));
            uri2 = Uri.parse(bundle.getString("logoAway"));
            bitmap1 = null;
            bitmap2 = null;

            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            homeName.setText(homeTeam);
            awayName.setText(awayTeam);
            homeLogo.setImageBitmap(bitmap1);
            awayLogo.setImageBitmap(bitmap2);
    }

    //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
    public void handleAddHomeScore(View view) {
        scoreHome += 1;
        homeScore.setText(String.valueOf(scoreHome));
    }

    public void handleAddAwayScore(View view) {
        scoreAway += 1;
        awayScore.setText(String.valueOf(scoreAway));
    }

    public void handleReduceHome(View view) {
        if (scoreHome != 0){
        scoreHome -= 1;
        homeScore.setText(String.valueOf(scoreHome));
        }

    }

    public void handleReduceAway(View view) {
        if (scoreAway != 0){
            scoreAway -= 1;
            awayScore.setText(String.valueOf(scoreAway));
        }
    }


    //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    public void handleCekHasil(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("homeScore", scoreHome);
        intent.putExtra("awayScore", scoreAway);
        intent.putExtra("homeName", homeTeam);
        intent.putExtra("awayName", awayTeam);
        startActivity(intent);
    }



}
