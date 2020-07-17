package com.example.bilal.hadathfinal2015;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Bilal on 10/15/2015.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private final LayoutInflater layoutInflater;

    class ContactViewHolder extends RecyclerView.ViewHolder {

        private final TextView contactNameTV;
        private final TextView phoneNumberTV;
        private final CheckBox favoriteBtn;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactNameTV = itemView.findViewById(R.id.contact_name);
            phoneNumberTV = itemView.findViewById(R.id.phone_number);
            favoriteBtn = itemView.findViewById(R.id.favorite_btn);
        }
    }

    private Context context;
    private ArrayList<Contact> contactsList;


    public ContactListAdapter(Context context, ArrayList<Contact> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactViewItem = layoutInflater.inflate(R.layout.contact_view, parent, false);
        return new ContactViewHolder(contactViewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactViewHolder holder, int position) {
        final Contact contact = contactsList.get(position);
        holder.contactNameTV.setText(contact.getName());
        holder.phoneNumberTV.setText(contact.getPhoneNumber());
        holder.favoriteBtn.setChecked(contact.isFavorite());

        holder.favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact.setIsFavorite(holder.favoriteBtn.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}
