package com.example.photops.UI;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerViewScroller extends RecyclerView.OnScrollListener {
    private int visible = 10;
    private int previousTotalItem = 0;
    private boolean isLoading = true;
    private int currentPage = 0;
    private int initialPageIndex = 0;
    private RecyclerView.LayoutManager recyclerLayoutManager;

    public RecyclerViewScroller(GridLayoutManager layoutManager) {
        this.recyclerLayoutManager = layoutManager;
        visible = visible * layoutManager.getSpanCount();
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int lastVisibleItemPosition = ((GridLayoutManager) recyclerLayoutManager).findLastVisibleItemPosition();
        int totalItemCount = recyclerLayoutManager.getItemCount();

        if (totalItemCount < previousTotalItem) {
            this.currentPage = this.initialPageIndex;
            this.previousTotalItem = totalItemCount;
            if (totalItemCount == 0) {
                this.isLoading = true;
            }
        }

        if (isLoading && (totalItemCount > previousTotalItem)) {
            isLoading = false;
            previousTotalItem = totalItemCount;
        }

        if (!isLoading && (lastVisibleItemPosition + visible) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, view);
            isLoading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);
}
