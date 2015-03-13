package com.qiao.testimagechange;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
/**
 * android本地批量更新图片格式
 * @author 有点凉了
 *
 */
public class MainActivity extends Activity{
	private static final String TAG="MainActivity";
	private ImageView imageView1;
	List<String> item=new ArrayList<String>();
	File[] files = null;
	File[] files2 = null;
	String path1= "";
	String path2= "";
	int count = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		String mulu1 = "01";//源目录
		String mulu2 = "test01";//目标目录
		/**
		 * 图片格式
		 */
		String jpeg=".jpeg";
		String png =".png";
		String gif = ".gif";
		String jpg=".jpg";
		String bmp = ".bmp";
		changeImage(mulu1,jpg,gif,png,jpeg,bmp,mulu2);
		
		
	}

	private void changeImage(String mulu1,final String jpg,final String gif,final String png,final String jpeg,final String bmp,final String mulu2) {
		path1= File.separator+"storage"+File.separator+"extSdCard"+File.separator+mulu1;
		File file = new File(path1);
		files =file.listFiles();
		//启动线程判断集合中是否包含相同名称不同格式的图片，如果有打印出来
/*		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < files.length; i++) {
					if (files[i].getName().indexOf(jpg)>-1||files[i].getName().indexOf(gif)>-1||files[i].getName().indexOf(png)>-1||files[i].getName().indexOf(bmp)>-1) {
						if(item.contains(files[i].getName().replace(jpg, "").replace(gif, "").replace(png, "").replace(bmp, ""))){
							Log.i(TAG,"==---相同"+files[i].getName().replace(jpg, "").replace(gif, "").replace(png, "").replace(bmp, "") );
						}
						item.add(files[i].getName().replace(jpg, "").replace(gif, "").replace(png, "").replace(bmp, ""));
					}
				}
				
			}
		}).start();*/
		
		//启动线程更新图片格式
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < files.length; i++) {
					File file1 = files[i];
					file1.getName();
					if (files[i].getName().indexOf(jpg)>-1||files[i].getName().indexOf(gif)>-1||files[i].getName().indexOf(png)>-1||files[i].getName().indexOf(jpeg)>-1||files[i].getName().indexOf(bmp)>-1) {
						item.add(path1+File.separator+files[i].getName());
						File f = new File(File.separator+"storage"+File.separator+"extSdCard"+File.separator+mulu2+File.separator, files[i].getName().replace(jpeg, png).replace(jpg, png).replace(gif, png).replace(png, png).replace(bmp, png)); 
						try {
							InputStream is = new FileInputStream(item.get(item.size()-1));
							Bitmap itemBitmap = BitmapFactory.decodeStream(is);
							FileOutputStream fos = new FileOutputStream(f);
							itemBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
							fos.flush();
						    fos.close();
						    
						    Log.i(TAG, "==---已经保存"+count++);
						    Thread.sleep(30);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}
				
			}
		}).start();
		
	}

	private void initView() {
		imageView1 = (ImageView) findViewById(R.id.imageView1);
	}

}
