package Pubmed.Model;

import java.util.List;

/**
 * Created by zenbox on 8/2/2016.
 */
public class UIDPage {
    private Header header;
    private Esearchresult esearchresult;

    public int getCount() {
        return esearchresult.getCount();
    }

    public List<String> getIdList() {
        return esearchresult.getIdList();
    }
}

    class Header {}

    class Esearchresult {
        private int count;
        private List<String> idlist;

        public int getCount() {
            return count;
        }

        public List<String> getIdList() {
            return idlist;
        }
    }


