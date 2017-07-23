package com.example.thomas.dicyplayground.Views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.Model.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Thomas on 03.04.2016.
 */
public class UserListView
{
    private final UserListAdapter adapter;
    private RecyclerView list;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;

    public UserListView(RecyclerView list, Context context)
    {
        this.list = list;
        this.context = context;

        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);

        list.setLayoutManager(layoutManager);
        adapter = new UserListAdapter();
        list.setAdapter(adapter);

    }

    public void setUsers(List<User> users)
    {
        adapter.setUsers(users);
    }

    public void refresh()
    {
        adapter.notifyDataSetChanged();
    }

    public User getSelectedUser()
    {
        return adapter.getSelectedUser();
    }
}
