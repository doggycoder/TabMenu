package wuwang.git.ui.menu;

import java.util.ArrayList;

import wuwang.git.tabmenu.R;
import wuwang.tools.DisplayUtil;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class TabMenu extends LinearLayout{

	public static final int V_STYLE_LEFT=0;
	public static final int V_STYLE_RIGHT=1;
	public static final int V_STYLE_TOP=2;
	public static final int V_STYLE_BOTTOM=3;
	
	public static final int V_ALIGN_CENTER=0;
	public static final int V_ALIGN_LEFT=1;
	public static final int V_ALIGN_RIGHT=2;
	
	private AttributeSet attrs;
	
	private int menuNum;
	
	private ArrayList<ImageTextButton> MenuItem;
	private ImageTextButton cMenu;
	
	private boolean isCMenuOpen=false;
	
	private int V_Style=V_STYLE_BOTTOM;
	private int V_Align=V_ALIGN_CENTER;
	private int V_TextSize;
	private int V_ImageWidth;
	private int V_ImageHeight;
	private int V_TextColor;
	private int V_Tint;
	private int V_TintSelected;
	private boolean V_IsSelected;
	private int V_BgColorSelected;
	private int V_BgColor;
	private boolean V_HasCenterMenu;
	
	private int V_CenterImageWidth;
	private int V_CenterImageHeight;
	private int V_CenterTint;
	private int V_SelectWitch=1;
	
	private OnTabMenuClickListener tListener;
	
	public TabMenu(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initUI();
		InitAttrs(context);
	}
	
	public TabMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.attrs=attrs;
		initUI();
		getAttrs(context, attrs);
	}
	
	//xml创建UI时，初始化参数
	private void getAttrs(Context context,AttributeSet attrs){
		TypedArray tarr=getContext().obtainStyledAttributes(attrs, R.styleable.ImageTextButton);
		
		V_Style=tarr.getInt(R.styleable.ImageTextButton_Style,V_STYLE_BOTTOM);
		V_Align=tarr.getInt(R.styleable.ImageTextButton_Align, V_ALIGN_CENTER);
		V_TextSize=tarr.getDimensionPixelSize(R.styleable.ImageTextButton_TextSize, DisplayUtil.sp2px(context, 10));
		V_ImageWidth=tarr.getDimensionPixelSize(R.styleable.ImageTextButton_ImageWidth,  DisplayUtil.dip2px(context,25));
		V_ImageHeight=tarr.getDimensionPixelSize(R.styleable.ImageTextButton_ImageHeight, DisplayUtil.dip2px(context,25));
		V_TextColor=tarr.getColor(R.styleable.ImageTextButton_TextColor, 0xff595959);
		V_Tint=tarr.getColor(R.styleable.ImageTextButton_Tint, 0xff595959);
		V_TintSelected=tarr.getColor(R.styleable.ImageTextButton_TintSelected, 0xFFFF8000);
		V_BgColor=tarr.getColor(R.styleable.ImageTextButton_BgColor, 0xffffffff);
		V_BgColorSelected=tarr.getColor(R.styleable.ImageTextButton_BgColorSelected, V_BgColor);
		V_CenterImageWidth=tarr.getDimensionPixelSize(R.styleable.TabMenu_Center_ImageWidth, DisplayUtil.dip2px(context, 55));
		V_CenterImageHeight=tarr.getDimensionPixelSize(R.styleable.TabMenu_Center_ImageHeight, DisplayUtil.dip2px(context, 55));
		V_CenterTint=tarr.getColor(R.styleable.TabMenu_Center_Tint, 0xFFFF8000);
		
		V_SelectWitch=tarr.getInt(R.styleable.TabMenu_SelectWitch,1);
		
		
		Log.d("wuwang", V_ImageWidth+"/"+DisplayUtil.dip2px(context, 30));
		tarr.recycle();
	}
	
	//java创建UI时，初始化参数
	private void InitAttrs(Context context){
		V_Style=V_STYLE_BOTTOM;
		V_Align=V_ALIGN_CENTER;
		V_TextSize=DisplayUtil.sp2px(context, 10);
		V_ImageWidth= DisplayUtil.dip2px(context,25);
		V_ImageHeight=DisplayUtil.dip2px(context,25);
		V_TextColor=0xff595959;
		V_Tint=0xff595959;
		V_TintSelected= 0xFFFF8000;
		V_BgColor= 0xffffffff;
		V_BgColorSelected= V_BgColor;
	}
	
	//初始化UI参数
	private void initUI(){
		this.setOrientation(LinearLayout.HORIZONTAL);
		MenuItem=new ArrayList<>();
	}
	
	//初始化菜单，传入Menuitem的文字数组和图片数组，图片位置对应文字位置
	public void MenuInit(String[] Menus,int[] Srcs){
		menuNum=Menus.length>Srcs.length?Menus.length:Srcs.length;
		MenuItem.clear();
		this.removeAllViews();
		for(int i=0;i<menuNum;i++){
			ImageTextButton itb=new ImageTextButton(getContext(),attrs);
			itb.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
			if(Srcs.length>i){
				itb.SetMenuImage(Srcs[i]);
			}
			if(Menus.length>i){
				itb.SetMenuText(Menus[i]);
			}
			MenuItem.add(itb);
			this.addView(itb);
		}
		SelectMenu(V_SelectWitch);
		OpenClick();
	}
	
	////初始化菜单，传入Menuitem的文字数组和图片数组，并设置中心菜单，第三个参数为中心菜单位置
	public void MenuInit(String[] Menus,int[] Srcs,int cPos){
		MenuInit(Menus, Srcs);
		SetCenterMenu(cPos);
	}
	
	//设置中心菜单，参数为中心菜单位置，从1开始。大于子菜单总数时无效
	public void SetCenterMenu(int position){
		if(position>0&&position<=menuNum){
			cMenu=MenuItem.get(position-1);
			cMenu.makeCenter(V_CenterImageWidth, V_CenterImageHeight,V_CenterTint);
			MenuItem.remove(position-1);
		}
	}
	
	//选择菜单项，参数为选中位置，大于1不大于总菜单项减中间菜单
	public void SelectMenu(int position){
		if(position>0&&position<=MenuItem.size()){
			ReSetSelectWhich();
			SetSelectWhich(position);
		}
	}
	
	//取消被选中的菜单项的状态
	private void ReSetSelectWhich(){
		MenuItem.get(V_SelectWitch-1).Selected(false);
	}
	
	//选择某个菜单项
	private void SetSelectWhich(int position){
		V_SelectWitch=position;
		MenuItem.get(V_SelectWitch-1).Selected(true);
	}
	
	public void OpenClick(){
		for(int i=0;i<MenuItem.size();i++){
			final int ti=i;
			ImageTextButton itb=MenuItem.get(i);
			itb.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(!isCMenuOpen){
						int lastState=V_SelectWitch;
						SelectMenu(ti+1);
						if(tListener!=null){
							tListener.onMenuItemChanged(lastState, ti+1);
						}
					}
				}
			});
		}
		if(cMenu!=null){
			cMenu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					cMenuAnimation();
					if(tListener!=null){
						tListener.onCenterMenuClick(isCMenuOpen);
					}
				}
			});
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	private void cMenuAnimation(){
		cMenu.clearAnimation();
		if(isCMenuOpen){
			WuwangAnimation.Rotate(WuwangAnimation.STATE_SET,cMenu.getImage());
		}else{
			WuwangAnimation.Rotate(WuwangAnimation.STATE_STATE,cMenu.getImage());
		}
		isCMenuOpen=!isCMenuOpen;
	}
	
	public void setOnTabMenuClickListener(OnTabMenuClickListener tListener){
		this.tListener=tListener;
	}
	
	
}
