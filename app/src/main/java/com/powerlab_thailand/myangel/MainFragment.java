package com.powerlab_thailand.myangel;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Register Controller
        registerController();

//        Login Controler
        loginControler();


    } //Main Method

    private void loginControler() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText emailEditext = getView().findViewById(R.id.edtEmail);
                EditText passwordEditText = getView().findViewById((R.id.edtPassword));

                String emailString = emailEditext.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                final MyAlert myAlert = new MyAlert(getActivity());

                if (emailString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    myAlert.normalDialog(getString(R.string.title_have_space), getString(R.string.message_have_space));

                } else {
//                    No Space
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(emailString, passwordString)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        getActivity().getSupportFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.contentMainFragment, new RealtimeFragment())
                                                .commit();

                                    } else {
                                        myAlert.normalDialog("Authen Fail", task.getException().toString());
                                    }
                                }
                            });

                }
            }
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Replace Fragment
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null).commit();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

} //Main Class
