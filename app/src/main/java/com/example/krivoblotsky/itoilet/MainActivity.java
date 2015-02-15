package com.example.krivoblotsky.itoilet;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements ToiletStatusUpdaterHandler {

    /* Status updater to update toilet status */
    private ToiletStatusUpdater statusUpdater = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Create the view */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Appearance */
        this.updateViewWithStatus(ToiletStatus.ToiletStausUnavaliable);

        /* Start updating status */
        this.defaultStatusUpdater().updateStatus();

        /* Setup update button */
        this.setupUpdateButton();
    }

    /**
     * Appearance
     * */
    private void updateViewWithStatus(ToiletStatus status) {
        View contentView = this.getContentView();
        TextView textView = this.getStatusTextView();

        switch (status) {
            case ToiletStatusBusy: {
                contentView.setBackgroundColor(Color.rgb(255, 15, 53));
                textView.setText(R.string.status_busy);
            }
            break;
            case ToiletStatusFree: {
                contentView.setBackgroundColor(Color.rgb(54, 202, 115));
                textView.setText(R.string.status_free);
            }
            break;
            case ToiletStausUnavaliable: {
                contentView.setBackgroundColor(Color.GRAY);
                textView.setText(R.string.status_unavaliable);
            }
            break;
        }
    }

    /**
     * Setup update button handler
     * */
    private void setupUpdateButton() {

        /* Get the button */
        Button updateButton = this.getStatusUpdateButton();

        /* Setup listener */
        View.OnClickListener updateButtonClicked = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                statusUpdater.updateStatus();
            }
        };

        /* Set listener to button */
        updateButton.setOnClickListener(updateButtonClicked);
    }

    /**
     * Status updater
     * */
    private ToiletStatusUpdater defaultStatusUpdater() {
        if (statusUpdater == null) {
            statusUpdater = new ToiletStatusUpdater(getApplicationContext(), this);
        }
        return statusUpdater;
    }

    /**
     * ToiletStatusUpdaterHandler delegate
     * */
    public void toiletStatusUpdated(ToiletStatus status) {
        this.updateViewWithStatus(status);
    }

    /**
     * Private
     * */
    private View getContentView() {
        return this.findViewById(android.R.id.content);
    }

    private TextView getStatusTextView() {
        return (TextView)this.findViewById(R.id.statusTextView);
    }

    private Button getStatusUpdateButton() {
        return (Button)this.findViewById(R.id.statusUpdateButton);
    }
 }
