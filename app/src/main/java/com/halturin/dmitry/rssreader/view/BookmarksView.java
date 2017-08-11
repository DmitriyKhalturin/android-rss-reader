package com.halturin.dmitry.rssreader.view;

import com.halturin.dmitry.rssreader.presenter.vo.Feed;

import java.util.List;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:18.
 */

public interface BookmarksView {

    void setList(List<Feed> list);

}
