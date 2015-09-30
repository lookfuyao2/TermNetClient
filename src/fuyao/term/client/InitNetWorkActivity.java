package fuyao.term.client;

import jackpal.androidterm.R;
import jackpal.androidterm.Term;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitNetWorkActivity extends Activity implements OnClickListener {
    private EditText mSetName = null;
    private EditText mSetPassword = null;
    private EditText mConfirmPassword = null;
    private Button mOkButton = null;

    private EditText mSetHost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        mSetName = (EditText) findViewById(R.id.setName);
        mSetPassword = (EditText) findViewById(R.id.setPassword);
        mConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        mSetHost = (EditText) findViewById(R.id.host);
        mOkButton = (Button) findViewById(R.id.ok);
        mOkButton.setOnClickListener(this);
        restore();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.ok:
            String name = mSetName.getText().toString();
            String password = mSetPassword.getText().toString();
            String cPassword = mConfirmPassword.getText().toString();
            String host = mSetHost.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "Name can't be null", Toast.LENGTH_SHORT)
                        .show();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Password can't be null",
                        Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(cPassword)) {
                Toast.makeText(this, "Please Confirm Password",
                        Toast.LENGTH_SHORT).show();
            } else if (!cPassword.equals(password)) {
                Toast.makeText(this, "Passwords are not same",
                        Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(host)) {
                Toast.makeText(this, "host should not be empty",
                        Toast.LENGTH_SHORT).show();
            } else {
                save(name, password, host);
                // TODO: UUID.randomUUID().toString();
                // String uid = String.valueOf(System.currentTimeMillis());
                Intent intent = new Intent(getApplicationContext(), Term.class);
                intent.putExtra("name", name);
                intent.putExtra("host", host);
                startActivity(intent);
                finish();
            }
            break;

        default:
            break;
        }
    }

    private void restore() {
        SharedPreferences sp = getSharedPreferences("back",
                Context.MODE_PRIVATE);
        String temp = sp.getString("name", "");
        if (TextUtils.isEmpty(temp)) {
            return;
        }
        mSetName.setText(temp);
        temp = sp.getString("pword", "");
        if (TextUtils.isEmpty(temp)) {
            return;
        }
        mSetPassword.setText(temp);
        mConfirmPassword.setText(temp);
        temp = sp.getString("host", "");
        if (TextUtils.isEmpty(temp)) {
            return;
        }
        mSetHost.setText(temp);
    }

    private void save(String name, String pword, String host) {
        SharedPreferences sp = getSharedPreferences("back",
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString("name", name);
        editor.putString("pword", pword);
        editor.putString("host", host);
        editor.apply();
    }
}
