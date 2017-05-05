package shawfadetech.weston.shawfade19.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import shawfadetech.weston.shawfade19.R;

/**
 * Created by WestonKimaru on 2/21/2017.
 */
public class UserLogin extends Activity implements View.OnClickListener {
    public Button btnLogin, btnNewuser, btnForgotpass, btnhelp, btnexit;
    public EditText etAdmnumber, etPasskey;

    private Context context = UserLogin.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnNewuser = (Button) findViewById(R.id.btnmewUser);
        btnNewuser.setOnClickListener(this);
        btnForgotpass = (Button) findViewById(R.id.btnforgotpass);
        btnForgotpass.setOnClickListener(this);
        btnhelp = (Button)findViewById(R.id.btnhelp);
        btnhelp.setOnClickListener(this);
        btnexit = (Button)findViewById(R.id.btnexit);
        btnexit.setOnClickListener(this);

        etAdmnumber = (EditText)findViewById(R.id.etuserAdm);
        etPasskey = (EditText)findViewById(R.id.etusePasskey);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                Intent loginIntent = new Intent(getBaseContext(), ShawHome.class);
                startActivity(loginIntent);
                break;
            case R.id.btnmewUser:
                Intent newUser = new Intent(getBaseContext(), CreateUserAccount.class);
                startActivity(newUser);
                break;
            case R.id.btnforgotpass:
                final LayoutInflater layoutInflater = LayoutInflater.from(context);
                View promptView = layoutInflater.inflate(R.layout.accountresetdialog, null);
                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptView);
                alertDialogBuilder.setTitle("Account Reset Credentials");
                final EditText editnationalid = (EditText) promptView.findViewById(R.id.etusernationalid);
                editnationalid.setRawInputType(Configuration.KEYBOARD_12KEY);
                final EditText edittAdmnumber = (EditText) promptView.findViewById(R.id.etuserAdmission);
                edittAdmnumber.setRawInputType(Configuration.KEYBOARD_12KEY);
                final EditText email = (EditText) promptView.findViewById(R.id.etaccresetpasskey);
                email.setHint("E-mail Address");

                //editpasskey.setRawInputType(Configuration.KEYBOARD_12KEY);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                // create an alert dialog
                final AlertDialog customAlertDialog = alertDialogBuilder.create();
                customAlertDialog.setCancelable(false);
                customAlertDialog.setCanceledOnTouchOutside(false);
                customAlertDialog.show();
                customAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        String NationalID = editnationalid.getText().toString().trim();
                        String AdmissionNum = edittAdmnumber.getText().toString().trim();
                        String UserEmail = email.getText().toString().trim();
                        if(NationalID.isEmpty() || UserEmail.isEmpty() || AdmissionNum.isEmpty()){
                            Toast.makeText(context, "All fields required", Toast.LENGTH_LONG).show();
                        }else{
                            customAlertDialog.dismiss();
                            //Toast.makeText(context, "Validation error", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                break;
            case R.id.btnhelp:
                break;
            case R.id.btnexit:
                finish();
                System.exit(0);
                break;
            default:
                break;
        }

    }
}
