package com.example.divya.tryrxjava.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.divya.tryrxjava.R;
import com.example.divya.tryrxjava.model.User;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    Disposable disposable;
    Observer<User> userObserver;
    private static final String TAG = "RxJava Sample App : ";
    private TextView message;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        message = (TextView) v.findViewById(R.id.message);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onStart() {
        super.onStart();

        userObserver = getUsersObserver();
        mViewModel.getUserLists().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getUsersObserver());

    }

    private Observer<User> getUsersObserver()
    {
        return new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                message.setText("onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(User user) {
                Log.d(TAG, "User Name " + user.getName());
                message.setText(message.getText() + "\nUser Name " + user.getName());
           }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
                message.setText(message.getText() + "\nonComplete");
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
