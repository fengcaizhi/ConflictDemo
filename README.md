# 事件冲突集合
- listview的item中有抢占焦点的控件时
```
如：checkbox、button、ImageButton等
```
  - 出现什么问题
  
    给listview设置点击事件无效，item中子view抢占了焦点
  - 怎么解决
  
     使用android所给的属性`android:descendantFocusability=""`来解决
 ```
  使用descendantFocusability来解决啦，API描述如下：
  android:descendantFocusability
  Defines the relationship between the ViewGroup and its descendants when looking for a View to take focus.
  Must be one of the following constant values.
  该属性是当一个为view获取焦点时，定义viewGroup和其子控件两者之间的关系。
  属性的值有三种：
          beforeDescendants：viewgroup会优先其子类控件而获取到焦点
          afterDescendants：viewgroup只有当其子类控件不需要获取焦点时才获取焦点
          blocksDescendants：viewgroup会覆盖子类控件而直接获得焦点
  
  通常我们用到的是第三种，即在Item布局的根布局加上Android:descendantFocusability=”blocksDescendants”
  ```
  
  
 - scrollview与listview或gridview嵌套使用时
   - 出现什么问题
     - 嵌套listview时listview显示数据显示不全，只显示一条item数据，由于两者滑动时间冲突导致
   - 解决办法是：
     - ①：重新测量listview的高度 ,此方法好像在AS2.3就不好使了
         ```python
         if (mListview.getAdapter() == null)
                     return;
                 
                 int totalHeight = 0;
                 for (int i = 0; i < adadpter.getCount(); i++) {
                     View mView = adadpter.getView(i, null, mListview);
                     mView.measure(
                             View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                             View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                     totalHeight += mView.getMeasuredHeight();
                 }
                 ViewGroup.LayoutParams params = mListview.getLayoutParams();
                 /**
                  * listview的高度=头部高度+底部高度+item高度和+分割线高度和
                  */
                 params.height = totalHeight + (mListview.getDividerHeight() * (adadpter.getCount() - 1))
                         + mListview.getBottom() + mListview.getTop();
                 mListview.setLayoutParams(params);
                 mListview.requestLayout();
         ```
      - ②：自定义Listview重写onMeasure()方法
        ```python
           @Override
              protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                  int mHeight = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
                  super.onMeasure(widthMeasureSpec, mHeight);
              }
        ```
  
   
   
 ------------------------------------
 # LinearLayout
   - 当LinearLayout设置`android:orientation="vertical"`时
     控件设置属性`android:gravity="center_horizontal"`或`android:gravity="center"`无效
     控件设置属性`android:layout_gravity="center_horizontal"`或`android:layout_gravity="center"`有效（水平居中）
   - 当LinearLayout设置` android:orientation="horizontal"`时
      控件设置属性`android:gravity="android:gravity="center_horizontal|center_vertical|center"`三个均无效
      控件设置属性`android:layout_gravity="center_horizontal"`无效
      控件设置属性`android:layout_gravity="center|center_vertical"`2个属性有效且效果相同
  - 即：
       - 当LinearLayout设置` android:orientation="horizontal"`时，设置水平居中无效，垂直方向有效
       - 当LinearLayout设置`android:orientation="vertical"`时,设置水平居中有效，垂直方向无效
       - 区别：
            ```
            android:gravity：设置的是控件自身上面的内容位置
            android:layout_gravity：设置控件本身相对于父控件的显示位置。
            ```