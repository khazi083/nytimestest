package com.nytimes.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nytimes.Model.Result;
import com.nytimes.R;
import com.nytimes.databinding.ActivityDetailsBinding;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            Result news = (Result) getIntent().getParcelableExtra("SELECTED_NEWS");
            String urlImage=getIntent().getStringExtra("url");
            ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
            binding.byline.setText(news.getByline());
            binding.title.setText(news.getTitle());
            binding.pubDate.setText(news.getPublishedDate());
            Picasso.get()
                    .load(urlImage)
                    .error(R.drawable.article)
                    .placeholder(R.drawable.progress)
                    .into(binding.newsPicture);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }