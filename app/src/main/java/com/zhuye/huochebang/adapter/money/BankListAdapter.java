package com.zhuye.huochebang.adapter.money;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuye.huochebang.R;
import com.zhuye.huochebang.base.BaseHolder;
import com.zhuye.huochebang.base.BaseRecycleAdapter;
import com.zhuye.huochebang.bean.BankListBean;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class BankListAdapter extends BaseRecycleAdapter {

    public BankListAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.banklist_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

//        Random random = new Random();
//        int ran = random.nextInt(4);
//        int[] arr = new int[4];
//        arr[0] = R.drawable.bank1;
//        arr[1] = R.drawable.bank2;
//        arr[2] = R.drawable.bank3;
//        arr[3] = R.drawable.bank4;
        TextView name = holder.getView(R.id.name);
        RelativeLayout root = holder.getView(R.id.root);
        String bankname = ((BankListBean.DataBean)data.get(position)).getCard_name();
        if(bankname.contains("建设")){
            root.setBackgroundResource(R.drawable.bank1);
        }else if(bankname.contains("工商")){
            root.setBackgroundResource(R.drawable.bank2);
        }else if(bankname.contains("农业")){
            root.setBackgroundResource(R.drawable.bank3);
        } else {
            root.setBackgroundResource(R.drawable.bank4);
        }
        name.setText(bankname);
       // TextView type = holder.getView(R.id.type);
        TextView hao = holder.getView(R.id.hao);
        String haa = ((BankListBean.DataBean)data.get(position)).getCard();
        hao.setText("**** **** **** "+haa.substring(haa.length()-4));

        ImageView iv = holder.getView(R.id.moren);
        String isDefault = ((BankListBean.DataBean)data.get(position)).getIs_default();
        if(isDefault.equals("0")){
            iv.setVisibility(View.INVISIBLE);
        }else if(isDefault.equals("1")){
            iv.setVisibility(View.VISIBLE);
        }

    }
}
