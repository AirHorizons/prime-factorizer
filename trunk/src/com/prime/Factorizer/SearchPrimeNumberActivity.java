package com.prime.Factorizer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchPrimeNumberActivity extends Activity {
	
	private static final String TAG = SearchPrimeNumberActivity.class.toString();
	
	List<Integer> primeNumber = new ArrayList<Integer> ();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_prime);
	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case RunPrimeFactorizer.ACCUMULATE_COMPLETE:
				// 스레드로 부터 소인수 리스트를 받음
				primeNumber = (List<Integer>) msg.obj;

				// 결과값 출력
				String primeNumbers = "";
				for(int r : primeNumber) {
					Log.i("primeNumber : ", Integer.toString(r));
					primeNumbers += Integer.toString(r) + ",";
				}
				TextView tvResult = (TextView) findViewById(R.id.resultValue_prime);
				tvResult.setText(primeNumbers);
				break;
			case RunPrimeFactorizer.THREAD_ERROR:
				showDialog(PrimeFactorizerActivity.DIALOG_EXCEPTION_ERROR);
				Exception e = (Exception) msg.obj;
				Log.w(TAG, e);
				break;
			}

			RelativeLayout rl = (RelativeLayout)findViewById(R.id.RL_prime);
			rl.setVisibility(rl.VISIBLE);

			ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1_prime);
			pb.setVisibility(pb.GONE);
		}
	};
	
	 public void mOnClick_prime_clear(View v) {
	    	TextView tvInput = (TextView) findViewById(R.id.inputValue_prime);
	    	tvInput.setText("");
	    	
	    	primeNumber.clear();
	    	
	    	TextView tvResult = (TextView) findViewById(R.id.resultValue_prime);
	    	tvResult.setText("");
	    }
	    
	    public void mOnClick_prime(View v) {
	    	TextView tv = (TextView) findViewById(R.id.inputValue_prime);
	    	
	    	if(0 == tv.getText().length()) {
	    		Toast.makeText(this, "숫자를 입력해주세요", 1).show();
	    	} else {
	        	//0. 입력값 받음 & 초기화
	    		int inputNumber = Integer.parseInt(tv.getText().toString());
	    		
	    		primeNumber.clear();
	    		
	    		RelativeLayout rl = (RelativeLayout)findViewById(R.id.RL_prime);
	    		rl.setVisibility(rl.GONE);

	        	ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1_prime);
	        	pb.setVisibility(pb.VISIBLE);
	        	
	        	//1. 계산 스레드 시작
	        	RunPrimeNumber rpn = new RunPrimeNumber(mHandler, inputNumber);
	    		new Thread(rpn).start();
	    	}
		}

}
