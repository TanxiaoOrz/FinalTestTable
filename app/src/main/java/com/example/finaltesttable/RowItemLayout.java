package com.example.finaltesttable;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.FrameLayout;

public class RowItemLayout extends FrameLayout {

        private Context mContext;
        private View mView;
        private TextView titleTv;
        private EditText editTv;
        public static final int NO_LINE = 0;
        public static final int DIVIDE_LINE = 1;
        public static final int DIVIDE_AREA = 2;
        public int divideLineStyle = NO_LINE;
        private boolean isShowRedHintImg = false;
        private OnClickListener onClickListener;

        public void setViewOnlickListener(OnClickListener onClickListener) { //设置案件监听器
            this.onClickListener = onClickListener;
            mView.setOnClickListener(onClickListener);
        }


        public void setTitleText(String titleText) {//设置指示栏文字
            if (titleText != null) {
                System.out.println(titleText);
                titleTv.setText(titleText);
            }
        }

        public void setHintText(String hintText) {//设置编辑栏提示文字
            if (hintText != null) {
                editTv.setHint(hintText);
            }
        }

        public void setEditText(String editText){//设置编辑蓝文字
            if (editText != null){
                System.out.println(editText);
                editTv.setText(editText);
            }
        }

        public RowItemLayout(Context context) {
            this(context, null);
        }

        public RowItemLayout(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public RowItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context, attrs);
        }

        private void init(Context context, AttributeSet attrs) {//根据布局文件撤职初始化
            mContext = context;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(R.layout.row_item, this, true);
            titleTv = (TextView) mView.findViewById(R.id.menu_item_text);
            editTv = (EditText)  mView.findViewById(R.id.menu_item_edit);


            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.RowItemLayout);//根据attr设置
            setTitleText(a.getString(R.styleable.RowItemLayout_title_text));
            System.out.println(a.getString(R.styleable.RowItemLayout_hint_text));//某次颜色设置出错的调试代码
            setHintText(a.getString(R.styleable.RowItemLayout_hint_text));
            divideLineStyle = a.getInt(R.styleable.RowItemLayout_divide_line_style, NO_LINE);
            setDivideLine(divideLineStyle);
        }

        public void setDivideLine(int bootomLineStyle) {//根据传入值设置上下分割线的种类
            View lineView = findViewById(R.id.divide_line_view);
            View areaView = findViewById(R.id.divide_area_view);
            lineView.setVisibility(GONE);
            areaView.setVisibility(GONE);
            if (bootomLineStyle == DIVIDE_LINE) {
                lineView.setVisibility(VISIBLE);
            } else if (bootomLineStyle == DIVIDE_AREA) {
                areaView.setVisibility(VISIBLE);
            }
        }

        public String getEditTvText() {
            return editTv.getText().toString();
        }

}
