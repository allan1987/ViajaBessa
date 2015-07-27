package br.com.mnidersoft.viajabessa.controller;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import br.com.mnidersoft.viajabessa.Constant;
import br.com.mnidersoft.viajabessa.R;
import br.com.mnidersoft.viajabessa.fragment.MainFragment_;
import br.com.mnidersoft.viajabessa.model.dto.TravelPackage;
import br.com.mnidersoft.viajabessa.service.RestClient;
import br.com.mnidersoft.viajabessa.util.DialogUtil;
import br.com.mnidersoft.viajabessa.util.GenericUtil;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @RestService
    public RestClient restClient;

    private ArrayList<TravelPackage> travelPackageList;

    @AfterViews
    public void start() {
        DialogUtil.getInstance(this).showWaiting(false);

        this.travelPackageList = new ArrayList<>();

        this.getAllPackages();
    }

    @Background
    public void getAllPackages() {
        try {
            if (GenericUtil.isOnline(this)) {
                for (Object obj : this.restClient.getAllPackages(Build.VERSION.RELEASE, Build.BRAND, Build.MODEL)) {
                    TravelPackage travelPackage = new TravelPackage((LinkedHashMap) obj);
                    this.travelPackageList.add(travelPackage);
                }

                FragmentManager fm = this.getSupportFragmentManager();
                if(fm.findFragmentById(R.id.container) == null) {
                    MainFragment_ fragment_ = new MainFragment_();

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(Constant.TRAVEL_PACKAGE_LIST, this.travelPackageList);
                    fragment_.setArguments(bundle);

                    fm.beginTransaction()
                            .add(R.id.container, fragment_)
                            .commit();
                }
                this.dismissDialog();
            }
            else {
                this.makeAlertDialog(R.string.internetError);
            }
        }
        catch (Exception e) {
            this.makeAlertDialog(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_exit) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @UiThread
    public void dismissDialog() {
        DialogUtil.getInstance(this).dismiss();
    }

    @UiThread
    public void makeAlertDialog(String message) {
        DialogUtil.getInstance(this).makeAlert(message);
    }

    public void makeAlertDialog(int messageId) {
        this.makeAlertDialog(this.getString(messageId));
    }
}