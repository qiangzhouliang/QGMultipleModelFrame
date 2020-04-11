package qzl.com.basecommon.ui.java;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import qzl.com.basecommon.R;
import qzl.com.tools.utils.StringHelper;


/**
 * Created by 肖龙 on 2015/6/4.
 */
public class SearchLayout extends LinearLayout {

    EditText searchEdit = null;
    ImageView deleteIcon = null;
    TextView serachBtn = null;
    SearchListener searchListener = null;
    OnTextChangeListener onTextChangeListener;

    public SearchLayout(Context context) {
        super(context);
    }

    public SearchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        LinearLayout childLayout = (LinearLayout) this.getChildAt(0);
        searchEdit = (EditText) childLayout.getChildAt(1);
        deleteIcon = (ImageView) childLayout.getChildAt(2);
        searchEdit.addTextChangedListener(textWatcher);
        deleteIcon.setOnClickListener(clearTextListener);
        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchBtnListener.onClick(v);
                }
                return false;
            }
        });
        initSerachBtn();
    }

    private void initSerachBtn() {
        serachBtn = new TextView(this.getContext());
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.leftMargin = 15;
        serachBtn.setLayoutParams(lp);
        serachBtn.setGravity(Gravity.CENTER);
        serachBtn.setText("搜索");
        serachBtn.setTextColor(getResources().getColor(R.color.white));
        serachBtn.setBackgroundResource(R.drawable.title_bg);
        serachBtn.setPadding(25, 0, 25, 0);
        serachBtn.setOnClickListener(searchBtnListener);
    }

    private OnClickListener clearTextListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            searchEdit.setText("");
            if (null != searchListener) {
                searchListener.clearText();
            }
        }
    };
    private OnClickListener searchBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //关闭软键盘
            ((InputMethodManager) ((Activity) SearchLayout.this.getContext()).getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(searchEdit.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if (searchListener != null) {
                String text = searchEdit.getText().toString();
                searchListener.search(text);
            }
        }
    };
    private final int mNum2 = 2;
    private TextWatcher textWatcher = new TextWatcher() {

        String text;

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            text = searchEdit.getText().toString().trim();
            if (!StringHelper.isEmptyString(text)) {
                deleteIcon.setVisibility(View.VISIBLE);
                if (SearchLayout.this.getChildCount() != mNum2) {
                    SearchLayout.this.addView(serachBtn);
                }
            } else {
                deleteIcon.setVisibility(View.INVISIBLE);
                SearchLayout.this.removeView(serachBtn);
                if (null != searchListener) {
                    searchListener.clearText();
                }
            }
            //添加的接口
            if (onTextChangeListener != null) {
                onTextChangeListener.onTextChange(s);
            }
        }
    };

    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    public interface SearchListener {
        void search(String editContent);

        void clearText();
    }

    public void setSearchOnTextChange(OnTextChangeListener onTextChangeListener) {
        this.onTextChangeListener = onTextChangeListener;
    }

    public interface OnTextChangeListener {
        void onTextChange(CharSequence s);
    }

    //设置搜索框的文本
    public void setSearchHint(String text) {
        if (searchEdit != null) {
            searchEdit.setHint(text);
        }
    }

    public void setSearchText(String text) {
        if (searchEdit != null) {
            searchEdit.setText(text);
        }
    }

    /**
     * 获取搜索内容
     * @return
     */
    public String getSearchText(){
        if(null != searchEdit){
            return searchEdit.getText().toString().trim();
        }
        return "";
    }

    /**
     * 清空搜索栏
     */
    public void clearSearch(){
        if(null != searchEdit){
            searchEdit.setText("");
        }
    }

    /**
     * 搜索框的返回事件
     * @return
     */
    public boolean onBackPressed(){
        if(!"".equals(getSearchText())){
            clearSearch();
            return true;
        }
        return false;
    }

}
