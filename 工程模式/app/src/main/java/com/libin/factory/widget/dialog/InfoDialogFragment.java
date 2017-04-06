package com.libin.factory.widget.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.libin.factory.R;

/**
 * Created by doudou on 2017/4/6.
 */

public class InfoDialogFragment extends DialogFragment {

    private EditText et_shop_price;
    private EditText et_shop_info;

    private String name;    //商品名
    private String price;   //商品价格


    public InfoDialogFragment(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public interface AddOnclickListener {
        void onAddOnClick(String name, String price);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.shop_dialog_layout, null);

        et_shop_price = (EditText) view.findViewById(R.id.et_shop_price);
        et_shop_info = (EditText) view.findViewById(R.id.et_shop_info);

        et_shop_price.setText(price);
        et_shop_info.setText(name);

        builder.setView(view)
                .setTitle("添加商品")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        et_shop_price.setText("");
                        et_shop_info.setText("");
                    }
                })
                .setNegativeButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddOnclickListener addOnclickListener = (AddOnclickListener) getActivity();
                        addOnclickListener.onAddOnClick(et_shop_info.getText().toString(), et_shop_price.getText().toString());
                    }
                });

        return builder.create();
    }
}
