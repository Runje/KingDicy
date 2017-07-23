package com.example.thomas.dicyplayground.Views;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Model.User;
import com.example.OldModel.Logger;
import com.example.thomas.dicyplayground.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Thomas on 03.04.2016.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>
{
    private List<User> users;
    private boolean[] selectedUsers;

    public UserListAdapter()
    {
        users = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v, (viewHolder, position) -> {
            selectItem(position);
        });
        return vh;
    }

    private void selectItem(int position)
    {
        for (int i = 0; i < selectedUsers.length; i++)
        {
            selectedUsers[i] = i == position;
        }

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.textView.setText(users.get(position).toString());
        holder.setSelected(selectedUsers[position]);
    }

    @Override
    public int getItemCount()
    {
        return users.size();
    }

    public UserListAdapter(List<User> users)
    {
        setUsers(users);
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
        selectedUsers = new boolean[users.size()];
    }

    public User getSelectedUser()
    {
        for (int i = 0; i < selectedUsers.length; i++)
        {
            if (selectedUsers[i])
            {
                return users.get(i);
            }
        }

        Logger.e("User not found or no user selected");
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener
    {
        public TextView textView;
        private UserClickListener userClickListener;

        public ViewHolder(View view, UserClickListener userClickListener)
        {
            super(view);
            this.userClickListener = userClickListener;
            this.textView = (TextView) view.findViewById(R.id.username);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Logger.i("Click item: " + getAdapterPosition());
            if (userClickListener != null)
            {
                userClickListener.onClick(this, getAdapterPosition());
            }
        }

        public void setSelected(boolean selected)
        {
            itemView.setSelected(selected);
        }

        public interface UserClickListener
        {
            void onClick(ViewHolder viewHolder, int position);
        }
    }
}
