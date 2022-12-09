package com.example.finaltesttable;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondPageFragment extends Fragment {

    ListView listView;
    String[] history={"时间1","时间2"};
    MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.second_page, container, false);

        mainActivity=(MainActivity) getActivity();
        history = new String[mainActivity.getRecordArrayList().size()];


        initialHistory();



        listView=view.findViewById(R.id.history);
        listView.setAdapter(new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_checked, history));




        //绑定监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SecondPageFragment.this.getContext(), "你选择了第" + (i+1) + "条记录", Toast.LENGTH_SHORT).show();
                mainActivity.setRecord(mainActivity.getRecordArrayList().get(i));
            }
        });

        return view;
    }

    public void initialHistory(){//更新历史记录
        int i=0;
        for (Record record:mainActivity.getRecordArrayList()){
            history[i]=record.getId();
            i++;
        }
    }
}
