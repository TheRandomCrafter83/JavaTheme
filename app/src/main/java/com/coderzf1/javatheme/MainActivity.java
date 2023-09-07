package com.coderzf1.javatheme;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.coderzf1.javatheme.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean useSystemTheme = true;
    private boolean isDark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                isDark = true;
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                isDark = false;
                break;
        }
        binding.useDarkMode.setChecked(isDark);
        binding.useSystemDefaults.setOnCheckedChangeListener((compoundButton, checked)->{
            useSystemTheme = checked;
            binding.useDarkMode.setEnabled(!checked);
            setTheme(useSystemTheme,isDark);
        });
        binding.useDarkMode.setOnCheckedChangeListener((compoundButton, checked) -> {
            isDark = checked;
            setTheme(useSystemTheme,isDark);
        });
    }

    void setTheme(boolean useSystemTheme, boolean isDark){
        if (useSystemTheme)AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        if(!useSystemTheme){
            if (isDark) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            if (!isDark) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


}