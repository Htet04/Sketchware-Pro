package dev.aldi.sayuti.editor.view.item;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.besome.sketch.beans.ViewBean;

import java.util.ArrayList;
import java.util.List;

import a.a.a.sy;
import a.a.a.wB;

public class ItemRecyclerView extends RecyclerView implements sy {

    private final Paint paint;
    private final Rect rect;
    private final float paddingFactor;
    private boolean hasSelection;
    private boolean hasFixed;
    private ViewBean viewBean;

    public ItemRecyclerView(Context context) {
        super(context);
        setMinimumWidth((int) wB.a(context, 32.0F));
        setMinimumHeight((int) wB.a(context, 32.0F));
        paddingFactor = wB.a(context, 1.0f);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(wB.a(getContext(), 2.0f));
        rect = new Rect();
        setDrawingCacheEnabled(true);
    }

    @Override
    public ViewBean getBean() {
        return viewBean;
    }

    @Override
    public void setBean(ViewBean viewBean) {
        this.viewBean = viewBean;
    }

    @Override
    public boolean getFixed() {
        return hasFixed;
    }

    public void setFixed(boolean z) {
        hasFixed = z;
    }

    public boolean getSelection() {
        return hasSelection;
    }

    @Override
    public void setSelection(boolean z) {
        hasSelection = z;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (hasSelection) {
            paint.setColor(0x9599d5d0);
            rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
            canvas.drawRect(rect, paint);
        } else {
            paint.setColor(0x60000000);
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float f2 = (float) measuredWidth;
            canvas.drawLine(0.0f, 0.0f, f2, 0.0f, paint);
            float f3 = (float) measuredHeight;
            canvas.drawLine(0.0f, 0.0f, 0.0f, f3, paint);
            canvas.drawLine(f2, 0.0f, f2, f3, paint);
            canvas.drawLine(0.0f, f3, f2, f3, paint);
        }
        super.onDraw(canvas);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding((int) (left * paddingFactor), (int) (top * paddingFactor), (int) (right * paddingFactor), (int) (bottom * paddingFactor));
    }

    private static class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {

        private List<String> dataList;

        private int layout;

        public SimpleAdapter(int layout) {
            dataList = new ArrayList<>();
            this.layout = layout;
        }

        public void setItemList(List<String> newList) {
            dataList = newList;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder hodler, int position) {
            hodler.bind(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;

            ViewHolder(View view) {
                super(view);
                textView = itemView.findViewById(android.R.id.text1);
            }

            void bind(String itemText) {
                if (textView != null) {
                    textView.setText(itemText);
                }
            }
        }
    }
}
