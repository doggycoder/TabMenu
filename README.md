# TabMenu

  选项卡菜单
  
  使用方式：
  
  在xml布局文件中添加：
  
   <wuwang.git.ui.menu.TabMenu 
      android:layout_width="match_parent"
      android:layout_height="55dp"
      android:layout_alignParentBottom="true"
      android:id="@+id/TMENU"
      >


  在Activity中定义：
  
  	private TabMenu tMenu;    //TabMenu
  	private String[] menus=new String[]{"菜单一","菜单二","添加","菜单三","菜单四"};  //菜单项的文字
	private int[] srcs=new int[]{R.drawable.guangchang,R.drawable.quanzi,
				R.drawable.center,R.drawable.taolun,R.drawable.more};   //菜单项的图标

  然后再Activity中获取TabMenu并进行设置：
  
  	tMenu=(TabMenu) findViewById(R.id.TMENU);
  	//初始化菜单，第一个参数为菜单项文字，第二个参数为菜单项图标，第三个参数为设置第n个菜单项为中间菜单
	tMenu.MenuInit(menus, srcs, 3);  
	tMenu.setOnTabMenuClickListener(l);	//l为OnTabMenuClickListener
	
