package cn.leaf.sort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private TextView bubble,select,merge,insert,quick;
    private EditText size;
    private Button enter;
    private CheckBox sorts[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bubble=findViewById(R.id.bubble_test);
        select=findViewById(R.id.select_test);
        merge=findViewById(R.id.merge_test);
        insert=findViewById(R.id.insert_test);
        quick=findViewById(R.id.quick_test);
        size=findViewById(R.id.size);
        enter=findViewById(R.id.enter);
        Intent i=getIntent();
        bubble.setText(i.getStringExtra("bubble"));
        select.setText(i.getStringExtra("choose"));
        merge.setText(i.getStringExtra("merge"));
        insert.setText(i.getStringExtra("insert"));
        quick.setText(i.getStringExtra("quick"));
        sorts=new CheckBox[5];
        sorts[0]=findViewById(R.id.check_bubble);
        sorts[1]=findViewById(R.id.check_selection);
        sorts[2]=findViewById(R.id.check_insertion);
        sorts[3]=findViewById(R.id.check_merge);
        sorts[4]=findViewById(R.id.check_quick);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=size.getText().toString();
                Integer input_size=2;
                try {
                    input_size=Integer.valueOf(number);
                    if(input_size<2){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"乱打是吧？卡屎你(σ｀д′)σ",Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.exit(0);
                }
                HashSet<Byte> sort_chosen=new HashSet<>();
                for(byte i=0;i<5;i++){
                    if(sorts[i].isChecked()){
                        sort_chosen.add(i);
                    }
                }
                Intent start=new Intent(MainActivity.this,Run.class);
                start.putExtra("size",input_size.intValue());
                Bundle b=new Bundle();
                b.putSerializable("sort",sort_chosen);
                start.putExtra("bundle",b);
                startActivity(start);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.exit(0);
    }
}
