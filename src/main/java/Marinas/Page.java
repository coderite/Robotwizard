package Marinas;

/**
 * Created by zenbox on 1/17/16.
 */
public class Page {
    private String base;
    private String state;
    private int page = 1;

    public void nextPage() {
        this.page = page + 1;
    }

    public void previousPage() {
        if(this.page > 1 && this.page != 0)
        this.page = page - 1;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return base + "/" + state + "/" + page;
    }
}
