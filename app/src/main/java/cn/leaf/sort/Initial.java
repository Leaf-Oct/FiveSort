package cn.leaf.sort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class Initial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        getSupportActionBar().setTitle("初始化中");
        Intent i=new Intent(this,MainActivity.class);
        int a[]=new int[100];
        Sort.randomArray(a);
        Sort.bubbleSort(a);
        i.putExtra("bubble","冒泡 "+printArray(a));
        Sort.randomArray(a);
        Sort.chooseSort(a);
        i.putExtra("choose","选择 "+printArray(a));
        Sort.randomArray(a);
        Sort.insertSort(a);
        i.putExtra("insert","插入"+printArray(a));
        Sort.randomArray(a);
        Sort.mergeSort(a);
        i.putExtra("merge","归并"+printArray(a));
        Sort.randomArray(a);
        Sort.quickSort(a);
        i.putExtra("quick","快速"+printArray(a));
        findViewById(R.id.loading).setVisibility(View.GONE);
        startActivity(i);
        finish();
    }
    private static String printArray(int a[]){
        StringBuffer sb=new StringBuffer();
        for(int i:a){
            sb.append(' ');
            sb.append(i);
        }
        return sb.toString();
    }
}
