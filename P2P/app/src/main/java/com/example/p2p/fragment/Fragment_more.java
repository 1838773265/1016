package com.example.p2p.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.core.app.ActivityCompat;

import com.example.lib_core.mvp.view.activity.BaseActivity;
import com.example.lib_core.mvp.view.fragment.BaseFragment;
import com.example.p2p.R;
import com.example.p2p.activity.GestureEditActivity;
import com.example.p2p.activity.GuiGuInvestActivity;
import com.example.p2p.utils.UIUtils;

public class Fragment_more extends BaseFragment {
    @Override
    public int bindlayout() {
        return R.layout.fragmnet_more;
    }

    @Override
    public void initInject() {

    }

    private ToggleButton toggleMore;
    private TextView tvMoreReset;
    private TextView tvMorePhone;
    private TextView tvMoreFankui;
    private TextView tvMoreShare;
    private TextView tvMoreAbout;
    private RelativeLayout rlMoreContact;



    private SharedPreferences sp;

    @Override
    public void initview() {

        toggleMore = (ToggleButton) findviewbyid(R.id.toggle_more);
        tvMoreReset = (TextView) findviewbyid(R.id.tv_more_reset);
        tvMorePhone = (TextView) findviewbyid(R.id.tv_more_phone);
        tvMoreFankui = (TextView) findviewbyid(R.id.tv_more_fankui);
        tvMoreShare = (TextView) findviewbyid(R.id.tv_more_share);
        tvMoreAbout = (TextView) findviewbyid(R.id.tv_more_about);
        rlMoreContact = (RelativeLayout) findviewbyid(R.id.rl_more_contact);

        //初始化SharedPreferences
        sp = this.getActivity().getSharedPreferences("secret_protect", Context.MODE_PRIVATE);
        //用户注册

    }

    @Override
    public void initdata() {

        //设置手势密码
        setGesturePassword();

        //重置手势密码
        resetGesture();

        //联系客服
        contactService();

        //提交反馈意见
        commitFanKui();

        //分享
        share();

        //关于硅谷理财
        aboutInvest();

    }


    private void aboutInvest() {
        tvMoreAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GuiGuInvestActivity.class));
            }
        });
    }

    private void share() {
        tvMoreShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }

    private void showShare() {

        tvMoreShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");

                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, "快来下载硅谷金融");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity"));

                getContext().startActivity(Intent.createChooser(intent, "Share"));
            }
        });


    }

    private String department = "不明确";

    private void commitFanKui() {
        tvMoreFankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提供一个View
                View view = View.inflate(getActivity(), R.layout.view_fankui, null);
                final RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg_fankui);
                final EditText et_fankui_content = (EditText) view.findViewById(R.id.et_fankui_content);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton rb = (RadioButton) rg.findViewById(checkedId);
                        department = rb.getText().toString();
                    }
                });

                new AlertDialog.Builder(getActivity())
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //获取反馈的信息
                                String content = et_fankui_content.getText().toString();

                                        UIUtils.toast("发送反馈信息成功", false);

                                        UIUtils.toast("发送反馈信息失败", false);

                                    }
                                })
                        .setNegativeButton("取消", null)
                        .show();

            }
        });
    }

    private void contactService() {
        rlMoreContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("联系客服")
                        .setMessage("是否现在联系客服：010-56253825")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //获取手机号码
                                String phone = tvMorePhone.getText().toString().trim();
                                //使用隐式意图，启动系统拨号应用界面
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + phone));
                                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                getActivity().startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }

    private void resetGesture() {
        tvMoreReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = toggleMore.isChecked();
                if (checked) {
                    startActivity(new Intent(getActivity(),GestureEditActivity.class));
                } else {
                    UIUtils.toast("手势密码操作已关闭，请开启后再设置", false);
                }
            }
        });
    }

    private void setGesturePassword() {
        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    UIUtils.toast("开启了手势密码", false);
//                    sp.edit().putBoolean("isOpen", true).commit();
                    String inputCode = sp.getString("inputCode", "");
                    if (TextUtils.isEmpty(inputCode)) {//之前没有设置过
                        new AlertDialog.Builder(getActivity())
                                .setTitle("设置手势密码")
                                .setMessage("是否现在设置手势密码")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        UIUtils.toast("现在设置手势密码", false);
                                        sp.edit().putBoolean("isOpen", true).commit();
//                                            toggleMore.setChecked(true);
                                        //开启新的activity:
                                        startActivity(new Intent(getActivity(),GestureEditActivity.class));
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        UIUtils.toast("取消了现在设置手势密码", false);
                                        sp.edit().putBoolean("isOpen", false).commit();
                                        toggleMore.setChecked(false);

                                    }
                                })
                                .show();
                    } else {
                        UIUtils.toast("开启手势密码", false);
                        sp.edit().putBoolean("isOpen", true).commit();
//                        toggleMore.setChecked(true);
                    }
                } else {
                    UIUtils.toast("关闭了手势密码", false);
                    sp.edit().putBoolean("isOpen", false).commit();
//                    toggleMore.setChecked(false);

                }
            }
        });
    }

}
