package com.example.finaltesttable;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.HashMap;

public class FirstPageFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private RowItemLayout name;
    private RowItemLayout sex;
    private RowItemLayout code;
    private RowItemLayout post;
    private RowItemLayout phone;
    private RowItemLayout birth;
    private MainActivity mainActivity;

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

        View view = inflater.inflate(R.layout.first_page, container, false);
        name = view.findViewById(R.id.name);
        sex = view.findViewById(R.id.sex);
        code = view.findViewById(R.id.code);
        post = view.findViewById(R.id.post);
        phone = view.findViewById(R.id.phone);
        birth = view.findViewById(R.id.birth);
        birth.setViewOnlickListener(new View.OnClickListener() {//设置监听器，打开日期控件
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(FirstPageFragment.this.getContext(),FirstPageFragment.this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                dialog.show();
            }
        });
        initialData();
        mainActivity =(MainActivity) getActivity();
        if (mainActivity!=null){
            mainActivity.setFirstPageFragment(this);
        }//让mainActivity知道自己
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initialData();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        birth.setEditText(i+"年"+i1+"月"+i2+"日");
    }

    public void initialData() {
        if (mainActivity!=null){
            String[] context = mainActivity.getContext();//获取数据
            if (context!=null) {
                name.setEditText(context[0]);//填入
                sex.setEditText(context[1]);
                code.setEditText(context[2]);
                post.setEditText(context[3]);
                phone.setEditText(context[4]);
                birth.setEditText(context[5]);
            }
        }
    }

    public void loadRecord() {
        if (mainActivity!=null){
            Record record = mainActivity.getRecord();
            name.setEditText(record.getName());
            birth.setEditText(record.getBirth());
            post.setEditText(record.getPost());
            phone.setEditText(record.getPhone());
            sex.setEditText(record.getSex());
            code.setEditText(record.getCode());
        }
    }

    public Record getRecord() {
        Record record = new Record();
        if (mainActivity!=null){
            record.setName(name.getEditTvText());
            record.setBirth(birth.getEditTvText());
            record.setPost(post.getEditTvText());
            record.setPhone(phone.getEditTvText());
            record.setSex(sex.getEditTvText());
            record.setCode(code.getEditTvText());
        }
        return record;
    }

    @Override
    public void onPause() {
        if (mainActivity!=null){//组装存储填写数据
            String[] context = new String[6];
            context[0] = name.getEditTvText();
            context[1] = sex.getEditTvText();
            context[2] = code.getEditTvText();
            context[3] = post.getEditTvText();
            context[4] = phone.getEditTvText();
            context[5] = birth.getEditTvText();
            mainActivity.setContext(context);
            for (String str:context)
                System.out.println(str);
        }
        super.onPause();
    }
}
