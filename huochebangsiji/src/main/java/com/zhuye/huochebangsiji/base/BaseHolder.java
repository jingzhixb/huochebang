package com.zhuye.huochebangsiji.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zzzy on 2017/11/21.
 */

public class BaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private View  rootView;



    public OnItemClickListener mOnItemClickListener;
    public OnItemLongClickListener mOnItemLongClickListener;

    public BaseHolder(View itemView) {
        super(itemView);
        rootView = itemView;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);


    }

    public <V extends View> V getView(int ResId){
        return (V)rootView.findViewById(ResId);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemClickListener){
        mOnItemLongClickListener = onItemClickListener;
    }
    @Override
    public void onClick(View view) {
        if(mOnItemClickListener!=null){
            mOnItemClickListener.OnItemClick(view,getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if(mOnItemLongClickListener!=null){
            mOnItemLongClickListener.OnItemLongClick(view,getAdapterPosition());
        }
        return true;
    }

    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void OnItemLongClick(View view, int position);
    }
}
