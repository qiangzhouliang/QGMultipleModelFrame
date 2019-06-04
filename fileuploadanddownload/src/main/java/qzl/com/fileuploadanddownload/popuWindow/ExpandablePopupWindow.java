package qzl.com.fileuploadanddownload.popuWindow;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import qzl.com.basecommon.base.BasCommonPopuWindow;
import qzl.com.basecommon.base.BasePopuWindow;
import qzl.com.fileuploadanddownload.R;
import qzl.com.tools.utils.ScreenUtil;

import java.util.List;

public class ExpandablePopupWindow extends BasCommonPopuWindow {
    private ListView popupListView;
    private Activity mActivity;
    private List<String> list;
    private OnSelectedItemListener onSelectedItemListener;
    private View mView;
    public ExpandablePopupWindow(Activity activity,View view, List<String> list,OnSelectedItemListener onSelectedItemListener){
        super(activity);
        this.mActivity = activity;
        this.list = list;
        this.mView = view;
        this.onSelectedItemListener = onSelectedItemListener;
        setPopWindowContent(R.layout.pop_material);
    }
    @Override
    protected void showPopuwindow(@NotNull BasePopuWindow popuWindow) {
        popuWindow.showAtLocation(mView, Gravity.BOTTOM, 0, ScreenUtil.getVirtualBarHeight(mActivity));
    }

    @Override
    protected void initComplate(@NotNull PopupWindow popupWindow, @Nullable View pupView) {
        popupListView = pupView.findViewById(R.id.pop_item_list);
        popupListView.setAdapter(new PopupWindowAdapter(mActivity, list));
        popupListView.setOnItemClickListener(onItemClickListener);
    }

    public interface OnSelectedItemListener{
        abstract void onSelectedValue(String str);
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
