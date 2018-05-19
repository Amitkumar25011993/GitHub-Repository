package com.example.amit.github.githubfeed;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.amit.github.MainActivity;
import com.example.amit.github.R;
import com.example.amit.github.model.GithubResponse;
import com.example.amit.github.model.Owner;
import com.example.amit.github.network.RetroFitClient;
import com.example.amit.github.utility.StreamDrawable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

public class FeedActivity extends AppCompatActivity {

    public static final String INTENT_USERNAME = "intent_username";

    RecyclerView rvFeed;
    TextView tvUsername;
    ImageView ivAvatar;
    FloatingActionButton fab;
    FrameLayout progressBar;
//    ContentLoadingProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        initViews();

        final String username = getIntent().getStringExtra(INTENT_USERNAME);
        RetroFitClient rfClient = new RetroFitClient();
        toggleProgressBar(true);
        rfClient.getUser(username, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<GithubResponse> ghResponse = (List<GithubResponse>) response.body();
                if (ghResponse!=null) {
                    if (ghResponse.size() > 0) {
                        initFeedRecyclerView(ghResponse);
                    } else {
                        Toast.makeText(FeedActivity.this, ">- Didn't found with " + username + "-<", Toast.LENGTH_SHORT).show();
                    }
                }
                toggleProgressBar(false);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                toggleProgressBar(false);
            }
        });
    }



    private SimpleTarget target = new SimpleTarget<Bitmap>(200,200) {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {

             final int CORNER_RADIUS = 4; // dips
             final int MARGIN = 0; // dips

            final float density = FeedActivity.this.getResources().getDisplayMetrics().density;
           int  mCornerRadius = (int) (CORNER_RADIUS * density + 0.5f);
           int  mMargin = (int) (MARGIN * density + 0.5f);
            StreamDrawable sd = new StreamDrawable(bitmap, mCornerRadius, mMargin);
            ivAvatar.setImageDrawable(sd);
        }
    };

    void initFeedRecyclerView(List<GithubResponse> githubResponseList){
        Owner owner = githubResponseList.get(0).getOwner();
        Glide.with(this).load(owner.getAvatar_url()).asBitmap().into(target);
        tvUsername.setText(owner.getLogin());
        rvFeed.setAdapter(new RvFeedAdapter(githubResponseList, this));
        rvFeed.setLayoutManager(new LinearLayoutManager(this));
    }


    void toggleProgressBar(boolean enable){
        if (enable){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    void initViews(){
        rvFeed = (RecyclerView) findViewById(R.id.rv_feed);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        progressBar = (FrameLayout) findViewById(R.id.progress_bar_holder);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeedActivity.this, MainActivity.class));
                finish();
            }
        });

//        progressBar = (ContentLoadingProgressBar) findViewById(R.id.progressbar);
    }

}
