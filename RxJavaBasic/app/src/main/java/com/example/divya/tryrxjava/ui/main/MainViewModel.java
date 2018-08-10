package com.example.divya.tryrxjava.ui.main;

import android.arch.lifecycle.ViewModel;

import com.example.divya.tryrxjava.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


public class MainViewModel extends ViewModel {

    Observable getUserLists()
    {
        List<User> users = prepareUsers();
        return  Observable.fromIterable(users);
    }

    List<User> prepareUsers()
    {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "User1"));
        list.add(new User(2, "User2"));
        list.add(new User(3, "User3"));
        list.add(new User(4, "User4"));
        list.add(new User(5, "User5"));
        return list;

    }
}
