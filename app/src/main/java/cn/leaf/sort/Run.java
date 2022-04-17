package cn.leaf.sort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run extends AppCompatActivity {
    private static final int BubbleOK=0,ChooseOK=1,InsertOK=2,MergeOK=3,QuickOK=4;
    private static final int BubbleN=5,ChooseN=6,InsertN=7,MergeN=8,QuickN=9;
    private TextView bubble,choose,merge,insert,quick;
    @SuppressLint("HandlerLeak")
    private Handler h=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle b=msg.getData();
            long time=b.getLong("time", Long.MAX_VALUE);
            switch (msg.what){
                case BubbleOK:
                    bubble.setText("最慢的冒泡 "+time+" ms");
                    break;
                case ChooseOK:
                    choose.setText("选择 "+time+" ms");
                    break;
                case MergeOK:
                    merge.setText("归并 "+time+" ms");
                    break;
                case InsertOK:
                    insert.setText("插入 "+time+" ms");
                    break;
                case QuickOK:
                    quick.setText("快速 "+time+" ms");
                    break;
                case BubbleN:
                    bubble.setText("冒泡执行中");
                    break;
                case ChooseN:
                    choose.setText("选择执行中");
                    break;
                case MergeN:
                    merge.setText("归并执行中");
                    break;
                case InsertN:
                    insert.setText("插入执行中");
                    break;
                case QuickN:
                    quick.setText("快排执行中");
                    break;
            }
        }
    };
    private static int size=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        size=getIntent().getIntExtra("size",0);
        getSupportActionBar().setTitle(size+"规模 执行排序");
        bubble=findViewById(R.id.bubble_result);
        choose=findViewById(R.id.choose_result);
        merge=findViewById(R.id.merge_result);
        insert=findViewById(R.id.insert_result);
        quick=findViewById(R.id.quick_result);
        if(size<=0){
            finish();
            return;
        }
        Bundle b=getIntent().getBundleExtra("bundle");
        HashSet<Byte> hs=(HashSet<Byte>) b.get("sort");
        ExecutorService pool= Executors.newFixedThreadPool(5);
        for(byte number:hs){
            pool.execute(new sort(number));
        }
//        pool.execute(new sort(0));
//        pool.execute(new sort(1));
//        pool.execute(new sort(2));
//        pool.execute(new sort(3));
//        pool.execute(new sort(4));
        pool.shutdown();
    }
    class sort extends Thread{
        int type;
        public sort(int type){
            this.type=type;
        }
        @Override
        public void run() {
            super.run();
            Message m=new Message();
            m.what=type+5;
            h.sendMessage(m);
            int a[]=new int[size];
            long time=0,current;
            switch (type){
                case 0:
                    for(int i=0;i<20;i++){
                        current=System.currentTimeMillis();
                        Sort.randomArray(a);
                        Sort.bubbleSort(a);
                        time+=System.currentTimeMillis()-current;
                    }
                    break;
                case 1:
                    for(int i=0;i<20;i++){
                        current=System.currentTimeMillis();
                        Sort.randomArray(a);
                        Sort.chooseSort(a);
                        time+=System.currentTimeMillis()-current;
                    }
                    break;
                case 2:
                    for(int i=0;i<20;i++){
                        current=System.currentTimeMillis();
                        Sort.randomArray(a);
                        Sort.insertSort(a);
                        time+=System.currentTimeMillis()-current;
                    }
                    break;
                case 3:
                    for(int i=0;i<20;i++){
                        current=System.currentTimeMillis();
                        Sort.randomArray(a);
                        Sort.mergeSort(a);
                        time+=System.currentTimeMillis()-current;
                    }
                    break;
                case 4:
                    for(int i=0;i<20;i++){
                        current=System.currentTimeMillis();
                        Sort.randomArray(a);
                        Sort.quickSort(a);
                        time+=System.currentTimeMillis()-current;
                    }
                    break;
            }
            Bundle b=new Bundle();
            b.putLong("time",time/20);
            m=new Message();
            m.what=type;
            m.setData(b);
            h.sendMessage(m);
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
