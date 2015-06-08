package wuwang.git.bottommenu;

import wuwang.git.ui.menu.TabMenu;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	private TabMenu tMenu;
	private String[] menus=new String[]{"广场","圈子","添加","夜话","更多"};
	private int[] srcs=new int[]{R.drawable.guangchang,R.drawable.quanzi,R.drawable.center,R.drawable.taolun,R.drawable.more};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tMenu=(TabMenu) findViewById(R.id.TMENU);
		tMenu.MenuInit(menus, srcs, 3);
		tMenu.OpenClick();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
