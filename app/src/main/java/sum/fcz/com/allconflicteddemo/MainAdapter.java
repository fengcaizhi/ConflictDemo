package sum.fcz.com.allconflicteddemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created at  2017/3/10 22:37
 *
 * @author Feng Caizhi
 * @function
 */

public class MainAdapter extends BaseAdapter {
    private Context context;
    private List<ItemBean> list;

    public MainAdapter(Context context, List<ItemBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position).tv);
        holder.button.setText(list.get(position).bt);
        holder.button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).bt, Toast.LENGTH_SHORT).show();
            }
        });
        holder.tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).tv, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class ViewHolder {
        Button button;
        TextView tv;

        public ViewHolder(View v) {
            button = (Button) v.findViewById(R.id.button);
            tv = (TextView) v.findViewById(R.id.textview);
        }
    }

}
