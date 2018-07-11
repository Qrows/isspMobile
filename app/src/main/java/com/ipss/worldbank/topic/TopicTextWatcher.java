package com.ipss.worldbank.topic;

import android.text.Editable;
import android.text.TextWatcher;

import com.ipss.worldbank.entity.Topic;

public class TopicTextWatcher implements TextWatcher {
    private Topic[] topics;
    private TopicAdapter adapter;
    private Topic[] filteredTopics;

    public TopicTextWatcher(Topic[] topics, Topic[] filteredTopics, TopicAdapter adapter) {
        this.topics = topics;
        this.filteredTopics = filteredTopics;
        this.adapter = adapter;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String string = s.toString();
        int j = 0;

        for (int i = 0; i < topics.length; i++) {
            if ((topics[i].getValue().toLowerCase().startsWith(string))) {
                filteredTopics[j] = topics[i];
                j++;
            }
        }

        for (;j < filteredTopics.length; j++) {
            filteredTopics[j] = null;
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
