package com.example.finaltesttable;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private String name;
    private String sex;
    private String code;
    private String post;
    private String phone;
    private String birth;
    private String id;

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", code='" + code + '\'' +
                ", post='" + post + '\'' +
                ", phone='" + phone + '\'' +
                ", birth='" + birth + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Record(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @SuppressLint("Range")
    public static ArrayList<Record> selectAll(SQLiteDatabase db) {//获取所有的记录
        Cursor cursor = db.query("Record",null,null,null,null,null,null);
        ArrayList<Record> recordList = new ArrayList<Record>();
        if (cursor.moveToFirst()) {//将结果打包回传
            do {
                Record record = new Record();
                record.setName(cursor.getString(cursor.getColumnIndex("name")));
                record.setCode(cursor.getString(cursor.getColumnIndex("code")));
                record.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                record.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                record.setPost(cursor.getString(cursor.getColumnIndex("post")));
                record.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
                record.setId(cursor.getString(cursor.getColumnIndex("id")));
                recordList.add(record);
            } while (cursor.moveToNext());
        }
        return recordList;
    }

    public void insert(SQLiteDatabase db){
        db.execSQL("INSERT INTO Record(name,phone,code,sex,post,birth) values(?,?,?,?,?,?)",
                new String[]{this.getName(),this.getPhone(),getCode(),getSex(),getPost(),getBirth()});
    }

    public void update(SQLiteDatabase db){
        db.execSQL("UPDATE Record SET name = ?,phone = ?,code = ?,sex= ?,post=?,birth=? WHERE id = ?",
                new String[]{this.getName(),this.getPhone(),getCode(),getSex(),getPost(),getBirth(),getId()});
    }

    public void delete(SQLiteDatabase db){
        db.delete("Record","id=?",new String[]{getId()});
    }
}
