package br.com.mnidersoft.viajabessa.controller;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.mnidersoft.viajabessa.Constant;
import br.com.mnidersoft.viajabessa.R;
import br.com.mnidersoft.viajabessa.model.dto.TravelPackage;
import br.com.mnidersoft.viajabessa.util.DialogUtil;
import br.com.mnidersoft.viajabessa.util.GenericUtil;

@EActivity(R.layout.activity_package)
public class TravelPackageActivity extends AppCompatActivity {

    @ViewById
    public TextView titleTxt;

    @ViewById
    public TextView priceTxt;

    @ViewById
    public ImageView photoImg;

    @ViewById
    public TextView descriptionTxt;

    private TravelPackage travelPackage;

    @AfterViews
    public void start() {
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.travelPackage = this.getIntent().getParcelableExtra(Constant.TRAVEL_PACKAGE);

        this.titleTxt.setText(this.travelPackage.title);
        this.priceTxt.setText(String.valueOf(this.travelPackage.price));
        this.descriptionTxt.setText(this.travelPackage.description);

        GenericUtil.loadImageByUrl(this, this.travelPackage.photo, this.photoImg);
    }

    @Click
    public void buyBtn() {
        DialogUtil.getInstance(this).makeAlert(R.string.buyWithSuccess, this.listener);
    }

    private DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
