package wuwang.git.ui.menu;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class WuwangAnimation {
	
	public static final int STATE_STATE=0;
	public static final int STATE_SET=1;
	
	public WuwangAnimation(){
		
	}
	
	@SuppressLint("NewApi")
	public static void Rotate(int st,View view){
		Animation anim;
		if(st==STATE_STATE)
			anim=new RotateAnimation(0f, 135f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		else{
			anim=new RotateAnimation(135f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		}
		anim.setFillAfter(true);
		anim.setRepeatCount(0);
		anim.setDuration(300);
		view.startAnimation(anim);
		
	}

}
