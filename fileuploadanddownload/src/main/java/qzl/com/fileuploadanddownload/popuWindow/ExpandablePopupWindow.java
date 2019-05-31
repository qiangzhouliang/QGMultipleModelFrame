package qzl.com.fileuploadanddownload.popuWindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import qzl.com.basecommon.base.BasePopuWindow;
import qzl.com.fileuploadanddownload.R;

import java.util.List;

public class ExpandablePopupWindow extends BasePopuWindow {
    private View popupView;
    private ListView popupListView;
    private OnSelectedItemListener onSelectedItemListener;

    public interface OnSelectedItemListener{
        abstract void onSelectedValue(String str);
    }

    public void setOnSelectedItemListener(OnSelectedItemListener onSelectedItemListener) {
        this.onSelectedItemListener = onSelectedItemListener;
    }

    public ExpandablePopupWindow(Activity activity, List<String> list){
        super(activity);
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.pop_material, null);
        this.setContentView(popupView);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        this.setFocusable(true);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupView.setFocusableInTouchMode(true);
        this.setBackgroundDrawable(new ColorDrawable(-00000));
        this.setAnimationStyle(R.style.popupWindowAnimation);
        popupListView = (ListView) popupView.findViewById(R.id.pop_item_list);
        popupListView.setAdapter(new PopupWindowAdapter(activity, list));
        popupListView.setOnItemClickListener(onItemClickListener);
    }
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onSelectedItemListener.onSelectedValue(parent.getAdapter().getItem(position).toString());
            dismiss();
        }
    };

    private class PopupWindowAdapter extends BaseAdapter{

        private Context context;
        private List<String> list;

        public PopupWindowAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null && !list.isEmpty())
                return list.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.i_pop_material_type, parent, false);
            }
            TextView popMaterialType = (TextView) convertView.findViewById(R.id.pop_material_type);
            popMaterialType.setText(list.get(position));
            return convertView;
        }
    }
}
