package my.qq.com.czw3dmgame.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import my.qq.com.czw3dmgame.R;

/**
 * Created by czw on 2016/7/8  09:51.
 */
public class PullToRefreshAdapter extends BaseAdapter{
    private Context context;
    private List<HashMap<String, Object>> alldata;

    public PullToRefreshAdapter(Context context, List<HashMap<String, Object>> alldata) {
        this.context = context;
        this.alldata = alldata;
    }

    @Override
    public int getCount() {

        return alldata.size();
    }

    @Override
    public Object getItem(int i) {
        return alldata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.pulltoitem, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.pull_item_iv);
            holder.tv_title = (TextView) convertView.findViewById(R.id.pull_item_tvtitle);
            holder.tv_date = (TextView) convertView.findViewById(R.id.pull_item_tvdate);
            holder.tv_down = (TextView) convertView.findViewById(R.id.pull_item_tvdown);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(alldata.get(position).get("shorttitle").toString());

        //格式化时间,必须先转成long型数据
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String date=alldata.get(position).get("senddate").toString();
        Long time=Long.valueOf(date)+System.currentTimeMillis();//String转long
        //填充格式化之后的时间
        holder.tv_date.setText(sdf.format(new Date(time)));

        holder.tv_down.setText(alldata.get(position).get("weight").toString());
        //填充图片
        String ivpath=alldata.get(position).get("imgpath").toString();
        if (ivpath!=null) {
            Bitmap bitmap = BitmapFactory.decodeFile(ivpath);
            holder.iv.setImageBitmap(bitmap);
        }

        return convertView;
    }
    class ViewHolder {
        ImageView iv;
        TextView tv_title;
        TextView tv_date;
        TextView tv_down;
    }
}
