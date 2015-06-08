package wuwang.git.ui.menu;

import wuwang.git.tabmenu.R;
import wuwang.tools.DisplayUtil;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageTextButton extends FrameLayout {

	public static final int V_STYLE_LEFT=0;
	public static final int V_STYLE_RIGHT=1;
	public static final int V_STYLE_TOP=2;
	public static final int V_STYLE_BOTTOM=3;
	
	public static final int V_ALIGN_CENTER=0;
	public static final int V_ALIGN_LEFT=1;
	public static final int V_ALIGN_RIGHT=2;
	
	private Context context;
	
	private boolean initFlag=true;
	
	private LinearLayout UI_licontainer;
	private ImageView UI_image;
	private TextView UI_text;
	
	private String V_Text="";				//��ʾ������
	private int V_Style=V_STYLE_BOTTOM;		//�������ͼƬ��λ��
	private int V_Align=V_ALIGN_CENTER;		//���ֶ��뷽ʽ
	private int V_TextSize;					//�����С
	private int V_Src;						//ͼƬ��ַ
	private int V_ImageWidth;				//ͼƬ�������
	private int V_ImageHeight;				//ͼƬ�߶�����
	private int V_TextColor;				//������ɫ
	private int V_Tint;						//ͼƬ�˾�ɫ
	private int V_TintSelected;				//��ѡ��ʱ��ͼƬ���˾�ɫ
	private boolean V_IsSelected;			//��ʾ�Ƿ�ѡ��
	private int V_BgColorSelected;			//��ѡ��ʱ�ı���ɫ
	private int V_BgColor;					//����ɫ
	
	
	public ImageTextButton(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
		initUI();
	}
	
	public ImageTextButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		// TODO Auto-generated constructor stub
		initUI();
		getAttrs(context, attrs);
	}

	//��ȡ����
	private void getAttrs(Context context,AttributeSet attrs){
		TypedArray tarr=getContext().obtainStyledAttributes(attrs, R.styleable.ImageTextButton);
		
		V_Text=tarr.getString(R.styleable.ImageTextButton_Text);
		V_Style=tarr.getInt(R.styleable.ImageTextButton_Style,V_STYLE_BOTTOM);
		V_Align=tarr.getInt(R.styleable.ImageTextButton_Align, V_ALIGN_CENTER);
		V_TextSize=tarr.getDimensionPixelSize(R.styleable.ImageTextButton_TextSize, DisplayUtil.sp2px(context, 10));
		V_Src=tarr.getResourceId(R.styleable.ImageTextButton_Src,0);
		V_ImageWidth=tarr.getDimensionPixelSize(R.styleable.ImageTextButton_ImageWidth,  DisplayUtil.dip2px(context,25));
		V_ImageHeight=tarr.getDimensionPixelSize(R.styleable.ImageTextButton_ImageHeight, DisplayUtil.dip2px(context,25));
		V_TextColor=tarr.getColor(R.styleable.ImageTextButton_TextColor, 0xff595959);
		V_Tint=tarr.getColor(R.styleable.ImageTextButton_Tint, 0xff595959);
		V_TintSelected=tarr.getColor(R.styleable.ImageTextButton_TintSelected, 0xFFFF8000);
		V_IsSelected=tarr.getBoolean(R.styleable.ImageTextButton_IsSelected, false);
		V_BgColor=tarr.getColor(R.styleable.ImageTextButton_BgColor, 0xffffffff);
		V_BgColorSelected=tarr.getColor(R.styleable.ImageTextButton_BgColorSelected, V_BgColor);
		
		Log.d("wuwang", V_ImageWidth+"/"+DisplayUtil.dip2px(context, 30));
		tarr.recycle();
	}
	
	//��ʼ��UI
	private void initUI(){
		UI_licontainer=new LinearLayout(context);
		UI_text=new TextView(context);
		UI_image=new ImageView(context);
	}
	
	//��ʼ������
	@SuppressLint({ "RtlHardcoded", "InlinedApi" })
	private void initLayout(){
		UI_licontainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,Gravity.CENTER));
		
		UI_text.setText(V_Text);
		UI_text.setTextSize(TypedValue.COMPLEX_UNIT_PX,V_TextSize);
		
		UI_image.setImageResource(V_Src);
		UI_licontainer.setGravity(Gravity.CENTER);
		switch(V_Style){
		case V_STYLE_LEFT:
			UI_licontainer.setOrientation(LinearLayout.HORIZONTAL);
			UI_licontainer.addView(UI_text);
			UI_licontainer.addView(UI_image);
			break;
		case V_STYLE_RIGHT:
			UI_licontainer.setOrientation(LinearLayout.HORIZONTAL);
			UI_licontainer.addView(UI_image);
			UI_licontainer.addView(UI_text);
			break;
		case V_STYLE_TOP:
			UI_licontainer.setOrientation(LinearLayout.VERTICAL);
			UI_licontainer.addView(UI_text);
			UI_licontainer.addView(UI_image);
			break;
		default:
			UI_licontainer.setOrientation(LinearLayout.VERTICAL);
			UI_licontainer.addView(UI_image);
			UI_licontainer.addView(UI_text);
			break;
		}
		
		switch(V_Align){
		case V_ALIGN_LEFT:
			UI_text.setGravity(Gravity.LEFT);
			break;
		case V_ALIGN_RIGHT:
			UI_text.setGravity(Gravity.RIGHT);
			break;
		default:
			UI_text.setGravity(Gravity.CENTER);
			break;
		}
		this.addView(UI_licontainer);
		
		if(V_Text==null||V_Text.length()==0){
			UI_text.setVisibility(View.GONE);
		}
		
		UI_image.getLayoutParams().height=V_ImageHeight;
		UI_image.getLayoutParams().width=V_ImageWidth;
	}
	

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		if(initFlag){
			initFlag=false;
			initLayout();
			Selected(V_IsSelected);
		}
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	public void makeCenter(int width,int height,int tint){
		UI_text.setVisibility(View.GONE);
		V_ImageWidth=width;
		V_ImageHeight=height;
		V_Tint=tint;
	}
	
	public void makeCenter(int width,int height){
		UI_text.setVisibility(View.GONE);
		V_ImageWidth=width;
		V_ImageHeight=height;
	}
	
	
	public void SetMenuText(String text){
		this.V_Text=text;
		UI_text.setText(text);
	}
	
	public void SetMenuImage(int src){
		this.V_Src=src;
		UI_image.setImageResource(src);
	}
	
	//ѡ�л�ȡ��ѡ��
	@SuppressLint("NewApi")
	public void Selected(boolean flag){
		V_IsSelected=flag;
		if(V_IsSelected){
			UI_text.setTextColor(V_TintSelected);
			UI_image.setColorFilter(V_TintSelected);
			UI_licontainer.setBackgroundColor(V_BgColorSelected);
		}else{
			UI_text.setTextColor(V_TextColor);
			UI_image.setColorFilter(V_Tint);
			UI_licontainer.setBackgroundColor(V_BgColor);
		}
	}
	
	//��ȡͼƬ
	public ImageView getImage(){
		return UI_image;
	}
	
}
