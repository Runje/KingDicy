package com.example.thomas.dicyplayground.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Model.User;
import com.example.thomas.dicyplayground.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 03.04.2016.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>
{
    private List<String> chat;

    public ChatAdapter()
    {
        chat = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.textView.setText(chat.get(position));
    }

    @Override
    public int getItemCount()
    {
        return chat.size();
    }

    public ChatAdapter(List<String> users)
    {
        this.chat = users;
    }

    public void setChat(List<String> chat)
    {
       this.chat = chat;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;

        public ViewHolder(View view)
        {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.username);
        }
    }
}
