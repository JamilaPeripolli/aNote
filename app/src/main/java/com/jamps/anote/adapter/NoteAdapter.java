package com.jamps.anote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jamps.anote.R;
import com.jamps.anote.model.Note;

import java.util.List;

public class NoteAdapter extends BaseAdapter{

    private Context context;
    private List<Note> notes;

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = LayoutInflater.from(context).inflate(R.layout.note_list_item,parent, false);
        ImageView importantWarninigImage = (ImageView) listItem.findViewById(R.id.iv_important);
        TextView title = (TextView) listItem.findViewById(R.id.tv_title);

        Note note = notes.get(position);
        if(!note.isImportant()) {
            importantWarninigImage.setVisibility(View.INVISIBLE);
        }
        title.setText(note.getTitle());

        return listItem;
    }
}
