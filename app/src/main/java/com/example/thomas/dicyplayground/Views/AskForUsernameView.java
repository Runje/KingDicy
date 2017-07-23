package com.example.thomas.dicyplayground.Views;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.widget.EditText;

import com.example.thomas.dicyplayground.R;

/**
 * Created by Thomas on 03.04.2016.
 */
public class AskForUsernameView
{
    private final UsernameSetter usernameSetter;
    Handler handler;
    Context context;

    public AskForUsernameView(Context context, UsernameSetter usernameSetter)
    {
        handler = new Handler(context.getMainLooper());
        this.context = context;
        this.usernameSetter =  usernameSetter;
    }

    public void show()
    {
        handler.post(() ->
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            EditText editText = new EditText(context);
            alert.setView(editText);
            alert.setMessage(R.string.enter_username);
            alert.setPositiveButton("OK", (dialog, which) -> {
                String name = editText.getText().toString();
                usernameSetter.setUsername(name);
            });

            alert.show();
        });
    }

    public interface UsernameSetter
    {
        void setUsername(String name);
    }
}
