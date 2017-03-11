package sum.fcz.com.allconflicteddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    MyListView mListview;
    MainAdapter adapter;
    List<ItemBean> list = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListview = (MyListView) findViewById(R.id.listview);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ItemBean b = new ItemBean();
            b.bt = "button" + i;
            b.tv = "textview" + i;
            list.add(b);
        }
        adapter = new MainAdapter(this, list);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(this);



    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, list.get(position)
                .tv + "main", Toast.LENGTH_SHORT).show();
    }

    public void measurHeight(Adapter adadpter, ListView mListview) {
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
    }
}
