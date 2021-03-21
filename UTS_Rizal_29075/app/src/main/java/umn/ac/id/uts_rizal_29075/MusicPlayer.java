package umn.ac.id.uts_rizal_29075;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class MusicPlayer extends AppCompatActivity {

    private Button menu;

    public static final int REQUEST_CODE=1;
    static ArrayList<MusicFiles> musicFiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        permission();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selamat Datang").setMessage("Mochamad Rizal" + "\n" + "00000029075").setPositiveButton("Tutup",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();

        menu = (Button) findViewById(R.id.btnMenu);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showButton(v);
            }
        });

    }

    private void showButton(View v) {
        PopupMenu popmenu = new PopupMenu(MusicPlayer.this, v);
        popmenu.getMenuInflater().inflate(R.menu.menu, popmenu.getMenu());
        popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.mainprofile)
                    keProfile();

                if (item.getItemId() == R.id.logintab)
                    keLogin();

                return false;
            }
        });
        popmenu.show();
    }

    private void keProfile() {
        Intent intent = new Intent(this,MainProfile.class);
        startActivity(intent);
    }

    private void keLogin() {
        Intent intent = new Intent(this,Logintab.class);
        startActivity(intent);
    }

    private void permission() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MusicPlayer.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    , REQUEST_CODE);
        } else {

            musicFiles = getAllAudio(this);
            initViewPager();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                musicFiles = getAllAudio(this);
                initViewPager();
            } else {
                ActivityCompat.requestPermissions(MusicPlayer.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        , REQUEST_CODE);
            }
        }
    }

    private void initViewPager() {
        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new SongFragment(), "Songs");
        viewPager.setAdapter(viewPagerAdapter);

    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
            this.fragments=new ArrayList<>();
            this.titles = new ArrayList<>();
        }


        void addFragments(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    public static ArrayList<MusicFiles> getAllAudio(Context context)
    {
        ArrayList<MusicFiles> tempAudioList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA, // path
                MediaStore.Audio.Media.ARTIST
        };
        Cursor cursor = context.getContentResolver().query(uri,projection,null,null,null);
        if(cursor != null) {
            while(cursor.moveToNext()) {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String path = cursor.getString(3);
                String artist = cursor.getString(4);

                MusicFiles musicFiles = new MusicFiles(path, title, artist, album, duration);
                Log.e("Path: " + path, "Album: " + album);
                tempAudioList.add(musicFiles);
            }

            cursor.close();
        }
        return tempAudioList;
    }




}