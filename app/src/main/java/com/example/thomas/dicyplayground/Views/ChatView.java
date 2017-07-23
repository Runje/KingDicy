package com.example.thomas.dicyplayground.Views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.Model.User;

import java.util.List;

/**
 * Created by Thomas on 03.04.2016.
 */
public class ChatView
{
    private final ChatAdapter adapter;
    private RecyclerView list;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;

    public ChatView(RecyclerView list, Context context)
    {
        this.list = list;
        this.context = context;

        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);

        list.setLayoutManager(layoutManager);
        adapter = new ChatAdapter();
        list.setAdapter(adapter);
    }

    public void setChat(List<String> chat)
    {
        adapter.setChat(chat);
    }

    public void refresh()
    {
        adapter.notifyDataSetChanged();
    }
}
