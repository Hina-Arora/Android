package dropit.hina.dropitproj.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import dropit.hina.dropitproj.R;

public class GridInsetDecoration extends RecyclerView.ItemDecoration {

    private int insetHorizontal = 0;
    private int insetVertical = 0;

    public GridInsetDecoration(Context context) {
        insetHorizontal = context.getResources()
                .getDimensionPixelSize(R.dimen.grid_horizontal_spacing);
        insetVertical = context.getResources()
                .getDimensionPixelOffset(R.dimen.grid_vertical_spacing);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = insetVertical;
        outRect.right = insetHorizontal;
        outRect.bottom = insetVertical;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = insetVertical;
        } else {
            outRect.top = 0;
        }
    }
}