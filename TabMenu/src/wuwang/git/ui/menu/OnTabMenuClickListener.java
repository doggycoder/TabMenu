package wuwang.git.ui.menu;

//
public interface OnTabMenuClickListener {
	
	//常规选项卡选中项更改
	public void onMenuItemChanged(int lastState,int nowState);
	//中央按钮被点击
	public void onCenterMenuClick(boolean isOpen);

}
