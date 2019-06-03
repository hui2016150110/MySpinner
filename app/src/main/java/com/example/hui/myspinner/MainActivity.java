package com.example.hui.myspinner;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.myspinner);
        /**这个方法接受三个参数
        第一个是上下文这里传入this就可以
        第二个是一个int的资源，也就是我们下拉框要显示的数据源
        第三个还是一个int的资源，这里是我们刚才新建的布局资源
        */
        final ArrayAdapter adapter = MyAdapter.createFromResource(this,R.array.datalist,R.layout.spinner_layout);
        spinner.setBackgroundColor(0x0);
        //加载下拉框的样式
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setPopupBackgroundResource(R.drawable.chat2);
        spinner.setDropDownVerticalOffset(dip2px(20));
        spinner.setDropDownWidth(dip2px(180));
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((MyAdapter) adapter).setPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    private  int dip2px(float dpValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}

class MyAdapter<T> extends ArrayAdapter<T>{

    private int position;
    public MyAdapter(@NonNull Context context, int resource, @NonNull T[] objects) {
        super(context, resource, objects);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView textView = (TextView)view;
        if(this.position == position){
            textView.setTextColor(0xff373741);
            textView.getPaint().setFakeBoldText(true);
        }
        else{
            textView.setTextColor(0xff6d6d6d);
            textView.getPaint().setFakeBoldText(false);
        }
        return view;
    }

    public static @NonNull MyAdapter<CharSequence> createFromResource(@NonNull Context context,
                                                                           @ArrayRes int textArrayResId, @LayoutRes int textViewResId) {
        final CharSequence[] strings = context.getResources().getTextArray(textArrayResId);
        return new MyAdapter<>(context, textViewResId, strings);
    }

}
