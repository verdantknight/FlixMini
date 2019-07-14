package com.example.flixmini.mainlist;

import com.example.flixmini.dto.PageEntity;

public interface MovieListContract {
    interface View {
        void showPage(PageEntity page);
    }
}
