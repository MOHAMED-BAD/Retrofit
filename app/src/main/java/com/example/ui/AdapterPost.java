package com.example.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.Post;
import com.example.retrofit.R;


public class AdapterPost extends ListAdapter<Post, AdapterPost.ContactViewHolder> {
    static DiffUtil.ItemCallback<Post> diffCallback = new DiffUtil.ItemCallback<Post>() {

        @Override
        public boolean areItemsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.getBody().equals(newItem.getBody());
        }
    };

    protected AdapterPost() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.onBind(getItem(position));

    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView id, title, body;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.userid);
            title = itemView.findViewById(R.id.tittle);
            body = itemView.findViewById(R.id.body);

        }

        void onBind(Post posts) {
            id.setText(String.valueOf(posts.getUserId()));
            title.setText(posts.getTitle());
            body.setText(posts.getBody());
        }


    }
}

