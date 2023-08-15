package com.morg.mywebhooks;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.morg.mywebhooks.database.AppDatabase;
import com.morg.mywebhooks.database.StudentModel;
import com.morg.mywebhooks.databinding.ActivityMainBinding;
import com.morg.mywebhooks.preference.SingletonNexApp;
import com.morg.webhook.configuration.WebhooksConfiguration;
import com.morg.webhook.model.Fields;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppDatabase appDatabase = AppDatabase.getInstance(this);
//        SingletonNexApp.getInstance().getSharedPreferences(this).setString("Student","Student 1");
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
        binding.fab.setOnClickListener(view -> new WebhooksConfiguration(MainActivity.this, "")
                .setTitle("Testing")
                .setDescription("Description Testing")
                .addField(new Fields("Coba", "Isinya ini"))
                .addField(new Fields("Coba 1", "Isinya ini 1"))
                .addField(new Fields("Coba 2", "Isinya ini 2"))
                .addField(new Fields("Coba 3", "Isinya ini 3"))
                .bottomSheet("Kirim Log", "Kirim ngga nih?")
                .build());
    }

}