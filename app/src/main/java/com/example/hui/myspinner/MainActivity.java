package com.example.hui.myspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.datalist,R.layout.spinner_layout);
        spinner.setBackgroundColor(0x0);
        //加载下拉框的样式
        adapter.setDropDownViewResource(R.layout.spinner_item);

        spinner.setAdapter(adapter);
    }
}
