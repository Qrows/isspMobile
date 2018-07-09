package com.ipss.worldbank.topic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import com.ipss.worldbank.R;

import java.util.LinkedList;

public class ScrollViewTopicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        LinkedList<Topic> topics = new LinkedList<>();
        topics.add(new Topic("Topic 1", "Sono il primo topic. Questo popup è molto bello"));
        topics.add(new Topic("Beatrice non commentare", "Mi sto divertendo molto a scrivere queste cose, risparmiami quella faccia"));
        topics.add(new Topic("Topic 1", "Sono il primo topic. Questo popup è molto bello"));
        topics.add(new Topic("Beatrice non commentare", "Mi sto divertendo molto a scrivere queste cose, risparmiami quella faccia"));
        topics.add(new Topic("Topic 1", "Sono il primo topic. Questo popup è molto bello"));
        topics.add(new Topic("Beatrice non commentare", "Mi sto divertendo molto a scrivere queste cose, risparmiami quella faccia"));
        topics.add(new Topic("Topic 1", "Sono il primo topic. Questo popup è molto bello"));
        topics.add(new Topic("Beatrice non commentare", "Mi sto divertendo molto a scrivere queste cose, risparmiami quella faccia"));
        topics.add(new Topic("Topic 1", "Sono il primo topic. Questo popup è molto bello"));
        topics.add(new Topic("Beatrice non commentare", "Mi sto divertendo molto a scrivere queste cose, risparmiami quella faccia"));
        topics.add(new Topic("Topic 1", "Sono il primo topic. Questo popup è molto bello"));
        topics.add(new Topic("Beatrice non commentare", "Mi sto divertendo molto a scrivere queste cose, risparmiami quella faccia"));
        topics.add(new Topic("Topic 1", "Sono il primo topic. Questo popup è molto bello"));
        topics.add(new Topic("Beatrice non commentare", "Mi sto divertendo molto a scrivere queste cose, risparmiami quella faccia"));
        topics.add(new Topic("Topic 1", "Sono il primo topic. Questo popup è molto bello"));
        topics.add(new Topic("Beatrice non commentare", "Mi sto divertendo molto a scrivere queste cose, risparmiami quella faccia"));
        topics.add(new Topic("Topic 1", "Sono il primo topic. Questo popup è molto bello"));
        topics.add(new Topic("Beatrice non commentare", "Mi sto divertendo molto a scrivere queste cose, risparmiami quella faccia"));
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_scrollview);
        EditText searchRt = findViewById(R.id.searchEt);
        searchRt.setHint(R.string.topic_hint);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new TopicAdapter(topics, this));
    }
}