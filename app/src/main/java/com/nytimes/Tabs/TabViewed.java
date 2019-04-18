package com.nytimes.Tabs;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.nytimes.Adapters.MyRecyclerAdapter;
import com.nytimes.Retrofit.Common;
import com.nytimes.Model.News;
import com.nytimes.Model.Result;
import com.nytimes.R;
import com.nytimes.Retrofit.MyAPI;
import com.nytimes.Retrofit.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class TabViewed extends Fragment {
    View view;
    MyAPI mAPI;
    RecyclerView mRecyclerView;
    CompositeDisposable mCompositeDisposable;
    TextView noConnect;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Common.isNetworkAvailable(getContext())){
            mCompositeDisposable = new CompositeDisposable();
            Retrofit retrofit = RetrofitClient.getInstance();
            mAPI = retrofit.create(MyAPI.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_viewed, container, false);
        noConnect = view.findViewById(R.id.no_internet_v);
        mRecyclerView = view.findViewById(R.id.recycler_view_viewed);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (Common.isNetworkAvailable(getContext())) {
            fetchData();
        }else {
            noConnect.setVisibility(View.VISIBLE);
        }
        return view;
    }

    private void fetchData() {
        mCompositeDisposable.add(mAPI.getResults(Common.getAPIUrl("mostviewed"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        displayData(news.getResults());
                    }
                })
        );
    }

    private void displayData(List<Result> results) {
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(getContext(),results);
        mRecyclerView.setAdapter(adapter);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mCompositeDisposable!=null)mCompositeDisposable.clear();
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
