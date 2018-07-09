package com.ipss.worldbank.topic;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ipss.worldbank.R;

import java.util.LinkedList;

public class TopicAdapter extends BaseAdapter {
    private LinkedList<Topic> topics;
    private LayoutInflater inflater;
    private Context context;

    public TopicAdapter(LinkedList<Topic> topics, Context context) {
        this.topics = topics;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return topics.size();
    }

    @Override
    public Object getItem(int position) {
        return topics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View rowView;

        if (position % 2 == 0) {
            rowView = inflater.inflate(R.layout.scrollview_topic_item_light, parent, false);
        } else {
            rowView = inflater.inflate(R.layout.scrollview_topic_item_dark, parent, false);
        }

        TextView topicTv = rowView.findViewById(R.id.topicTv);
        Button helpButtonTopic = rowView.findViewById(R.id.indicatorHelpBtn);
        final Topic topic = topics.get(position);

        topicTv.setTypeface(null, Typeface.BOLD);
        topicTv.setText(topic.getName());
        helpButtonTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle(R.string.description);
                dialog.setMessage(topic.getDescription());
                dialog.setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        return rowView;
    }
}
