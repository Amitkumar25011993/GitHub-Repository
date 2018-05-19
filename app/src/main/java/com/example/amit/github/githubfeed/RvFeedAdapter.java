package com.example.amit.github.githubfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.amit.github.R;
import com.example.amit.github.model.GithubResponse;

import java.util.List;

/**
 * Created by amit on 5/19/2018.
 */

public class RvFeedAdapter extends RecyclerView.Adapter<RvFeedAdapter.Holder> {

    List<GithubResponse> mGithubResponseList;
    Context c;

    public RvFeedAdapter(List<GithubResponse> githubResponseList, Context c){
        this.mGithubResponseList = githubResponseList;
        this.c = c;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.rv_item_feed, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder h, int position) {
        GithubResponse gr = mGithubResponseList.get(position);
        h.tvRepoName.setText(gr.name);
        h.tvRepoDesc.setText(gr.description);

    }

    @Override
    public int getItemCount() {
        return mGithubResponseList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView tvRepoName;
        TextView tvRepoDesc;

        public Holder(View v) {
            super(v);
            tvRepoName = (TextView) v.findViewById(R.id.tv_repo_name);
            tvRepoDesc = (TextView) v.findViewById(R.id.tv_repo_desc);
        }
    }
}
