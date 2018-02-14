package com.app.community.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

import com.app.community.R;

public class MainActivity extends Activity {

	RatingBar R1;
	Button RatingBarCount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		R1 = (RatingBar)findViewById(R.id.ratingBar1);
		RatingBarCount = (Button)findViewById(R.id.button1);
		
		RatingBarCount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(MainActivity.this, String.valueOf(R1.getRating()), Toast.LENGTH_SHORT).show();
				
			}
		});
	}
}
