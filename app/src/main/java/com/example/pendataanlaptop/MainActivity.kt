package com.example.pendataanlaptop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendataanlaptop.adapter.UserAdapter;
import com.example.pendataanlaptop.database.AppDatabase;
import com.example.pendataanlaptop.database.entitas.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnTambah;
    private AppDatabase database;
    private UserAdapter userAdapter;
    private List<User> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        btnTambah = findViewById(R.id.btn_tambah);

        database = AppDatabase.getInstance(getApplicationContext());

        userAdapter = new UserAdapter(getApplicationContext(), list);
        userAdapter.setDialog(new UserAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                showOptionsDialog(position);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    private void showOptionsDialog(final int position) {
        final CharSequence[] dialogItem = {"Edit", "Hapus"};
        dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                    Intent intent = new Intent(MainActivity.this, TambahActivity.class);
                    intent.putExtra("uid", list.get(position).getId());
                    startActivity(intent);
                    break;
                    case 1:
                    User user = list.get(position);
                    deleteAndRefresh(user);
                    break;
                }
            }
        });
        dialog.show();
    }

    private void deleteAndRefresh(final User user) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                database.userDao().delete(user);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        list.addAll(database.userDao().getAll());
                        userAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshUserList();
    }

    private void refreshUserList() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<User> newList = database.userDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        list.addAll(newList);
                        userAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
