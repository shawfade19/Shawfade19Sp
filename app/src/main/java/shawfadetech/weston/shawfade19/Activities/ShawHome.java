package shawfadetech.weston.shawfade19.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import shawfadetech.weston.shawfade19.Fragments.Notifications;
import shawfadetech.weston.shawfade19.Fragments.SettingsFragment;
import shawfadetech.weston.shawfade19.R;

public class ShawHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context context = ShawHome.this;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shaw_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "No Action has been assigned", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new FragmentMain();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shaw_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        SettingsFragment fragment =null;
        if (id == R.id.nav_Account) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            View promptView = layoutInflater.inflate(R.layout.accountlogindialog, null);
            android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
            alertDialogBuilder.setView(promptView);
            alertDialogBuilder.setTitle("User Credentials Input");
            final EditText editUsername = (EditText) promptView.findViewById(R.id.etusernamelogin);
            //editUsername.setRawInputType(Configuration.KEYBOARD_12KEY);
            final EditText editpasskey = (EditText) promptView.findViewById(R.id.etpasskeylogin);
            editpasskey.setRawInputType(Configuration.KEYBOARD_12KEY);
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
                    String Username = editUsername.getText().toString().trim();
                    String Userpasskey = editpasskey.getText().toString().trim();
                    if(Username.isEmpty() || Userpasskey.isEmpty()){
                        Toast.makeText(context, "All fields required", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() < 4){
                        Toast.makeText(context, "Pin less than four characters", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() > 5){
                        Toast.makeText(context, "Pin more than five characters", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() == 4){
                        if(Username.equalsIgnoreCase("Shawfade19") && Userpasskey.equalsIgnoreCase("Shawfade19")){
                            customAlertDialog.dismiss();
                            //user account home fragment here
                        }else{
                            Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(context, "Validation error", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } /*else if (id == R.id.nav_New) {

        }*/ else if (id == R.id.nav_reset) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            View promptView = layoutInflater.inflate(R.layout.accountresetdialog, null);
            android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
            alertDialogBuilder.setView(promptView);
            alertDialogBuilder.setTitle("Account Reset Credentials");
            final EditText editnationalid = (EditText) promptView.findViewById(R.id.etusernationalid);
            editnationalid.setRawInputType(Configuration.KEYBOARD_12KEY);
            final EditText edittAdmnumber = (EditText) promptView.findViewById(R.id.etuserAdmission);
            edittAdmnumber.setRawInputType(Configuration.KEYBOARD_12KEY);
            final EditText editpasskey = (EditText) promptView.findViewById(R.id.etaccresetpasskey);
            editpasskey.setRawInputType(Configuration.KEYBOARD_12KEY);
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
                    String Userpasskey = editpasskey.getText().toString().trim();
                    if(NationalID.isEmpty() || Userpasskey.isEmpty() || AdmissionNum.isEmpty()){
                        Toast.makeText(context, "All fields required", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() < 4){
                        Toast.makeText(context, "Pin less than four characters", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() > 5){
                        Toast.makeText(context, "Pin more than five characters", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() == 4){
                        if(NationalID.equalsIgnoreCase("123456789") && Userpasskey.equalsIgnoreCase("123456789") && AdmissionNum.equalsIgnoreCase("123456789")){
                            customAlertDialog.dismiss();
                            //user account home fragment here
                        }else{
                            Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(context, "Validation error", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }else if (id == R.id.nav_viewusers) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Please Wait......");
            dialog.setCancelable(false);
            dialog.setIndeterminate(true);
            dialog.show();
            dialog.setMax(20);
            dialog.dismiss();

        }else if (id == R.id.nav_accsettings) {

        } else if (id == R.id.nav_notification) {
           // fragment = new Notifications();

        } else if (id == R.id.nav_finance) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            View promptView = layoutInflater.inflate(R.layout.accountlogindialog, null);
            android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
            alertDialogBuilder.setView(promptView);
            alertDialogBuilder.setTitle("Finance Credentials Input");
            final EditText editUsername = (EditText) promptView.findViewById(R.id.etusernamelogin);
            editUsername.setRawInputType(Configuration.KEYBOARD_12KEY);
            editUsername.setHint("Admission Number");
            final EditText editpasskey = (EditText) promptView.findViewById(R.id.etpasskeylogin);
            editpasskey.setRawInputType(Configuration.KEYBOARD_12KEY);
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
                    String Username = editUsername.getText().toString().trim();
                    String Userpasskey = editpasskey.getText().toString().trim();
                    if(Username.isEmpty() || Userpasskey.isEmpty()){
                        Toast.makeText(context, "All fields required", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() < 4){
                        Toast.makeText(context, "Pin less than four characters", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() > 5){
                        Toast.makeText(context, "Pin more than five characters", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() == 4){
                        if(Username.equalsIgnoreCase("Shawfade19") && Userpasskey.equalsIgnoreCase("Shawfade19")){
                            customAlertDialog.dismiss();
                            //user account home fragment here
                        }else{
                            Toast.makeText(context, "Pin mismatch", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(context, "Validation error", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else if (id == R.id.nav_CheckFinances) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            View promptView = layoutInflater.inflate(R.layout.accountlogindialog, null);
            android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
            alertDialogBuilder.setView(promptView);
            alertDialogBuilder.setTitle("Status Check Credentials Input");
            final EditText editUsername = (EditText) promptView.findViewById(R.id.etusernamelogin);
            //editUsername.setRawInputType(Configuration.KEYBOARD_12KEY);
            editUsername.setHint("Admission Number");
            final EditText editpasskey = (EditText) promptView.findViewById(R.id.etpasskeylogin);
            editpasskey.setRawInputType(Configuration.KEYBOARD_12KEY);
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
                    String Username = editUsername.getText().toString().trim();
                    String Userpasskey = editpasskey.getText().toString().trim();
                    if(Username.isEmpty() || Userpasskey.isEmpty()){
                        Toast.makeText(context, "All fields required", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() < 4){
                        Toast.makeText(context, "Pin less than four characters", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() > 5){
                        Toast.makeText(context, "Pin more than five characters", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() == 4){
                        if(Username.equalsIgnoreCase("Shawfade19") && Userpasskey.equalsIgnoreCase("Shawfade19")){
                            customAlertDialog.dismiss();
                            //user account home fragment here
                        }else{
                            Toast.makeText(context, "Pin mismatch", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(context, "Validation error", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }else if (id == R.id.nav_AskExtension) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            View promptView = layoutInflater.inflate(R.layout.accountlogindialog, null);
            android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
            alertDialogBuilder.setView(promptView);
            alertDialogBuilder.setTitle("User Credentials Input");
            final EditText editUsername = (EditText) promptView.findViewById(R.id.etusernamelogin);
            editUsername.setRawInputType(Configuration.KEYBOARD_12KEY);
            editUsername.setHint("Admission Number");
            final EditText editpasskey = (EditText) promptView.findViewById(R.id.etpasskeylogin);
            editpasskey.setRawInputType(Configuration.KEYBOARD_12KEY);
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
                    String Username = editUsername.getText().toString().trim();
                    String Userpasskey = editpasskey.getText().toString().trim();
                    if(Username.isEmpty() || Userpasskey.isEmpty()){
                        Toast.makeText(context, "All fields required", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() < 4){
                        Toast.makeText(context, "Pin less than four characters", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() > 5){
                        Toast.makeText(context, "Pin more than five characters", Toast.LENGTH_LONG).show();
                    } else if(Userpasskey.length() == 4){
                        if(Username.equalsIgnoreCase("Shawfade19") && Userpasskey.equalsIgnoreCase("Shawfade19")){
                            customAlertDialog.dismiss();
                            //user account home fragment here
                        }else{
                            Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(context, "Validation error", Toast.LENGTH_LONG).show();
                    }
                }
            });


        }else if (id == R.id.nav_settings) {
            //Intent settingsIntent = new Intent(getBaseContext(), shawfadetech.weston.shawfade19.Activities.SettingsFragment.class);
            //startActivity(settingsIntent);
            //startActivity(new Intent(this, SettingsFragment.class));
           fragment = new SettingsFragment();

        }else if (id == R.id.nav_contactus) {

        }else if (id == R.id.nav_privacy) {


        }else if (id == R.id.nav_rateus) {

        }else if (id == R.id.nav_more) {

        }else if (id == R.id.nav_share) {

        }else if (id == R.id.nav_exit) {
            finish();
            System.exit(0);

        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //ft.replace(R.id.mainFrame, fragment);
        ft.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
