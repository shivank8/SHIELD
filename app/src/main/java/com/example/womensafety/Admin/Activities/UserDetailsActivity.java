package com.example.womensafety.Admin.Activities;

import android.view.Menu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.womensafety.Activities.LoginActivity;
import com.example.womensafety.Models.suspect_registered;
import com.example.womensafety.R;
import com.example.womensafety.SuperAdmin.Adapters.suspectSuperAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDetailsActivity extends AppCompatActivity {

    ListView userSusList;
    TextView susUser;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;


    FirebaseDatabase database;
    DatabaseReference reference;

    public String name;
    public String mob_num;

    String flag="0";
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        database=FirebaseDatabase.getInstance();

        auth=FirebaseAuth.getInstance();

        name=getIntent().getStringExtra("username");
        mob_num=getIntent().getStringExtra("mob");
        flag=getIntent().getStringExtra("flag");

        reference=database.getReference("suspects_registered").child(mob_num);

        setUpToolbar();
        navigationView = findViewById(R.id.navigationMenu);

        if(flag.equals("1")) {
            Menu menu = navigationView.getMenu();

            // find MenuItem you want to change
            menu.removeItem(R.id.superadmin_home);
            menu.removeItem(R.id.superadmin_manage_superadmin);
            menu.removeItem(R.id.superadmin_manage_account);
            menu.removeItem(R.id.superadmin_manage_admin);

            MenuItem user = menu.findItem(R.id.superadmin_manage_users);

            // set new title to the MenuItem
            user.setTitle("Manage User");

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {

                        case R.id.superadmin_homepage:
                            startActivity(new Intent(UserDetailsActivity.this, AdminHomepage.class));
                            break;

                        case R.id.superadmin_home:
                            startActivity(new Intent(UserDetailsActivity.this, SuperAdminDashboardActivity.class));
                            break;
                        case R.id.superadmin_manage_account:
                            startActivity( new Intent(UserDetailsActivity.this, ManageSuperAdminAccountActivity.class));
                            break;

                        case R.id.superadmin_manage_admin:
                            startActivity(new Intent(UserDetailsActivity.this, ManageAdminActivity.class));
                            break;

                        case R.id.superadmin_manage_users:
                            break;

                        case R.id.superadmin_manage_superadmin:
                            startActivity(new Intent(UserDetailsActivity.this, ManageSuperAdminActivity.class));
                            break;

                        case R.id.superadmin_settings:
                            startActivity(new Intent(UserDetailsActivity.this, AdminSettingsActivity.class));
                            break;
                        case R.id.superadmin_logout:
                            auth.signOut();
                            startActivity(new Intent(UserDetailsActivity.this, LoginActivity.class));
                            finish();
                            break;

                    }

                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
            });


        }
        else {

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {

                        case R.id.superadmin_homepage:
                            startActivity(new Intent(UserDetailsActivity.this, AdminHomepage.class));
                            break;

                        case R.id.superadmin_home:
                            startActivity(new Intent(UserDetailsActivity.this, SuperAdminDashboardActivity.class));
                            break;
                        case R.id.superadmin_manage_account:
                            startActivity( new Intent(UserDetailsActivity.this, ManageSuperAdminAccountActivity.class));
                            break;

                        case R.id.superadmin_manage_admin:
                            startActivity(new Intent(UserDetailsActivity.this, ManageAdminActivity.class));
                            break;

                        case R.id.superadmin_manage_users:
                            break;

                        case R.id.superadmin_manage_superadmin:
                            startActivity(new Intent(UserDetailsActivity.this, ManageSuperAdminActivity.class));
                            break;

                        case R.id.superadmin_settings:
                            startActivity(new Intent(UserDetailsActivity.this, AdminSettingsActivity.class));
                            break;
                        case R.id.superadmin_logout:
                            auth.signOut();
                            startActivity(new Intent(UserDetailsActivity.this, LoginActivity.class));
                            finish();
                            break;

                    }

                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
            });

        }











        /*
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.superadmin_homepage:
                        startActivity(new Intent(UserDetailsActivity.this, SuperAdminHomepage.class));
                        break;

                    case R.id.superadmin_home:
                        startActivity(new Intent(UserDetailsActivity.this, SuperAdminDashboardActivity.class));
                        break;
                    case R.id.superadmin_manage_account:
                        startActivity( new Intent(UserDetailsActivity.this, ManageSuperAdminAccountActivity.class));
                        break;

                    case R.id.superadmin_manage_admin:
                        startActivity(new Intent(UserDetailsActivity.this, ManageAdminActivity.class));
                        break;

                    case R.id.superadmin_manage_users:
                        break;

                    case R.id.superadmin_manage_superadmin:
                        startActivity(new Intent(UserDetailsActivity.this, ManageSuperAdminActivity.class));
                        break;

                    case R.id.superadmin_settings:
                        startActivity(new Intent(UserDetailsActivity.this, SuperAdminSettingsActivity.class));
                        break;
                    case R.id.superadmin_logout:
                        auth.signOut();
                        startActivity(new Intent(UserDetailsActivity.this, LoginActivity.class));
                        finish();
                        break;

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

         */



        //database referencing work

        /*reference=database.getReference("registered_users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot use : snapshot.getChildren())
                {
                    if(use.child("full_name").getValue().toString().equals(username))
                    {
                        userId= use.getKey();
                        Log.d("uid", userId );
                        Log.d("username",use.child("full_name").getValue().toString() );
                        Log.d("userId",use.getKey());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        //changing the text view for the users accordingly

        susUser=(TextView)findViewById(R.id.sus_user_name);

        susUser.setText("Suspects Registered By "+name);

        //List View operations starting from here

        userSusList=(ListView)findViewById(R.id.users_suspect_list);

        final ArrayList<suspect_registered> sus=new ArrayList<suspect_registered>();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot susSnap : snapshot.getChildren())
                {
                    suspect_registered suspectRegistered=susSnap.getValue(suspect_registered.class);
                    sus.add(suspectRegistered);
                }

                suspectSuperAdapter adapter=new suspectSuperAdapter(UserDetailsActivity.this,0,sus);

                userSusList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        actionBarDrawerToggle.syncState();
    }
}










/*
package com.example.womensafety.SuperAdmin.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.womensafety.Activities.LoginActivity;
import com.example.womensafety.Activities.SelectUserActivity;
import com.example.womensafety.Activities.SuspectListActivity;
import com.example.womensafety.Adapters.suspectAdapter;
import com.example.womensafety.Models.suspect_registered;
import com.example.womensafety.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDetailsActivity extends AppCompatActivity {

    ListView userSusList;
    TextView susUser;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;


    FirebaseDatabase database;
    DatabaseReference reference;

    public String name;
    public String mob_num;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        database=FirebaseDatabase.getInstance();

        auth=FirebaseAuth.getInstance();

        name=getIntent().getStringExtra("username");
        mob_num=getIntent().getStringExtra("mob");
        reference=database.getReference("suspects_registered").child(mob_num);

        setUpToolbar();
        navigationView = findViewById(R.id.navigationMenu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.superadmin_homepage:
                        startActivity(new Intent(UserDetailsActivity.this, SuperAdminHomepage.class));
                        break;

                    case R.id.superadmin_home:
                        startActivity(new Intent(UserDetailsActivity.this, SuperAdminDashboardActivity.class));
                        break;
                    case R.id.superadmin_manage_account:
                        startActivity( new Intent(UserDetailsActivity.this, SelectUserActivity.class));
                        break;

                    case R.id.superadmin_manage_admin:
                        startActivity(new Intent(UserDetailsActivity.this, ManageAdminActivity.class));
                        break;

                    case R.id.superadmin_manage_users:
                        break;

                    case R.id.superadmin_manage_superadmin:
                        startActivity(new Intent(UserDetailsActivity.this, ManageSuperAdminActivity.class));
                        break;

                    case R.id.superadmin_settings:
                        startActivity(new Intent(UserDetailsActivity.this, SuperAdminSettingsActivity.class));
                        break;
                    case R.id.superadmin_logout:
                        auth.signOut();
                        startActivity(new Intent(UserDetailsActivity.this, LoginActivity.class));
                        finish();
                        break;

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //database referencing work

        /*reference=database.getReference("registered_users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot use : snapshot.getChildren())
                {
                    if(use.child("full_name").getValue().toString().equals(username))
                    {
                        userId= use.getKey();
                        Log.d("uid", userId );
                        Log.d("username",use.child("full_name").getValue().toString() );
                        Log.d("userId",use.getKey());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        //changing the text view for the users accordingly
/*
        susUser=(TextView)findViewById(R.id.sus_user_name);

        susUser.setText("Suspects Registered By "+name);

        //List View operations starting from here

        userSusList=(ListView)findViewById(R.id.users_suspect_list);

        final ArrayList<suspect_registered> sus=new ArrayList<suspect_registered>();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot susSnap : snapshot.getChildren())
                {
                    suspect_registered suspectRegistered=susSnap.getValue(suspect_registered.class);
                    sus.add(suspectRegistered);
                }

                suspectAdapter adapter = new suspectAdapter(UserDetailsActivity.this,0,sus);
                userSusList.setAdapter(adapter);
                //suspectAdapter adapter=new suspectAdapter(UserDetailsActivity.this,0,sus);

                //userSusList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        actionBarDrawerToggle.syncState();
    }
}*/
