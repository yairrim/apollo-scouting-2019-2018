package com.example.scoutingmisgav;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Teleop teleop = new Teleop();
    Autonomus autonomus = new Autonomus();
    EndGame endGame = new EndGame();
    Teams team = new Teams();
    boolean inRestart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyStoragePermissions(this);
        handler.post(runnable);

        Database.initDatabase();
        Button newS = (Button) findViewById(R.id.New);

        newS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Save();
            }
        });


        final TextView teamNumberView = (TextView) findViewById(R.id.team_number);
        teamNumberView.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                team.Set(teamNumberView.getText().toString());

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {}
        });


    }

    public void UpdateScore() {
        TextView AutonomousScoreText = findViewById(R.id.AutonomousScoreText);
        TextView TeleopTextScore = findViewById(R.id.TeleopScoreText);
        TextView EndGameTextScore = findViewById(R.id.EndGameScoreText);

        TextView LanderMineralText = findViewById(R.id.LanderMinerealText);
        TextView DepotMineralText = findViewById(R.id.DepotMinerealText);

        LanderMineralText.setText(teleop.getMineralInLanderAsString());
        DepotMineralText.setText(teleop.getMineralInDepotAsString());
        TeleopTextScore.setText(teleop.getPointsAsString());
        AutonomousScoreText.setText(autonomus.getPointsAsString());
        EndGameTextScore.setText(endGame.getPointsAsString());

    }

    private Handler handler = new Handler();

    // Define the code block to be executed
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            UpdateScore();
            TeleopPeriod();
            AutonomousPeriod();
            EndGamePeriod();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Make to run your application only in portrait mode


            handler.postDelayed(runnable, 0);
        }
    };

    public void TeleopPeriod() {

        Button AddMineralL = findViewById(R.id.AddMineral);
        Button DisMinearlL = findViewById(R.id.DisMineral);

        Button AddMineralD = findViewById(R.id.addMineralD);
        Button DisMinearlD = findViewById(R.id.disMineralD);

        AddMineralL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                teleop.AddMinearlToLander();
            }
        });

        DisMinearlL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teleop.DisMinearlToLander();
            }
        });

        AddMineralD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                teleop.AddMinearlToDepot();
            }
        });

        DisMinearlD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teleop.DisMinearlToDepot();
            }
        });
        if (inRestart) {
            teleop.MineralInLander = 0;
            teleop.MineralInDepot = 0;
        }
        teleop.SetScore(teleop.MineralInLander, teleop.MineralInDepot);

    }

    public void AutonomousPeriod() {
        CheckBox land = findViewById(R.id.Land);
        CheckBox sampling = findViewById(R.id.Sampling);
        CheckBox sampling2 = findViewById(R.id.Sampling2);
        CheckBox teamMarker = findViewById(R.id.TeamMarker);
        CheckBox parking = findViewById(R.id.Parking);

        if (inRestart) {
            land.setChecked(false);
            sampling.setChecked(false);
            sampling2.setChecked(false);
            teamMarker.setChecked(false);
            parking.setChecked(false);
        }
        autonomus.SetScore(land.isChecked(), sampling.isChecked(), sampling2.isChecked(), teamMarker.isChecked(), parking.isChecked());
    }

    public void EndGamePeriod() {
        RadioButton LatchRadio = findViewById(R.id.Latch);
        RadioButton FullParkRadio = findViewById(R.id.FullPark);
        RadioButton PartParkRadio = findViewById(R.id.PartPark);
        RadioButton NoneRadio = findViewById(R.id.None);

        if (inRestart) {
            LatchRadio.setChecked(false);
            FullParkRadio.setChecked(false);
            PartParkRadio.setChecked(false);
            NoneRadio.setChecked(false);
            inRestart = false;

        }

        if (LatchRadio.isChecked()) {
            endGame.SetScore(EndGame.State.Latch);
        }
        else if (PartParkRadio.isChecked()) {
            endGame.SetScore(EndGame.State.PartiallyParked);
        }
        else if (FullParkRadio.isChecked()) {
            endGame.SetScore(EndGame.State.FullyParked);
        }
        else{
            endGame.SetScore(EndGame.State.NONE);
        }

    }

    public File getApolloStorageDir() {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "apollo");
        if (!file.mkdirs()) {
            Log.e("", "Directory not created");
        }
        return file;
    }

    private String getApolloFileName() {
        return getApolloFile().getPath();
    }

    private File getApolloFile() {
        return new File(getApolloStorageDir(), "matches_.csv");
    }


    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void saveFile() {
        final TextView teamNumberView = (TextView) findViewById(R.id.team_number);
        File file = getApolloFile();
        boolean existed = file.exists();

        try {

            FileOutputStream out = new FileOutputStream(file, true);
            if (!existed) {
                out.write("Team,land,smaple,sample2,Marker,parking,Lander,Depot,EndState,Total".getBytes());
                out.write("\n".getBytes());
            }
            if ((!team.get().equals("Team"))) {
                out.write((team.get() + ",").getBytes());
                out.write(autonomus.getCsvState());
                out.write(teleop.getCsvState());
                out.write(endGame.getCsvState());
                out.write(getTotalPoint().getBytes());
                out.write("\n".getBytes());
                out.flush();
                out.close();
                teamNumberView.setText("Team");
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                inRestart = true;
            }
            else {
                Toast.makeText(getApplicationContext(), "Set Team Number", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("out", "ERROR");
        }

    }

    public String getTotalPoint() {
        return String.valueOf(autonomus.getPoints() + teleop.getPoints() + endGame.getPoints());
    }

    public void Save() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Confirm Save");
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Are you sure,You want to save?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                saveFile();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancal Save", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
