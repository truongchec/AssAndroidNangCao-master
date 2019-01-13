package com.example.dung.ass;

public class LichHoc {
    private String tvNgayHoc;
    private String tvPhongHoc;
    private String tvMonHoc;
    private String tvGvHoc;

    public LichHoc(String tvNgayHoc, String tvPhongHoc, String tvMonHoc, String tvGvHoc) {
        this.tvNgayHoc = tvNgayHoc;
        this.tvPhongHoc = tvPhongHoc;
        this.tvMonHoc = tvMonHoc;
        this.tvGvHoc = tvGvHoc;
    }

    public String getTvNgayHoc() {
        return tvNgayHoc;
    }

    public void setTvNgayHoc(String tvNgayHoc) {
        this.tvNgayHoc = tvNgayHoc;
    }

    public String getTvPhongHoc() {
        return tvPhongHoc;
    }

    public void setTvPhongHoc(String tvPhongHoc) {
        this.tvPhongHoc = tvPhongHoc;
    }

    public String getTvMonHoc() {
        return tvMonHoc;
    }

    public void setTvMonHoc(String tvMonHoc) {
        this.tvMonHoc = tvMonHoc;
    }

    public String getTvGvHoc() {
        return tvGvHoc;
    }

    public void setTvGvHoc(String tvGvHoc) {
        this.tvGvHoc = tvGvHoc;
    }
}
