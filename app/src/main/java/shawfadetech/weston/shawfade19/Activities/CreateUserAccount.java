package shawfadetech.weston.shawfade19.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import shawfadetech.weston.shawfade19.R;

/**
 * Created by WestonKimaru on 2/23/2017.
 */
public class CreateUserAccount extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreate, btnCancel;
    private EditText txtFname, txtOname, txtGender, txtIDnumber, txtEmail, txtPhone, txtIntake,
            txtCourse, txtRegNum, txtUsername, txtPass1, txtPass2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.registerstudent);

        txtFname = (EditText)findViewById(R.id.txtFname);
        txtFname.setOnClickListener(this);
        txtOname = (EditText)findViewById(R.id.txtOname);
        txtOname.setOnClickListener(this);
        txtGender = (EditText)findViewById(R.id.txtGender);
        txtGender.setOnClickListener(this);
        txtIDnumber = (EditText)findViewById(R.id.txtIDnum);
        txtIDnumber.setOnClickListener(this);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtEmail.setOnClickListener(this);
        txtPhone = (EditText)findViewById(R.id.txtPhone);
        txtPhone.setOnClickListener(this);
        txtIntake = (EditText)findViewById(R.id.txtIntake);
        txtIntake.setOnClickListener(this);
        txtCourse = (EditText)findViewById(R.id.txtCourse);
        txtCourse.setOnClickListener(this);
        txtRegNum = (EditText)findViewById(R.id.txtRegnum);
        txtRegNum.setOnClickListener(this);
        txtUsername = (EditText)findViewById(R.id.txtUname);
        txtUsername.setOnClickListener(this);
        txtPass1 = (EditText)findViewById(R.id.txtPass1);
        txtPass1.setOnClickListener(this);
        txtPass2 = (EditText)findViewById(R.id.txtPass2);
        txtPass2.setOnClickListener(this);
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
