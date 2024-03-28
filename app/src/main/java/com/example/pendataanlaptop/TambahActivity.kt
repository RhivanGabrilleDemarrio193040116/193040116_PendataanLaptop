package com.example.pendataanlaptop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pendataanlaptop.database.AppDatabase;
import com.example.pendataanlaptop.database.entitas.User;

public class TambahActivity extends AppCompatActivity {
    private EditText editId, editMerekLaptop, editTipeLaptop, editProcessor, editHarga;
    private Button btnSave;
    private AppDatabase database;
    private int uid = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        editId = findViewById(R.id.id);
        editMerekLaptop = findViewById(R.id.merek_laptop);
        editTipeLaptop = findViewById(R.id.tipe_laptop);
        editProcessor = findViewById(R.id.processor);
        editHarga = findViewById(R.id.harga);
        btnSave = findViewById(R.id.btn_save);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", 0);
        if (uid > 0) {
            isEdit = true;
            User user = database.userDao().get(uid);
            editId.setText(String.valueOf(user.getId()));
            editMerekLaptop.setText(user.getMerekLaptop());
            editTipeLaptop.setText(user.getTipeLaptop());
            editProcessor.setText(user.getProcessor());
            editHarga.setText(String.valueOf(user.getHarga()));
        } else {
            isEdit = false;
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit) {
                    User user = new User(
                        uid,
                        editMerekLaptop.getText().toString(),
                        editTipeLaptop.getText().toString(),
                        editProcessor.getText().toString(),
                        Integer.parseInt(editHarga.getText().toString())
                    );
                    database.userDao().update(user);
                } else {
                    User user = new User(
                        editMerekLaptop.getText().toString(),
                        editTipeLaptop.getText().toString(),
                        editProcessor.getText().toString(),
                        Integer.parseInt(editHarga.getText().toString())
                    );
                    database.userDao().insert(user);
                }
                finish();
            }
        });
    }
}
