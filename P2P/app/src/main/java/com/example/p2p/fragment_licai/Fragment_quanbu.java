package com.example.p2p.fragment_licai;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib_core.mvp.view.fragment.BaseFragment;
import com.example.p2p.R;
import com.example.p2p.adapter.MyQuanBuAdapter;
import com.example.p2p.dager.licai.compent.DaggerLiCaiCompont;
import com.example.p2p.dager.licai.compent.LiCaiCompont;
import com.example.p2p.dager.licai.modules.LiCaiModules;
import com.example.p2p.entity.QuanBuEntity;
import com.example.p2p.mvp.contract.LiCaiContract;
import com.example.p2p.mvp.presenter.LiCaiPresenter;

import java.util.ArrayList;
import java.util.List;

public class Fragment_quanbu extends BaseFragment<LiCaiPresenter> implements LiCaiContract.LiCaiView {
    @Override
    public int bindlayout() {
        return R.layout.fragment_quanbu;
    }

    @Override
    public void initInject() {
        DaggerLiCaiCompont.builder().liCaiModules(new LiCaiModules(this)).build().injectFragment_quan(this);
    }

    private MyQuanBuAdapter myQuanBuAdapter;
    private List<QuanBuEntity.ResultBean> list = new ArrayList<>();

    private RecyclerView recy;

    @Override
    public void initview() {
        recy = findviewbyid(R.id.recy);

        myQuanBuAdapter = new MyQuanBuAdapter(R.layout.item_quanbu,list);

        recy.setAdapter(myQuanBuAdapter);

        recy.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void initdata() {
        presenter.getquanbu();
    }

    @Override
    public void showquanbu(QuanBuEntity quanBuEntity) {

        List<QuanBuEntity.ResultBean> result = quanBuEntity.getResult();

        list.clear();
        list.addAll(result);

        myQuanBuAdapter.anInt = list.size();

        myQuanBuAdapter.notifyDataSetChanged();

    }
}
