package tw.com.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userid,userpassword;
    private TextView result;
    private Button sendbtn,cancelbtn;
    private CheckBox remme;


    private Button btn1,btn2,btn3;

    private LinearLayout layout1;
    private TextView touchme;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        gp();

    }

    private void gp() {
        int i = 1;
    }


    private void findViews(){  //自訂的私有化方法

        userid = findViewById(R.id.userid);
        userpassword = findViewById(R.id.userpwd);
        result = findViewById(R.id.result);
        sendbtn = findViewById(R.id.sendbtn);
        cancelbtn = findViewById(R.id.cancelbtn);
        remme = findViewById(R.id.remme);  //核取方塊 (打勾)



        remme.setChecked(false);

        remme.setOnClickListener(v -> {

            if (remme.isChecked()){ //已選
                result.setText("已勾選");
            }else{
                result.setText("");
            }

        });

        sendbtn.setOnClickListener(sendListener);  //註冊sendbtn按鈕 做事件的監聽

        cancelbtn.setOnClickListener(v -> {

            userid.setText("");
            userpassword.setText("");

        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(btnLis);
        btn2.setOnClickListener(btnLis);
        btn3.setOnClickListener(btnLis);

        touchme = findViewById(R.id.touchme);
        layout1 = findViewById(R.id.layout1);

        touchme.setOnTouchListener(new myTouch());   // 使用物件方式處理

        layout1.setOnTouchListener(new myTouch());

        userpassword.setOnFocusChangeListener(new myFocus());


    }

    private View.OnClickListener sendListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String uid = userid.getText().toString();
            String upwd = userpassword.getText().toString();
            result.setText(uid + "-" + upwd);

        }
    }; //匿名類別


    // lambda方式
    private View.OnClickListener btnLis = v -> {

        switch (v.getId()){
            case R.id.btn1:
                result.setText("第一個按鈕被點擊了");
                break;
            case R.id.btn2:
                result.setText("第二個按鈕被點擊了");
                break;
            case R.id.btn3:
                result.setText("第三個按鈕被點擊了");
                break;
        }

    };

    //物件方式建立
    private class myTouch implements View.OnTouchListener{

        // down按 , move移 , up放
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();

            if (action == MotionEvent.ACTION_DOWN){
                result.setText("Down");
            }else if (action == MotionEvent.ACTION_MOVE){
                //result.setText("Move");

                int pointCount = event.getPointerCount();
                StringBuffer sb = new StringBuffer();
                for (int i = 0 ; i < pointCount ; i++){
                    sb.append("點:"+event.getPointerId(i) + ":" + (int)event.getX(i) + "-" +(int)event.getY(i));
                    if (i < pointCount-1)
                        sb.append("\n");
                }
                result.setText(sb.toString());



            }else if (action == MotionEvent.ACTION_UP){
                result.setText("up");
            }

            return true;
        }
    }

    private class myFocus implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            result.setText(hasFocus ? "關注":"已離開");
        }
    }

}