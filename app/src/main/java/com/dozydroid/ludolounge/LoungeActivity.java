package com.dozydroid.ludolounge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

public class LoungeActivity extends AppCompatActivity {

    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    String[] variantTitles = {
            "Snakes and Ladders",
            "Classic Ludo",
            "Quick Ludo",
            "Masters Ludo",
            "Variation 5",
            "Variation 6"
    };
    int[] variantLogoIDs = {
            R.drawable.snake_and_ladder,
            R.drawable.snake_and_ladder,
            R.drawable.snake_and_ladder,
            R.drawable.snake_and_ladder,
            R.drawable.snake_and_ladder,
            R.drawable.snake_and_ladder
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lounge);

        if(accessToken==null){
            Toast.makeText(this, "Please login first!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoungeActivity.this, LoginActivity.class);
            startActivity(i);
            this.finish();
        }

        Profile userProfile = Profile.getCurrentProfile();
        String userName = userProfile.getName();
        setTitle("Welcome "+userName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lounge_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                LoginManager.getInstance().logOut();
                Intent i = new Intent(LoungeActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                break;
        }
        return true;
    }
}
