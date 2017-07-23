package com.example.thomas.dicyplayground.Views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.example.Model.User;
import com.example.thomas.dicyplayground.R;

/**
 * Created by Thomas on 04.04.2016.
 */
public class ChallengePlayerView
{
    private final Context context;
    private final User player;
    private final Handler handler;
    private AnswerListener listener;

    public ChallengePlayerView(@NonNull Context context, @NonNull User player, @NonNull AnswerListener listener)
    {
        this.listener = listener;
        this.handler = new Handler(context.getMainLooper());
        this.context = context;
        this.player = player;
    }

    public void show()
    {
        handler.post(() ->
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            // TODO: string resource with argument
            alert.setMessage(player + " fordert dich heraus. Möchtest du gegen " + player + " spielen?");
            alert.setPositiveButton(R.string.yes, (dialog, which) -> {
                listener.answer(true);
            });

            alert.setNegativeButton(R.string.no, (dialog, which) -> {
                listener.answer(false);
            });

            alert.show();
        });
    }

    public interface AnswerListener
    {
        void answer(boolean accepted);
    }
}
