package com.example.interface1.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.interface1.CustomArrayAdapter;
import com.example.interface1.ListItemClass;
import com.example.interface1.R;
import com.example.interface1.databinding.FragmentHomeBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private Document doc;
    private Document doc1;
    private Thread secThread;
    private Runnable runnable;
    private ListView listView;
    private CustomArrayAdapter adapter;
    private List<ListItemClass> arrayList;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)root.findViewById(R.id.refreshLayout);


        listView = (ListView)root.findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(getActivity(), R.layout.listitem1, arrayList, getLayoutInflater());
        listView.setAdapter(adapter);
        init();

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        // Your code goes here
                        // In this code, we are just
                        // changing the text in the textbox
                        init();







                        // This line is important as it explicitly
                        // refreshes only once
                        // If "true" it implicitly refreshes forever
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    private void init() {


        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();

    }
    private void getWeb() {
        try {
            doc = Jsoup.connect("https://www.banki.ru/products/currency/cash/kursk/").get();
            doc1=Jsoup.connect("https://time100.ru/").get();
            Elements t=doc1.getElementsByTag("h3");
            Element t1=t.get(0);
            Elements tables = doc.getElementsByTag("tbody");
            Element table = tables.get(0);
            Elements elements_from_table = table.children();
            Log.d("MyLog", "size: " + table.children().get(1).child(6).text());

            ListItemClass items = new ListItemClass();
            items.setData_1(table.children().get(1).child(1).text());
            items.setData_2(table.children().get(2).child(1).text());
            items.setData_3(table.children().get(2).child(2).text());
            items.setData_4(table.children().get(2).child(4).text());
            //items.setData_5(table.children().get(4).child(1).text());
            items.setData_5(t1.text());
            arrayList.clear();

            arrayList.add(items);

            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();

                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}