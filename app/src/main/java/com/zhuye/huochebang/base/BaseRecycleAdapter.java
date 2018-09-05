package com.zhuye.huochebang.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zzzy on 2017/11/21.
 */

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseHolder> {

     protected Context conn;
    protected List<T> data = new ArrayList<>();

    public BaseHolder.OnItemClickListener mOnItemClickListener;
    public BaseHolder.OnItemLongClickListener mOnItemLongClickListener;
    public BaseRecycleAdapter(Context conn) {
        this.conn= conn;

    }

    public List<T> getDat(){
        return data;
    }
    public BaseRecycleAdapter(Context conn,List<T> data) {
        this.conn= conn;
        this.data= data;

    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // View view = View.inflate(conn,getResId(),null);
        //// TODO: 2017/12/5  
        View view = LayoutInflater.from(conn).inflate(getResId(),parent,false);
        BaseHolder baseholder = new BaseHolder(view);
        return baseholder;
    }

    protected abstract int getResId();
    public  void setData(List<T> data){
        this.data= data;
    }


    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        conver(holder,position);
        if(mOnItemClickListener!=null)
            holder.setOnItemClickListener(mOnItemClickListener);
        if(mOnItemLongClickListener!=null){
            holder.setOnItemLongClickListener(mOnItemLongClickListener);
        }
    }

    protected abstract void conver(BaseHolder holder, int position);



    @Override
    public int getItemCount() {
        if(data== null|| data.size()<=0){
            return 0;
        }
        return data.size();

    }


    public void setOnItemClickListener(BaseHolder.OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(BaseHolder.OnItemLongClickListener onItemLongClickListener){
        mOnItemLongClickListener = onItemLongClickListener;
    }

//    public <V extends View> V getView(int ResId){
//        return (V)rootView.findViewById(ResId);
//    }



    public void removeData(T t,int position){
        this.data.remove(t);
        notifyItemRemoved(position);

    }

    public void removeData(int position){
        this.data.remove(position);
        notifyItemRemoved(position);

    }

    public void removeData(List<T> t){
        removeData(t,0);

    }

    public void removeData(List<T> t,int position){
        this.data.removeAll(t);
        notifyItemRemoved(position);
    }

    public void clear(){
        int itemCount = data.size();
        data.clear();
        this.notifyItemRangeRemoved(0,itemCount);
    }

    public int getSize(){
       return this.data.size();
    }



    public void addData(List<T> t){
        addData(t,0);
    }

    public void addData(Map t){
        Iterator iter = t.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            data.add((T) entry.getKey());
        }
//        data.clear();
//        addData(data,0);
        notifyItemInserted(0);
    }
    public void addData(List<T> t,int position){
        this.data.addAll(t);
        notifyItemInserted(position);
    }

    public void updataItem(T t , int position){
        data.remove(position);
        this.data.add(position,t);
        notifyItemChanged(position);
    }

    public void addData2(List<T> t,int position){
        data.clear();
        this.data.addAll(t);
        notifyItemRangeChanged(position,data.size());
    }

}
