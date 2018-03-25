package com.wander.notes.di;


import com.wander.notes.view.ui.AddNoteActivity;
import com.wander.notes.view.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector()
    abstract AddNoteActivity contributeAddNoteActivity();
}
