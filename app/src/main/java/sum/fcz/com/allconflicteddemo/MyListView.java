package sum.fcz.com.allconflicteddemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created at  2017/3/11 00:16
 *
 * @author Feng Caizhi
 * @function
 */

public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mHeight = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mHeight);
    }
}
