package demo.sweetalert.pedant.cn.newdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

/** 
* @ClassName: CollectNewDialog 
* @Description:弹出屏幕下面的修改文件夹名称和删除文件夹的对话框
* @author shaobing  
* @date 2016年3月10日 上午11:50:05 
* @version V1.0 
*/
public class AnswerDialog extends Dialog
{

	private Context mContext;
	private OnShareDialogListener mListener;
	private String [] mNames = {"微信好友","QQ好友","微信朋友圈","QQ空间"};
	private ScaleAnimation mScaleAnimation;

	public AnswerDialog(Context mContext, OnShareDialogListener mListener)
	{
		super(mContext, R.style.CustomDialog);
		// TODO Auto-generated constructor stub
		this.mContext= mContext;
		this.mListener =mListener;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_answer);
		setOwnerActivity((Activity)mContext);
		/**************************************************************************************/
		this.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失 true消失  false不消失
		//设置对话框的位置
		Window dialogWindow = this.getWindow();
//		dialogWindow.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height= WindowManager.LayoutParams.MATCH_PARENT;
		dialogWindow.setAttributes(lp);
		dialogWindow.setGravity(Gravity.CENTER );

		initData();
		initEvent();
	}
	public void initData()
	{
		final LinearLayout layout = (LinearLayout) findViewById(R.id.llyt_answer);
		layout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				float x = layout.getX();
				float y = layout.getY();
				// 获取屏幕密度（方法2）
				DisplayMetrics dm = new DisplayMetrics();
				dm = mContext.getResources().getDisplayMetrics();
				float pivotX = dm.widthPixels;
				float pivotY = dm.heightPixels;
				Log.i("aaaa","x  :"+x+"  "+y+"  "+layout.getWidth()+"  "+layout.getHeight()+"  "+pivotX+"  "+pivotY);
				/** 设置缩放动画 */
				final ScaleAnimation animation =new ScaleAnimation(1.0f, 0.1f, 1.0f, 0.1f,
						Animation.ABSOLUTE,
						(float) pivotX-x,
						Animation.ABSOLUTE,
						-(y));
				animation.setDuration(2000);//设置动画持续时间
				animation.setFillEnabled(true);
				animation.setFillAfter(true);
				layout.startAnimation(animation);
			}
		});
	}
	private void initEvent()
	{

	}
	public interface OnShareDialogListener
	{
		void onDialogMessage(int position);
	}
}