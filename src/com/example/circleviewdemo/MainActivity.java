package com.example.circleviewdemo;

import com.lling.circleviewdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CircleView circleView1 = (CircleView) findViewById(R.id.circleview1);
		circleView1.setOnClickListener(this);
		CircleView circleView2 = new CircleView(this);
		circleView2.setText("圆二");
		circleView2.setTextColor(0xffffffff);
		circleView2.setOnClickListener(this);
		LayoutParams params = new LayoutParams(160, 160);
		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		layout.addView(circleView2, params);
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), ((TextView)v).getText(), Toast.LENGTH_SHORT).show();
	}
	
}
