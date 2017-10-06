package com.popland.pop.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
EditText EDTtaikhoan, EDTmatkhau;
    CheckBox CheckBoxremember;
    ToggleButton Togglebutton;
    Button btnDangnhap;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EDTtaikhoan = (EditText) findViewById(R.id.EDTtaikhoan);
        EDTmatkhau = (EditText) findViewById(R.id.EDTmatkhau);
        CheckBoxremember = (CheckBox) findViewById(R.id.checkboxRemember);
        Togglebutton = (ToggleButton) findViewById(R.id.toggleButton);
        btnDangnhap = (Button) findViewById(R.id.BTNdangnhap);

        sharedPreferences = getSharedPreferences("StatusSaving",MODE_PRIVATE);
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = EDTtaikhoan.getText().toString();
                String password = EDTmatkhau.getText().toString();
                if(user.equals("tanhai")&& password.equals("13579513")){
                    Toast.makeText(MainActivity.this,"you log in successfully",Toast.LENGTH_LONG).show();
                    if(CheckBoxremember.isChecked()){
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name",user);
                        editor.putString("pass",password);
                        editor.putBoolean("TBstate",Togglebutton.isChecked());
                        editor.commit();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"log in failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EDTtaikhoan.setText(sharedPreferences.getString("name","example"));//get default values if nothing saved in xe SH
        EDTmatkhau.setText(sharedPreferences.getString("pass","123456789"));
        Togglebutton.setChecked(sharedPreferences.getBoolean("TBstate",false));
    }
}
