package com.company;

import java.util.Arrays;
import java.util.LinkedList;

public class Picture {
    private int[] _id = {0, -1};
    private boolean _vertical = false;
    private LinkedList<String> _tags = new LinkedList<String>();
    private int _score = 0;
    private int _tagCount = 0;

    public Picture(int id, int tagCount){
        _id[0]=id;
        _tagCount=tagCount;
    }

    public void setOrientation(char orientation){
        if (orientation == 'H'){
            _vertical=false;
        }
        else {
            _vertical=true;
        }
    }

    public boolean getVertical(){
        return _vertical;
    }

    public void setTags(String tags) {
        _tags.add(tags);
    }

    public LinkedList<String> getTags(){
        return _tags;
    }

    public int getTagCount(){
        return _tagCount;
    }
    public void commonTags(Picture next){
        int common=0;

            for (int i = 0; i<next.getTagCount(); i++){
                for (int j = 0; j<_tagCount; j++) {
                        if (_tags.get(j).equals(next.getTags().get(i))) {
                            common++;
                        }

                }
            }

        int[] minimun= {common,_tagCount-common,next.getTagCount()-common};
        Arrays.sort(minimun);
        _score=minimun[0];

    }

    public boolean merge(Picture next, int threshold){
        boolean merged = false;
        boolean add;
        int newTagCount=0;
        if(_vertical && next.getVertical() && _score<=threshold){
            _id[1]=next.getSingleId();
            _vertical=false;
            for (int i = 0; i<next.getTagCount();i++){
                add = true;
                for (int j = 0; j<_tagCount; j++) {
                    if (add && _tags.get(j).equals(next.getTags().get(i))) {
                        add=false;
                    }
                }
                if(add) {
                    _tags.add(next.getTags().get(i));
                    newTagCount++;
                }
            }
            _tagCount+=newTagCount;
            merged = true;
        }
        return merged;
    }

    public int getSingleId(){
        return _id[0];
    }

    public String getId(){
        if(_id[1] !=-1) {
            return String.format("%s %s", _id[0], _id[1]);
        }
        else {
            return Integer.toString(_id[0]);
        }
    }

    public int getScore(){
        return _score;
    }
}
