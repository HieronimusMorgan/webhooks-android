package com.morg.mywebhooks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.morg.mywebhooks.database.AppDatabase;
import com.morg.mywebhooks.database.StudentModel;
import com.morg.mywebhooks.databinding.ActivityMainBinding;
import com.morg.webhook.configuration.SendLog;
import com.morg.webhook.configuration.WebhooksConfiguration;
import com.morg.webhook.model.Fields;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        binding.buttonFirst.setOnClickListener(view1 -> {
            for (int i = 0; i < 10; i++) {
                StudentModel studentModel = new StudentModel();
                studentModel.setName("Student " + i);
                studentModel.setClassRoom(String.valueOf(i));
                appDatabase.studentDAO().insertStudent(studentModel);
            }
//            NavHostFragment.findNavController(FirstFragment.this)
//                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
        SendLog sendLog = new SendLog(this, "https://discord.com/api/webhooks/1272780437454061588/jWjScs5ASOrputBs11v0uWUnlZCZZEkK5x1GK5MMhW7yYZxJFHv3t8gBCBZXpnbK9VYn");
        binding.fab.setOnClickListener(view -> {
                    sendLog.sendLogError(new Throwable());
                    sendLog.sendLogDebug(MainActivity.class.getSimpleName(), "Debug Log");
                    sendLog.sendLogInfo("Info Testing");
                    sendLog.sendLogWarning("Warning Testing");
                    sendLog.sendLogVerbose("Verbose Testing");
                    sendLog.sendLogAssert("Assert Testing");

                    new WebhooksConfiguration(MainActivity.this, "https://discord.com/api/webhooks/1272780437454061588/jWjScs5ASOrputBs11v0uWUnlZCZZEkK5x1GK5MMhW7yYZxJFHv3t8gBCBZXpnbK9VYn")
                            .setTitle("Testing")
                            .setDescription("Description Testing")
                            .addField(new Fields("Coba", "Isinya ini"))
                            .addField(new Fields("Coba 1", "Isinya ini 1"))
                            .addField(new Fields("Coba 2", "Isinya ini 2"))
                            .addField(new Fields("Coba 3", "Isinya ini 3"))
                            .bottomSheet("Kirim Log", "Kirim ngga nih?")
                            .build();
                }
        );
    }

}